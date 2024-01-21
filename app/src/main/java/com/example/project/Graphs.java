package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Graphs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);
        bottom_navigation();


    }
    private void setupUI() {

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