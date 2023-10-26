package com.example.project;

import android.content.Context;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


public class Main_screen extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager=null;
    private Sensor stepsensor;
    private int totalsteps=0;
    private int previoustotalsteps=0;
    private ProgressBar progressBar;
    private TextView showsteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        progressBar=findViewById(R.id.progressBar);
        showsteps=findViewById(R.id.txt_steps);
        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        stepsensor=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);




    }
    protected void onResume(){
        super.onResume();
        if(stepsensor==null){
            Toast.makeText(this,"NO SENSOR",Toast.LENGTH_SHORT).show();
        }else {
            sensorManager.registerListener(this,stepsensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType()==Sensor.TYPE_STEP_COUNTER) {
            totalsteps = (int) sensorEvent.values[0];
            int currentsteps=totalsteps-previoustotalsteps;
            showsteps.setText(String.valueOf(currentsteps));

            progressBar.setProgress(currentsteps);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}