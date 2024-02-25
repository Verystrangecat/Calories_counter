package com.example.project;

import android.Manifest;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;

import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.project.utils.Step_Counter_Service;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class Main_screen extends AppCompatActivity {

    private int totalsteps = 0;
    private int previoustotalsteps = 0;
    private ProgressBar progressBar;
    private TextView showsteps;
    BarChart barChart;

    ViewPager2 viewPager2;
    ArrayList<ViewpagerItem> arrayList;
    private static final String string_permission= Manifest.permission.ACTIVITY_RECOGNITION;
    private static final int permission_code=200;
    private BroadcastReceiver stepCountReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null && intent.getAction().equals("StepCountUpdate")) {
                int currentSteps = intent.getIntExtra("currentSteps", 0);
                showsteps.setText(String.valueOf(currentSteps));
                progressBar.setProgress(currentSteps);
            } else {
                SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);
                int savednum = Integer.parseInt(sharedPreferences.getString("key1", "0"));
                previoustotalsteps = savednum;
                totalsteps = Integer.parseInt(sharedPreferences.getString("totalsteps", "0"));
                showsteps.setText(String.valueOf(totalsteps - previoustotalsteps));
                progressBar.setProgress(totalsteps - previoustotalsteps);
            }
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        setupUI();
        getpermissionforcativity();// if gets the pemission then starts the service
        bottom_navigation();
        informationchanged();//changes the amount of ecerything left if the day changed
        setViewPager2();
        setBar_Chart();
        scheduleMidnightAlarm();




    }

    private void informationchanged() {
        long time=System.currentTimeMillis();
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String datenow = DateFormat.format("dd-MM-yyyy", cal).toString();
        SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        String dateprev=sharedPreferences.getString("date", datenow);
        if(!dateprev.equals(datenow)){
            editor.putString("date", datenow);
            editor.putString("amount_calories_left", sharedPreferences.getString("amount_calories","0"));
            editor.putString("amount_proteins_left",sharedPreferences.getString("amount_proteins","0"));
            editor.putString("amount_fats_left",sharedPreferences.getString("amount_fats","0"));
            editor.putString("amount_carbs_left",sharedPreferences.getString("amount_carbs","0"));
            Gson gson2 = new Gson();
            String json2 = sharedPreferences.getString("MyObject", "");
            Array_class obj = gson2.fromJson(json2, Array_class.class);
            if(obj!=null){
            obj.arrayList.clear();
            Gson gson = new Gson();
            String json = gson.toJson(obj);
            editor.putString("MyObject", json);}
            editor.apply();
        }
    }

    private void setupUI() {
        progressBar = findViewById(R.id.progressBar);
        showsteps = findViewById(R.id.txt_steps);
        barChart=findViewById(R.id.barchart);


        viewPager2 = findViewById(R.id.viewpager);
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(stepCountReceiver, new IntentFilter("StepCountUpdate"));


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(stepCountReceiver);
    }

    //checking the permission for activity and asking for it as well as activating the sensor
    public void getpermissionforcativity(){
        if (ActivityCompat.checkSelfPermission(this, string_permission) == PackageManager.PERMISSION_GRANTED){
            if(!isServiceRunning(Step_Counter_Service.class)){
            Intent serviceIntent = new Intent(this, Step_Counter_Service.class);
            startService(serviceIntent);}

        }
        else if (ActivityCompat.shouldShowRequestPermissionRationale(this, string_permission)) {// Show rationale if permission was denied before// This is the case where the user denied the permission previously, but did not check "Don't ask again."
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Let the app count your steps, it will help you on your journey")
                    .setTitle("Permission required")
                    .setCancelable(false)
                    .setPositiveButton("Allow", (dialogInterface, i) -> {
                        ActivityCompat.requestPermissions(Main_screen.this, new String[]{string_permission}, permission_code);
                        dialogInterface.dismiss();
                    })
                    .setNegativeButton("Forbid", (dialogInterface, i) -> dialogInterface.dismiss());
            builder.show();}
        else
        // Request the permission for the first time
         ActivityCompat.requestPermissions(this, new String[]{string_permission}, permission_code);

    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == permission_code) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if(!isServiceRunning(Step_Counter_Service.class)){
                    Intent serviceIntent = new Intent(this, Step_Counter_Service.class);
                    startService(serviceIntent);}
            } else if (shouldShowRequestPermissionRationale(string_permission)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
               builder.setMessage("Notifications will help to improve yourself")
                      .setTitle("Permission needed")     .setCancelable(false)     .setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss())
                        .setPositiveButton("Settings", (dialogInterface, i) -> {
                           Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                            intent.setData(uri);
                            startActivity(intent);
                            dialogInterface.dismiss();
                        });
                builder.show();
           }
        }
    }




// todo deal with the step counter







//items are in the menu
    private void bottom_navigation() {
        BottomNavigationView bottomNavigationView1 = findViewById(R.id.bottom_navigation);
        bottomNavigationView1.setSelectedItemId(R.id.navigation_home);
        bottomNavigationView1.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_home)
                return true;
            else if (item.getItemId() == R.id.navigation_diary) {
                startActivity(new Intent(Main_screen.this, Diary_screen_tabs.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.navigation_graphs) {
                startActivity(new Intent(Main_screen.this, Graphs.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.navigation_more) {
                startActivity(new Intent(Main_screen.this, More_activity.class));
                finish();
                return true;
            }
            return false;
        });
    }

    public void setViewPager2() {
        arrayList = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);
        arrayList.add(new ViewpagerItem("Calories", sharedPreferences.getString("amount_calories", "0"),
                sharedPreferences.getString("amount_calories_left", sharedPreferences.getString("amount_calories", "0")), R.drawable.circle_calorie));
        arrayList.add(new ViewpagerItem("Carbs", sharedPreferences.getString("amount_carbs", "0"),
                sharedPreferences.getString("amount_carbs_left", sharedPreferences.getString("amount_carbs", "0")), R.drawable.circle_carbs));
        arrayList.add(new ViewpagerItem("Fats", sharedPreferences.getString("amount_fats", "0"),
                sharedPreferences.getString("amount_fats_left", sharedPreferences.getString("amount_fats", "0")), R.drawable.circle_fats));
        arrayList.add(new ViewpagerItem("Proteins", sharedPreferences.getString("amount_proteins", "0"),
                sharedPreferences.getString("amount_proteins_left", sharedPreferences.getString("amount_proteins", "0")), R.drawable.circle_proteins));
        Vp_adapter vp_adapter = new Vp_adapter(arrayList);
        viewPager2.setAdapter(vp_adapter);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(2);
        viewPager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
    }






        //todo make everything reset at midnight if works with calories do with steps

    public boolean isServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
    private void scheduleMidnightAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent alarmIntent = new Intent(this, MidnightAlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this,
                123456,
                alarmIntent,
                PendingIntent.FLAG_IMMUTABLE
        );

        // Set the alarm to trigger at midnight and repeat every 24 hours
        alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                getMidnight().getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,
                pendingIntent
        );
    }

    private void cancelMidnightAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent alarmIntent = new Intent(this, MidnightAlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this,
                88,
                alarmIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        // Cancel the alarm
        alarmManager.cancel(pendingIntent);
    }

    private Calendar getMidnight() {
        Calendar midnight = Calendar.getInstance();
        midnight.setTimeInMillis(System.currentTimeMillis());
        midnight.set(Calendar.HOUR_OF_DAY, 0);
        midnight.set(Calendar.MINUTE, 0);
        midnight.set(Calendar.SECOND, 0);
        midnight.set(Calendar.MILLISECOND, 0);

        // If the current time is already past midnight, schedule for the next day
        if (midnight.before(Calendar.getInstance())) {
            midnight.add(Calendar.DAY_OF_MONTH, 1);
        }

        return midnight;
    }
    public void setBar_Chart(){
        SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        Gson gson2 = new Gson();
        String json2 = sharedPreferences.getString("Array_steps", "");
        Array_class_steps obj = gson2.fromJson(json2, Array_class_steps.class);

        if(obj!=null){
            ArrayList<Step> steps=obj.getArrayList();
            ArrayList<BarEntry> steps_per_day=new ArrayList<>();
            for(int i=0; i<steps.size(); i++){
                steps_per_day.add(new BarEntry((float) steps.get(i).getDate(),steps.get(i).getAmount_steps()));
            }
            BarDataSet dataSet=new BarDataSet(steps_per_day, "Your steps");
            dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            dataSet.setValueTextColor(Color.BLACK);
            dataSet.setValueTextSize(16f);
            BarData barData=new BarData(dataSet);
            barChart.setFitBars(true);
            barChart.setData(barData);
            barChart.getDescription().setText("Empty space");
            barChart.animateY(2000);
        }


    }

    }
//Todo add the check if alarm is already set
//todo start the alarm inside the service
//todo circular_menu
//Todo add the new class to tik proect and show the changes at step_counter_service
//Todo change the splashscreen




