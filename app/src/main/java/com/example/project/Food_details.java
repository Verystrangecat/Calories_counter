package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.project.Food_classes.Food;

import java.io.Serializable;

public class Food_details extends AppCompatActivity implements Serializable {
    TextView title,brand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        Food f=(Food) getIntent().getSerializableExtra("KEY_NAME");
        title=findViewById(R.id.textView_title);
        brand=findViewById(R.id.textView_brand);
        assert f != null;
        title.setText(f.getDescription());
        brand.setText(f.getBrandName());
    }
}