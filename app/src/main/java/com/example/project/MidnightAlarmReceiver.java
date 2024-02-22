package com.example.project;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.project.utils.Step_Counter_Service;

public class MidnightAlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(Step_Counter_Service.isRunning){
            Log.e("L", "GOT TO HERE");
            Step_Counter_Service.change_the_steps();
            Log.e("S",String.valueOf(Step_Counter_Service.currentsteps));

        }
        else {
            Intent serviceIntent = new Intent(context, Step_Counter_Service.class);
            context.startService(serviceIntent);
            Step_Counter_Service.change_the_steps();

        }
        //todo check if the alrammanager triggered only if the sensor exsists
    }
}
