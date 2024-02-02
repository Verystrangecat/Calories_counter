package com.example.project.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.IBinder;

import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.project.R;

public class Step_Counter_Service extends Service implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor stepsensor;
    private int totalsteps = 0;
    private int previoustotalsteps = 0;

    private static final int NOTIFICATION_ID = 987;

    private static final String CHANNEL_ID = "StepCountingChannel";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        createNotification();
        setupSensors();
    }
    //todo think how i can update the ui

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            totalsteps = (int) sensorEvent.values[0];
            int currentsteps = totalsteps - previoustotalsteps;
            // Update your UI or perform any necessary action
            // ...

            // For demonstration, updating the notification text
            updateNotificationText(String.valueOf(currentsteps));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //nothing needed
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
        sensorManager.unregisterListener(this);
        SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("key1", String.valueOf(previoustotalsteps));
        editor.apply();
        //todo deal with shared preference
    }
    private void setupSensors() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepsensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if (stepsensor == null) {
            stopSelf();
            Toast.makeText(getApplicationContext(), "No sensor available", Toast.LENGTH_SHORT);
        } else {
            sensorManager.registerListener(this, stepsensor, SensorManager.SENSOR_DELAY_NORMAL);
            SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);
            int savednum = Integer.parseInt(sharedPreferences.getString("key1", "0"));
            previoustotalsteps = savednum;

        }
    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Step Counting Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    private void createNotification() {
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Step counter ")
                .setContentText("Counting steps...")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build();

        startForeground(NOTIFICATION_ID, notification);
    }

    private void updateNotificationText(String text) {
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("You walked today")
                .setContentText("Steps: " + text)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build();

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, notification);
    }


}

