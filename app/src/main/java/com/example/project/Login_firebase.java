package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

import java.util.Calendar;

public class Login_firebase extends AppCompatActivity implements View.OnClickListener {
    EditText login, password;
    Button bntlog, short_cut;
    TextView link;
    Animation anim_button;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_firebase);
        setupUi();
        notification();
    }

    private void setupUi() {
        login = findViewById(R.id.edittext_login_emailf);
        password = findViewById(R.id.edittext_login_passwordf);
        bntlog = findViewById(R.id.btnLoginf);
        link = findViewById(R.id.text_link_signupf);
        short_cut = findViewById(R.id.btn_shortcutf);
        anim_button = AnimationUtils.loadAnimation(this, R.anim.login_button);
        mAuth = FirebaseAuth.getInstance();
        bntlog.setOnClickListener(this);
        link.setOnClickListener(this);
        short_cut.setOnClickListener(this);
    }

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

        } else if (view == short_cut) {
            startActivity(new Intent(Login_firebase.this, Main_screen.class));
            finish();
        }

    }

    public void notification() {
        create_notification_channel();
        Intent intent = new Intent(Login_firebase.this, Broadcast_reciever.class);
        PendingIntent pendingIntentmorning = PendingIntent.getBroadcast(Login_firebase.this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar calendarmorning = Calendar.getInstance();
        calendarmorning.set(Calendar.HOUR_OF_DAY, 8); // Set the hour (24-hour format)
        calendarmorning.set(Calendar.MINUTE, 0);// Set the minute
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendarmorning.getTimeInMillis(),AlarmManager.INTERVAL_DAY,
                pendingIntentmorning);
        PendingIntent pendingIntentday = PendingIntent.getBroadcast(Login_firebase.this, 1, intent, PendingIntent.FLAG_IMMUTABLE);
        Calendar calendarday = Calendar.getInstance();
        calendarday.set(Calendar.HOUR_OF_DAY, 14); // Set the hour (24-hour format)
        calendarday.set(Calendar.MINUTE, 0);// Set the minute
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendarday.getTimeInMillis(),AlarmManager.INTERVAL_DAY,
                pendingIntentday);
        PendingIntent pendingIntentevening = PendingIntent.getBroadcast(Login_firebase.this, 2, intent, PendingIntent.FLAG_IMMUTABLE);
        Calendar calendarevening = Calendar.getInstance();
        calendarevening.set(Calendar.HOUR_OF_DAY, 19); // Set the hour (24-hour format)
        calendarevening.set(Calendar.MINUTE, 0);// Set the minute
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendarevening.getTimeInMillis(),AlarmManager.INTERVAL_DAY,
                pendingIntentevening);


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
}