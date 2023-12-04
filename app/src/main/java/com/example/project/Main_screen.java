package com.example.project;

import static com.google.android.material.bottomnavigation.BottomNavigationView.*;

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

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class Main_screen extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager = null;
    private Sensor stepsensor;
    private int totalsteps = 0;
    private int previoustotalsteps = 0;
    private ProgressBar progressBar;
    private TextView showsteps;
    private MenuItem main, diary, more;

    private NavigationBarView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        setupUI();
        resetSteps();
        loadData();
        bottom_navigation();
//todo:pop up asking for a users permission


    }

    private void setupUI() {
        progressBar = findViewById(R.id.progressBar);
        showsteps = findViewById(R.id.txt_steps);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        stepsensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

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
        int savednum = Integer.parseInt(sharedPreferences.getString("key1","0"));
        previoustotalsteps = savednum;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    private void bottom_navigation(){
        BottomNavigationView bottomNavigationView1=findViewById(R.id.bottom_navigation);
        bottomNavigationView1.setSelectedItemId(R.id.navigation_home);
        bottomNavigationView1.setOnItemSelectedListener(item -> {
            if(item.getItemId()==R.id.navigation_home)
                return true;
            else if (item.getItemId()==R.id.navigation_diary){
                startActivity(new Intent(Main_screen.this, Diary_screen.class));
                finish();
                return true;
            }
            else if (item.getItemId()==R.id.navigation_graphs){
                startActivity(new Intent(Main_screen.this, Login.class));
                finish();
                return true;
            }
            else if (item.getItemId()==R.id.navigation_more){
                startActivity(new Intent(Main_screen.this, More_activity.class));
                finish();
                return true;
            }
            return false;
        });
    }
//Todo add the bottom navigation to other screens (tutorial:https://www.youtube.com/watch?v=MUl19ppdu0o&t=884s)
    //todo find out why I dont see the number of steps
}






