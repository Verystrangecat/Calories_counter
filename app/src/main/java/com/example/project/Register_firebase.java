package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
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
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.utils.Broadcast_reciever;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

import java.util.Calendar;


public class Register_firebase extends AppCompatActivity implements View.OnClickListener {
    EditText name, password, checkpassword, email;
    Button signup;
    TextView link_login;
    FirebaseAuth mAuth;
    private static final String string_permission= Manifest.permission.POST_NOTIFICATIONS;
    private static final int permission_code=100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_firebase);
        setupUi();
        check_the_permission();

    }

    private void setupUi() {
        name = findViewById(R.id.editTextnamef);
        password = findViewById(R.id.editText_signup_passwordf);
        checkpassword = findViewById(R.id.editText_signup_passwordcheckf);
        email = findViewById(R.id.editText_signup_emailf);
        signup = findViewById(R.id.btncreateaccountf);
        signup.setOnClickListener(this);
        link_login = findViewById(R.id.text_link_loginf);
        link_login.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        String emails, passwords;
        if (view == signup) {

            emails = email.getText().toString();
            passwords = password.getText().toString();
            if (TextUtils.isEmpty(emails)) {
                email.setError("Please enter your email");
                return;
            }
            if (TextUtils.isEmpty(passwords)) {
                password.setError("Please enter your password");
                return;
            }
            if(!checkDataEntered())
                return;

            mAuth.createUserWithEmailAndPassword(emails, passwords)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Register_firebase.this, "Continue creating account",
                                        Toast.LENGTH_SHORT).show();
                                Array_class arrayClass=new Array_class();
                                Array_class_steps steps=new Array_class_steps();
                                SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                Gson gson = new Gson();
                                String json = gson.toJson(arrayClass);
                                editor.putString("MyObject", json);
                                //creating an array for future use
                                String array=gson.toJson(steps);
                                editor.putString("Array_steps",array);
                                editor.apply();
                                startActivity(new Intent(Register_firebase.this, Setup_account.class));
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(Register_firebase.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }


                    });
        } else if (view == link_login) {
            startActivity(new Intent(Register_firebase.this, Login_firebase.class));

        }


    }
//Checks if data is entered and if it's correct
    private boolean checkDataEntered() {
        if (isEmpty(name)) {
            name.setError("You must enter your name to sign up");
         return false;
        }
        if (isEmpty(checkpassword)) {
            checkpassword.setError("You must confirm the password to sign up");
            return false;

        }
        if(!isEmail(email)) {
            email.setError("You must enter valid email to sign up");
        return false;}
        String passtext=password.getText().toString();
        String checkpasstext=checkpassword.getText().toString();
        if(passtext.length()<6){
            password.setError("Password should consist of minimum 6 characters");
            return false;
        }
        if(passtext.equals(checkpasstext))
            return true;
        checkpassword.setError("Is not the same as the password");
        return false;



        }


    public boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    public boolean isEmail(EditText text){
        CharSequence str=text.getText().toString();
        return !TextUtils.isEmpty(str)&& Patterns.EMAIL_ADDRESS.matcher(str).matches();
    }
    public void notification() {
        create_notification_channel();
        Intent intent = new Intent(Register_firebase.this, Broadcast_reciever.class);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        if(!isAlarmSet(Register_firebase.this, 0)){
            PendingIntent pendingIntentmorning = PendingIntent.getBroadcast(Register_firebase.this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
            Calendar calendarmorning = Calendar.getInstance();calendarmorning.set(Calendar.HOUR_OF_DAY, 8); // Set the hour (24-hour format)calendarmorning.set(Calendar.MINUTE, 0);// Set the minute
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendarmorning.getTimeInMillis(),AlarmManager.INTERVAL_DAY,
                    pendingIntentmorning);}
        if(!isAlarmSet(Register_firebase.this, 1)){
            PendingIntent pendingIntentday = PendingIntent.getBroadcast(Register_firebase.this, 1, intent, PendingIntent.FLAG_IMMUTABLE);
            Calendar calendarday = Calendar.getInstance();
            calendarday.set(Calendar.HOUR_OF_DAY, 14); // Set the hour (24-hour format)
            calendarday.set(Calendar.MINUTE, 0);// Set the minute
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendarday.getTimeInMillis(),AlarmManager.INTERVAL_DAY,
                    pendingIntentday);}
        if(!isAlarmSet(Register_firebase.this, 2)){
            PendingIntent pendingIntentevening = PendingIntent.getBroadcast(Register_firebase.this, 2, intent, PendingIntent.FLAG_IMMUTABLE);
            Calendar calendarevening = Calendar.getInstance();
            calendarevening.set(Calendar.HOUR_OF_DAY, 19); // Set the hour (24-hour format)
            calendarevening.set(Calendar.MINUTE, 0);// Set the minute
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendarevening.getTimeInMillis(),AlarmManager.INTERVAL_DAY,
                    pendingIntentevening);}



    }


    public void create_notification_channel() {
        CharSequence name = "Reminder_channel";
        String description = "Channel for notifications about meals";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel("notifyme", name, importance);
        channel.setDescription(description);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
        //Todo add the forgot password thing
    }

    private void check_the_permission() {
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
                        ActivityCompat.requestPermissions(Register_firebase.this, new String[]{string_permission}, permission_code);
                        dialogInterface.dismiss();
                    })
                    .setNegativeButton("Forbid", (dialogInterface, i) -> dialogInterface.dismiss());
            builder.show();
        } else {
            // Request the permission for the first time
            ActivityCompat.requestPermissions(this, new String[]{string_permission}, permission_code);
        }
    }

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
    public boolean isAlarmSet(Context context, int requestCode) {
        Intent intent = new Intent(context, Broadcast_reciever.class);

        // Use FLAG_NO_CREATE to check if the PendingIntent exists
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent,
                PendingIntent.FLAG_NO_CREATE | PendingIntent.FLAG_IMMUTABLE);

        return pendingIntent != null;
    }
}




