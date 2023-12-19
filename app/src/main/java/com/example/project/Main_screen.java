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
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;


public class Main_screen extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager = null;
    private Sensor stepsensor;
    private int totalsteps = 0;
    private int previoustotalsteps = 0;
    private ProgressBar progressBar;
    private TextView showsteps;
    private MenuItem main, diary, more;

    private NavigationBarView bottomNavigationView;
    ViewPager2 viewPager2;
    ArrayList<ViewpagerItem> arrayList;


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
        viewPager2=findViewById(R.id.viewpager);
        arrayList=new ArrayList<>();
        //My_information myInformation=(My_information) getApplication();
//        arrayList.add(new ViewpagerItem("Calories",myInformation.getCalories(),myInformation.getCal_left(),R.drawable.circle_calorie));
//        arrayList.add(new ViewpagerItem("Carbs",myInformation.getCarbs(),myInformation.getCarb_left(),R.drawable.circle_carbs));
//        arrayList.add(new ViewpagerItem("Fats",myInformation.getFats(),myInformation.getFats_left(),R.drawable.circle_fats));
//        arrayList.add(new ViewpagerItem("Proteins",myInformation.getProteins(),myInformation.getProtein_left(),R.drawable.circle_proteins));
        arrayList.add(new ViewpagerItem("Calories",1200,200,R.drawable.circle_calorie));
        arrayList.add(new ViewpagerItem("Carbs",12,10,R.drawable.circle_carbs));
        arrayList.add(new ViewpagerItem("Fats",23,23,R.drawable.circle_fats));
        arrayList.add(new ViewpagerItem("Proteins",15,13,R.drawable.circle_proteins));
        //Todo figure out the way to get and pass the information, preferebaly without sharedpreference
        Vp_adapter vp_adapter=new Vp_adapter(arrayList);
        viewPager2.setAdapter(vp_adapter);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(2);
        viewPager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

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

    //todo make everything reset at midnight

}






