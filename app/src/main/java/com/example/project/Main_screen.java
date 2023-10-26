package com.example.project;

import android.content.Context;

import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
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
        setupUI();
        resetSteps();
        loadData();
//todo:pop up asking for a users permission



    }
    private void setupUI(){
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

    protected void resetSteps(){
        showsteps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Main_screen.this, "Long press to resert",Toast.LENGTH_SHORT).show();
            }
        });
        showsteps.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                previoustotalsteps=totalsteps;
                showsteps.setText("0");
                progressBar.setProgress(0);
                saveData();
                return true;
            }
        });
    }

    private void saveData() {
        SharedPreferences sharedPreferences=getSharedPreferences("my pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("key1", String.valueOf(previoustotalsteps))  ;
        editor.apply();
    }
    private void loadData(){
        SharedPreferences sharedPreferences=getSharedPreferences("my pref", Context.MODE_PRIVATE);
        int savednum= (int) sharedPreferences.getFloat("key1",0f);
        previoustotalsteps=savednum;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}