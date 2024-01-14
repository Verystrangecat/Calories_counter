package com.example.project;

import static com.google.android.material.bottomnavigation.BottomNavigationView.*;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.project.utils.Broadcast_reciever;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Calendar;


public class Main_screen extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager = null;
    private Sensor stepsensor;
    private int totalsteps = 0;
    private int previoustotalsteps = 0;
    private ProgressBar progressBar;
    private TextView showsteps;
    private MenuItem main, diary, more;

    private NavigationBarView bottomNavigationView;
    ViewPager2 viewPager2;
    ArrayList<ViewpagerItem> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        setupUI();
        resetSteps();
        loadData();
        bottom_navigation();
        setViewPager2();
//todo:pop up asking for a users permission


    }

    private void setupUI() {
        progressBar = findViewById(R.id.progressBar);
        showsteps = findViewById(R.id.txt_steps);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        stepsensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        viewPager2 = findViewById(R.id.viewpager);


    }

    protected void onResume() {
        super.onResume();
        if (stepsensor == null) {
            Toast.makeText(this, "NO SENSOR", Toast.LENGTH_SHORT).show();
        } else {
            sensorManager.registerListener(this, stepsensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            totalsteps = (int) sensorEvent.values[0];
            int currentsteps = totalsteps - previoustotalsteps;
            showsteps.setText(String.valueOf(currentsteps));
            progressBar.setProgress(currentsteps);

        }

    }

    protected void resetSteps() {
        showsteps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Main_screen.this, "Long press to resert", Toast.LENGTH_SHORT).show();
            }
        });
        showsteps.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                previoustotalsteps = totalsteps;
                showsteps.setText("0");
                progressBar.setProgress(0);
                saveData();
                return true;
            }
        });
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("key1", String.valueOf(previoustotalsteps));
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);
        int savednum = Integer.parseInt(sharedPreferences.getString("key1", "0"));
        previoustotalsteps = savednum;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private void bottom_navigation() {
        BottomNavigationView bottomNavigationView1 = findViewById(R.id.bottom_navigation);
        bottomNavigationView1.setSelectedItemId(R.id.navigation_home);
        bottomNavigationView1.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_home)
                return true;
            else if (item.getItemId() == R.id.navigation_diary) {
                startActivity(new Intent(Main_screen.this, Diary_screen_tabs.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.navigation_graphs) {
                startActivity(new Intent(Main_screen.this, Login.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.navigation_more) {
                startActivity(new Intent(Main_screen.this, More_activity.class));
                finish();
                return true;
            }
            return false;
        });
    }

    public void setViewPager2() {
        arrayList = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);
        arrayList.add(new ViewpagerItem("Calories", sharedPreferences.getString("amount_calories", "0"),
                sharedPreferences.getString("amount_calories_left", sharedPreferences.getString("amount_calories", "0")), R.drawable.circle_calorie));
        arrayList.add(new ViewpagerItem("Carbs", sharedPreferences.getString("amount_carbs", "0"),
                sharedPreferences.getString("amount_carbs_left", sharedPreferences.getString("amount_carbs", "0")), R.drawable.circle_carbs));
        arrayList.add(new ViewpagerItem("Fats", sharedPreferences.getString("amount_fats", "0"),
                sharedPreferences.getString("amount_fats_left", sharedPreferences.getString("amount_fats", "0")), R.drawable.circle_fats));
        arrayList.add(new ViewpagerItem("Proteins", sharedPreferences.getString("amount_proteins", "0"),
                sharedPreferences.getString("amount_proteins_left", sharedPreferences.getString("amount_proteins", "0")), R.drawable.circle_proteins));
        //Todo figure out the way to get and pass the information, preferebaly without sharedpreference
        Vp_adapter vp_adapter = new Vp_adapter(arrayList);
        viewPager2.setAdapter(vp_adapter);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(2);
        viewPager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    public void notification() {
    create_notification_channel();
        Intent intent=new Intent(Main_screen.this, Broadcast_reciever.class);
        PendingIntent pendingIntentmorning=PendingIntent.getBroadcast(Main_screen.this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar calendarmorning = Calendar.getInstance();
        calendarmorning.set(Calendar.HOUR_OF_DAY, 8); // Set the hour (24-hour format)
        calendarmorning.set(Calendar.MINUTE, 0);// Set the minute
        alarmManager.set(AlarmManager.RTC_WAKEUP,calendarmorning.getTimeInMillis(),
                pendingIntentmorning);
        PendingIntent pendingIntentday=PendingIntent.getBroadcast(Main_screen.this, 1, intent, PendingIntent.FLAG_IMMUTABLE);
        Calendar calendarday = Calendar.getInstance();
        calendarday.set(Calendar.HOUR_OF_DAY, 14); // Set the hour (24-hour format)
        calendarday.set(Calendar.MINUTE, 0);// Set the minute
        alarmManager.set(AlarmManager.RTC_WAKEUP,calendarday.getTimeInMillis(),
                pendingIntentday);
        PendingIntent pendingIntentevening=PendingIntent.getBroadcast(Main_screen.this, 2, intent, PendingIntent.FLAG_IMMUTABLE);
        Calendar calendarevening = Calendar.getInstance();
        calendarevening.set(Calendar.HOUR_OF_DAY, 19); // Set the hour (24-hour format)
        calendarevening.set(Calendar.MINUTE, 0);// Set the minute
        alarmManager.set(AlarmManager.RTC_WAKEUP,calendarevening.getTimeInMillis(),
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


        //todo make everything reset at midnight

    }
}
//todo circular_menu





