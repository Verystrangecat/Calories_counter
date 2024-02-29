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

        //todo check if the alrammanager triggered only if the sensor exsists
    }
    public void save_steps(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("my pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String dateprevious = sharedPreferences.getString("date", "");
        Gson gson2 = new Gson();
        String json2 = sharedPreferences.getString("Array_steps", "");
        Array_class_steps obj = gson2.fromJson(json2, Array_class_steps.class);

        if (obj == null) {
            obj = new Array_class_steps(); // Create a new object if it doesn't exist
        }

        // Add the current date to the date variable
        double result=Double.parseDouble(dateprevious);
        obj.addday(Step_Counter_Service.currentsteps, result);
        String json = gson2.toJson(obj);
        editor.putString("Array_steps", json);
        editor.apply();
    }
    //the date from shared preference
    //todo check that the date is right
}//String datenow = DateFormat.format("dd-MM-yyyy", cal).toString();
