package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Graphs extends AppCompatActivity {
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);
        bottom_navigation();
        setupUI();
        pieChart();


    }

    private void pieChart() {
        double br=0, l=0,d=0,s=0;
        ArrayList<Food_class_meals> arrayList;
        ArrayList<PieEntry> calories=new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);
        Gson gson2 = new Gson();
        String json2 = sharedPreferences.getString("MyObject", "");
        Array_class obj = gson2.fromJson(json2, Array_class.class);
       if(obj!=null){
           arrayList=obj.arrayList;
           for(int i=0; i<arrayList.size(); i++){
               if(arrayList.get(i).meal.equals("b")){
                   br=br+Double.parseDouble(arrayList.get(i).calories);
               }
               else if(arrayList.get(i).meal.equals("l")){
                   l=l+Double.parseDouble(arrayList.get(i).calories);
               }
              else if(arrayList.get(i).meal.equals("d")){
                   d=d+Double.parseDouble(arrayList.get(i).calories);
               }
              else
                   s=s+Double.parseDouble(arrayList.get(i).calories);
           }
           br=Math.round(br);
           l=Math.round(l);
           d=Math.round(d);
           s=Math.round(s);

           calories.add(new PieEntry((int)br, "Breakfast"));
           calories.add(new PieEntry((int)l,"Lunch"));
           calories.add(new PieEntry((int)d,"Dinner"));
           calories.add(new PieEntry((int)s,"Snacks"));
           PieDataSet pieDataSet=new PieDataSet(calories,"Calories per meal");
           pieDataSet.setColors(ColorTemplate.PASTEL_COLORS);
// todo:play with colors
           PieData pieData=new PieData(pieDataSet);
           pieChart.setData(pieData);
           pieChart.getDescription().setEnabled(false);
           pieChart.setCenterText("Calories per meal");
           pieChart.animate();



    }

    }

    private void setupUI() {
        pieChart=findViewById(R.id.pieChart);


    }
    private void bottom_navigation() {
        BottomNavigationView bottomNavigationView1 = findViewById(R.id.bottom_navigation);
        bottomNavigationView1.setSelectedItemId(R.id.navigation_graphs);
        bottomNavigationView1.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_graphs)
                return true;
            else if (item.getItemId() == R.id.navigation_diary) {
                startActivity(new Intent(Graphs.this, Diary_screen_tabs.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.navigation_home) {
                startActivity(new Intent(Graphs.this, Main_screen.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.navigation_more) {
                startActivity(new Intent(Graphs.this, More_activity.class));
                finish();
                return true;
            }
            return false;
        });
    }
}