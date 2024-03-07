package com.example.project.utils;

import android.app.ActivityManager;
import android.app.AlarmManager;
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

import android.util.Log;
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
    private static int totalsteps = 0;
    private  static int previoustotalsteps = 0;
    public static int currentsteps=0;


    private static final int NOTIFICATION_ID = 987;
    public static boolean isRunning=false;

    private static final String CHANNEL_ID = "StepCountingChannel";

    /**
     *tells what to do when the service is created
     */

    @Override
    public void onCreate() {
        Log.e("C","Oncreate");
        isRunning=true;
        super.onCreate();
        createNotificationChannel();
        createNotification();
        setupSensors();
    }
    //todo think how i can update the ui


    /**
     *
     * @param intent The Intent that was used to bind to this service,
     * as given to {@link android.content.Context#bindService
     * Context.bindService}.  Note that any extras that were included with
     * the Intent at that point will <em>not</em> be seen here.
     *
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * changes the current amount of steps
     * @param sensorEvent the {@link android.hardware.SensorEvent SensorEvent}.
     */
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            totalsteps = (int) sensorEvent.values[0];
            if(previoustotalsteps==0 || previoustotalsteps>totalsteps){
                previoustotalsteps=totalsteps;
                Log.e("F",Integer.toString(totalsteps));
                //solves the problem of the first run of the programm
                //and the problem if the phone does the reboot
            }
            currentsteps = totalsteps - previoustotalsteps;
            // Update your UI or perform any necessary action
            // ...
            broadcastStepCount(currentsteps);
            // For demonstration, updating the notification text
            updateNotificationText(String.valueOf(currentsteps));
        }

    }

    /**
     *
     * @param sensor
     * @param i The new accuracy of this sensor, one of
     *         {@code SensorManager.SENSOR_STATUS_*}
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //nothing needed
    }

    /**
     * tells what to do when the service is destroyed
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
        sensorManager.unregisterListener(this);
        SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("key1", String.valueOf(previoustotalsteps));
        editor.putString("totalsteps", String.valueOf(totalsteps));
        editor.apply();
        isRunning=false;
        //todo deal with shared preference
    }

    /**
     * starts the sensor if it exists
     */
    private void setupSensors() {

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepsensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if (stepsensor == null) {
            stopSelf();
            Toast.makeText(getApplicationContext(), "No sensor available", Toast.LENGTH_SHORT).show();
        } else {

            sensorManager.registerListener(this, stepsensor, SensorManager.SENSOR_DELAY_NORMAL);
            SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);
            int savednum = Integer.parseInt(sharedPreferences.getString("totalsteps", "0"));
            previoustotalsteps = savednum;



        }
    }

    /**
     * creates notification channel
     */
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Step Counting Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            channel.setSound(null, null);
            manager.createNotificationChannel(channel);
        }
    }

    /**
     * creates the notification
     */
    private void createNotification() {
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Step counter ")
                .setContentText("Counting steps...")
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();

        startForeground(NOTIFICATION_ID, notification);
    }

    /**
     * updates the notification when the step count changes
     * @param text
     */
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

    /**
     * sends thw current step count to activity
     * @param currentSteps
     */
    private void broadcastStepCount(int currentSteps) {
        Intent intent = new Intent("StepCountUpdate");
        intent.putExtra("currentSteps", currentSteps);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    /**
     * sets the current steps to 0
     */
    public static void change_the_steps(){
        previoustotalsteps=totalsteps;
        currentsteps = totalsteps - previoustotalsteps;


    }

}




