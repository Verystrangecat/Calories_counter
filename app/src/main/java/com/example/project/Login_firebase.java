package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.utils.Broadcast_reciever;
import com.github.mikephil.charting.data.BarEntry;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Login_firebase extends AppCompatActivity implements View.OnClickListener {
    EditText login, password;
    Button bntlog;
    TextView link;
    Animation anim_button;
    FirebaseAuth mAuth;
    private static final String string_permission= Manifest.permission.POST_NOTIFICATIONS;
    private static final int permission_code=100;

    /**
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_firebase);
        setupUi();
        checknotification();

    }

    /**
     * connects ui to the class and sets up onclick
     */
    private void setupUi() {
        login = findViewById(R.id.edittext_login_emailf);
        password = findViewById(R.id.edittext_login_passwordf);
        bntlog = findViewById(R.id.btnLoginf);
        link = findViewById(R.id.text_link_signupf);
        anim_button = AnimationUtils.loadAnimation(this, R.anim.login_button);
        mAuth = FirebaseAuth.getInstance();
        bntlog.setOnClickListener(this);
        link.setOnClickListener(this);

    }

    /**
     *
     * @param view The view that was clicked.
     *       click for all the buttons
     *             adds the email and password to authetifaction after checking that they are not empty
     */
    @Override
    public void onClick(View view) {
        String emails, passwords;
        if (view == bntlog) {
            emails = login.getText().toString();
            passwords = password.getText().toString();
            if (TextUtils.isEmpty(emails)) {
                login.setError("Please enter your email");
                return;
            }
            if (TextUtils.isEmpty(passwords)) {
                password.setError("Please enter your password");
                return;
            }
            bntlog.startAnimation(anim_button);

            mAuth.signInWithEmailAndPassword(emails, passwords)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Toast.makeText(Login_firebase.this, "Login successful.",
                                        Toast.LENGTH_SHORT).show();
                                checkifotheruser();
                                startActivity(new Intent(Login_firebase.this, Main_screen.class));

                                finish();
                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(Login_firebase.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                    });

        } else if (view == link) {
            startActivity(new Intent(Login_firebase.this, Register_firebase.class));

        }

    }

    /**
     * checks if the user is different and if yes changes the information
     */
    public void checkifotheruser(){
        SharedPreferences sharedPreferences= getSharedPreferences("my pref", Context.MODE_PRIVATE);
        String oldemail=sharedPreferences.getString("email","");
        if(!oldemail.equals(login.getText().toString())){
            Log.e("Emails","Emails are not equal");
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("email",login.getText().toString());
            editor.apply();
            savethedate();
            cleanarryas(); //if user is different it clears the arrays
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            Query query = databaseReference.child("users").orderByChild("email").equalTo(login.getText().toString());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                /**
                 *
                 * @param dataSnapshot The current data at the location
                 *                     gets the specific user
                 */
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // Iterate through the results
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Extract the User object

                        User user = snapshot.getValue(User.class);
                        Log.e("User", "user not found");
                        if (user != null) {
                            Log.e("User", "user found");
                            // Found the user, you can access the user details
                            SharedPreferences sharedPreferences= getSharedPreferences("my pref", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            Log.e("Shared preferences", user.getName());
                            editor.putString(getString(R.string.amount_calories),String.valueOf(user.getCalories()));
                            editor.putString(getString(R.string.amount_proteins),String.valueOf(user.getProtein()));
                            editor.putString(getString(R.string.amount_carbs),String.valueOf(user.getCarbs()));
                            editor.putString(getString(R.string.amount_fats),String.valueOf(user.getFat()));
                            editor.putString(getString(R.string.amount_calories_left),String.valueOf(user.getCalories()));
                            editor.putString(getString(R.string.amount_proteins_left),String.valueOf(user.getProtein()));
                            editor.putString(getString(R.string.amount_carbs_left),String.valueOf(user.getCarbs()));
                            editor.putString(getString(R.string.amount_fats_left),String.valueOf(user.getFat()));
                            //todo get the current date and change the food array not the steps though but the steps array
                            editor.apply();
                            break; // Assuming there's only one user with the given email
                        }
                    }
                }

                /**
                 *
                 * @param databaseError A description of the error that occurred
                 */


                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Handle errors
                }
            });
        }


        }
    /**
     * saves the current date
     */
    private void savethedate() {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd.MM");
        String datenow = df.format(c);
        SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("date", datenow);
        editor.apply();
    }

    /**
     * cleans the arrays of food and steps if the user is changed
     */
    private void cleanarryas(){
        SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Array_class obj=new Array_class();
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        editor.putString(getString(R.string.array_meals), json);

        Gson gson2 = new Gson();
        Array_class_steps steps = new Array_class_steps();
        String json2 = gson2.toJson(steps);
        editor.putString(getString(R.string.array_steps),json2);

        editor.apply();
        //todo add the second array
    }

    /**
     *  check if the permission for notification is granted if no asks for permission
     *       else builds the notification
     */
    public void checknotification(){
            if (ActivityCompat.checkSelfPermission(this, string_permission) == PackageManager.PERMISSION_GRANTED) {
                notification();
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, string_permission)) {
                // Show rationale if permission was denied before
                // This is the case where the user denied the permission previously, but did not check "Don't ask again."
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Allow us to send messages so this will be easier for you")
                        .setTitle("Permission required")
                        .setCancelable(false)
                        .setPositiveButton("Allow", (dialogInterface, i) -> {
                            ActivityCompat.requestPermissions(Login_firebase.this, new String[]{string_permission}, permission_code);
                            dialogInterface.dismiss();
                        })
                        .setNegativeButton("Forbid", (dialogInterface, i) -> dialogInterface.dismiss());
                builder.show();
            } else {
                // Request the permission for the first time
                ActivityCompat.requestPermissions(this, new String[]{string_permission}, permission_code);
            }
        }

        /**
         *
         * @param requestCode The request code passed in
         * @param permissions The requested permissions. Never null.
         * @param grantResults The grant results for the corresponding permissions
         *     which is either {@link android.content.pm.PackageManager#PERMISSION_GRANTED}
         *     or {@link android.content.pm.PackageManager#PERMISSION_DENIED}. Never null.
         *deals with the result of the answer
         */
        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (requestCode == permission_code) {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    notification();
                } else if (shouldShowRequestPermissionRationale(string_permission)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Notifications will help you to stay tuned")
                            .setTitle("Permission needed")
                            .setCancelable(false)
                            .setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss())
                            .setPositiveButton("Settings", (dialogInterface, i) -> {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", getPackageName(), null);
                                intent.setData(uri);
                                startActivity(intent);
                                dialogInterface.dismiss();
                            });
                    builder.show();
                }
            }

        }

        /**
         *
         * @param context
         * @param requestCode
         * @return boolean if the alarm is set
         */
        public boolean isAlarmSet(Context context, int requestCode) {
            Intent intent = new Intent(context, Broadcast_reciever.class);

            // Use FLAG_NO_CREATE to check if the PendingIntent exists
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent,
                    PendingIntent.FLAG_NO_CREATE | PendingIntent.FLAG_IMMUTABLE);

            return pendingIntent != null;
        }
    /**
     * creates the notifiaction for the meals
     */
    public void notification() {
        create_notification_channel();
        Intent intent = new Intent(Login_firebase.this, Broadcast_reciever.class);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        if(!isAlarmSet(Login_firebase.this, 0)){
            PendingIntent pendingIntentmorning = PendingIntent.getBroadcast(Login_firebase.this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
            Calendar calendarmorning = Calendar.getInstance();calendarmorning.set(Calendar.HOUR_OF_DAY, 8); // Set the hour (24-hour format)calendarmorning.set(Calendar.MINUTE, 0);// Set the minute
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendarmorning.getTimeInMillis(),AlarmManager.INTERVAL_DAY,
                    pendingIntentmorning);}
        if(!isAlarmSet(Login_firebase.this, 1)){
            PendingIntent pendingIntentday = PendingIntent.getBroadcast(Login_firebase.this, 1, intent, PendingIntent.FLAG_IMMUTABLE);
            Calendar calendarday = Calendar.getInstance();
            calendarday.set(Calendar.HOUR_OF_DAY, 14); // Set the hour (24-hour format)
            calendarday.set(Calendar.MINUTE, 0);// Set the minute
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendarday.getTimeInMillis(),AlarmManager.INTERVAL_DAY,
                    pendingIntentday);}
        if(!isAlarmSet(Login_firebase.this, 2)){
            PendingIntent pendingIntentevening = PendingIntent.getBroadcast(Login_firebase.this, 2, intent, PendingIntent.FLAG_IMMUTABLE);
            Calendar calendarevening = Calendar.getInstance();
            calendarevening.set(Calendar.HOUR_OF_DAY, 19); // Set the hour (24-hour format)
            calendarevening.set(Calendar.MINUTE, 0);// Set the minute
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendarevening.getTimeInMillis(),AlarmManager.INTERVAL_DAY,
                    pendingIntentevening);}



    }

    /**
     * creates the notification channel
     */
    public void create_notification_channel() {
        CharSequence name = "Reminder_channel";
        String description = "Channel for notifications about meals";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel("notifyme", name, importance);
        channel.setDescription(description);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

    }
    }


    //todo change the screen of notification




