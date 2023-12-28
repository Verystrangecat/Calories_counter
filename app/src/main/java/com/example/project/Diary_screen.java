package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.project.databinding.ActivityMainScreenBinding;
import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Diary_screen extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_screen);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new Fragment_diary()).commit();
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottom_navigation();
        replace_fragment(new Fragment_breakfast());

    }
    private void bottom_navigation(){

        bottomNavigationView.setSelectedItemId(R.id.navigation_diary);
        bottomNavigationView.setOnItemSelectedListener(item -> {
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
                startActivity(new Intent(Diary_screen.this, More_activity.class));
                finish();
                return true;
            }
            return false;
        });
    }
    private void replace_fragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
        //Todo add the history of foods
        //use this https://stackoverflow.com/questions/7145606/how-do-you-save-store-objects-in-sharedpreferences-on-android
//Todo fix the problem with 2 dairy screens
    }
}