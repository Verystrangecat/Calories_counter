package com.example.project.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

public class ScheduleWork {
    public static void scheduleAlarm(Context context) {
        // Calculate the time until 23:59
        long currentTimeMillis = System.currentTimeMillis();
        long midnightMillis = calculateMidnightMillis(currentTimeMillis);

        // Create an Intent for the BroadcastReceiver
        Intent intent = new Intent(context, Alarm_Receiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        // Schedule the alarm with AlarmManager
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, midnightMillis, pendingIntent);
    }

    public static void enqueueOneTimeWorkRequest(Context context) {
        // Enqueue the OneTimeWorkRequest with WorkManager
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
                .build();

        WorkManager.getInstance(context).enqueue(workRequest);
    }

    private static long calculateMidnightMillis(long currentTimeMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTimeMillis);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTimeInMillis();


    }
    //todo change the time to midnight
}
/*
1) sets the alarm for the time
2)wakes the alarm receiver
3)creates work request
4)goes to the cass work to figure out what to do
 */
