package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Diary_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_screen);
        bottom_navigation();
    }
    private void bottom_navigation(){
        BottomNavigationView bottomNavigationView1=findViewById(R.id.bottom_navigation);
        bottomNavigationView1.setSelectedItemId(R.id.navigation_diary);
        bottomNavigationView1.setOnItemSelectedListener(item -> {
            if(item.getItemId()==R.id.navigation_home){
                startActivity(new Intent(Diary_screen.this, Main_screen.class));
                finish();
                return true;}
            else if (item.getItemId()==R.id.navigation_diary){
                return true;
            }
            else if (item.getItemId()==R.id.navigation_graphs){
                startActivity(new Intent(Diary_screen.this, Login.class));
                finish();
                return true;
            }
            else if (item.getItemId()==R.id.navigation_more){
                startActivity(new Intent(Diary_screen.this, Setup_account.class));
                finish();
                return true;
            }
            return false;
        });
    }
}