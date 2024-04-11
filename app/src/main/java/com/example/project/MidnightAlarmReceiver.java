package com.example.project;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.format.DateFormat;
import android.util.Log;

import com.example.project.utils.Step_Counter_Service;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MidnightAlarmReceiver extends BroadcastReceiver {
    /**
     *
     * @param context The Context in which the receiver is running.
     * @param intent The Intent being received.
     *               sets up the amount of current steps to 0
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        if(Step_Counter_Service.isRunning){
            Log.e("L", "GOT TO HERE");
            save_steps(context);
            Step_Counter_Service.change_the_steps();
            Log.e("S",String.valueOf(Step_Counter_Service.currentsteps));

        }
        else {
            Intent serviceIntent = new Intent(context, Step_Counter_Service.class);
            context.startService(serviceIntent);
            save_steps(context);
            Step_Counter_Service.change_the_steps();

        }


    }

    /**
     *
     * @param context
     * before setting current steps to 0 saves tge steps to the array
     */
    public void save_steps(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("my pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson2 = new Gson();
        String json2 = sharedPreferences.getString(context.getString(R.string.array_steps), "");
        Array_class_steps obj = gson2.fromJson(json2, Array_class_steps.class);

        if (obj == null) {
            obj = new Array_class_steps(); // Create a new object if it doesn't exist
        }
        // Add the current date to the date variable
        obj.addday(Step_Counter_Service.currentsteps);
        String json = gson2.toJson(obj);
        editor.putString(context.getString(R.string.array_steps), json);
        editor.apply();

    }
    //the date from shared preference
    //todo check that the date is right
}//String datenow = DateFormat.format("dd-MM-yyyy", cal).toString();
