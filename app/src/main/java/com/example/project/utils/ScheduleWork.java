package com.example.project.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

public class ScheduleWork {
    public static void scheduleAlarm(Context context) {
        // Create an Intent for the BroadcastReceiver
        if (isAlarmSet(context)) {
            // Alarm is already set, no need to set it again
            return;
        }
        Intent intent = new Intent(context, Alarm_Receiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 123, intent, PendingIntent.FLAG_IMMUTABLE);

        // Get the existing alarm and cancel it
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);

        // Set the alarm to trigger at midnight
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 11);
        calendar.set(Calendar.MINUTE, 15);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        long midnightMillis = calendar.getTimeInMillis();

        alarmManager.set(AlarmManager.RTC_WAKEUP, midnightMillis, pendingIntent);
        setAlarmStatus(context, true);
    }


    public static void enqueueOneTimeWorkRequest(Context context) {
        // Enqueue the OneTimeWorkRequest with WorkManager
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
                .build();

        WorkManager.getInstance(context).enqueue(workRequest);
    }


    // Method to check if the alarm is already set
    private static boolean isAlarmSet(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("alarm_prefs", Context.MODE_PRIVATE);
        return preferences.getBoolean("is_alarm_set", false);
    }

    // Method to update the alarm status
    private static void setAlarmStatus(Context context, boolean isAlarmSet) {
        SharedPreferences preferences = context.getSharedPreferences("alarm_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("is_alarm_set", isAlarmSet);
        editor.apply();
    }
}
//todo if the changes worked save them  (check in the morning)
/*
1) sets the alarm for the time
2)wakes the alarm receiver
3)creates work request
4)goes to the cass work to figure out what to do
 */
