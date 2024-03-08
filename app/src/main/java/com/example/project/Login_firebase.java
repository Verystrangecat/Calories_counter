package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
            cleanarryas();
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
                            editor.putString("amount_calories",String.valueOf(user.getCalories()));
                            editor.putString("amount_proteins",String.valueOf(user.getProtein()));
                            editor.putString("amount_carbs",String.valueOf(user.getCarbs()));
                            editor.putString("amount_fats",String.valueOf(user.getFat()));
                            editor.putString("amount_calories_left",String.valueOf(user.getCalories()));
                            editor.putString("amount_proteins_left",String.valueOf(user.getProtein()));
                            editor.putString("amount_carbs_left",String.valueOf(user.getCarbs()));
                            editor.putString("amount_fats_left",String.valueOf(user.getFat()));
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
        editor.putString("MyObject", json);

        Gson gson2 = new Gson();
        Array_class_steps steps = new Array_class_steps();
        String json2 = gson2.toJson(steps);
        editor.putString("Array_steps",json2);

        editor.apply();
        //todo add the second array
    }

    }
    //todo change the screen of notification




