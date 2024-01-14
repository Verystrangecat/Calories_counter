package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class More_activity extends AppCompatActivity implements View.OnClickListener {
    BottomNavigationView bottomNavigationView;
    TextView link_to_food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        bottom_navigation();
        link_to_food=findViewById(R.id.textView22);
        link_to_food.setOnClickListener(this);
    }
    private void bottom_navigation(){
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_more);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId()==R.id.navigation_home){
                startActivity(new Intent(More_activity.this, Main_screen.class));
                finish();
                return true;}
            else if (item.getItemId()==R.id.navigation_more){
                return true;
            }
            else if (item.getItemId()==R.id.navigation_graphs){
                startActivity(new Intent(More_activity.this, Login.class));
                finish();
                return true;
            }
            else if (item.getItemId()==R.id.navigation_diary){
                startActivity(new Intent(More_activity.this,Diary_screen_tabs.class));
                finish();
                return true;
            }
            return false;
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://fdc.nal.usda.gov/"));
        startActivity(intent);

    }
}