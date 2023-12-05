package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.project.Food_classes.Food;

import java.io.Serializable;

public class Food_details extends AppCompatActivity implements Serializable, View.OnClickListener {
    TextView title,brand, amount_portion;
    Food f;
    Dialog d;
    Button cancel, save;
    EditText portion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        setup();
    }
    private void setup(){
        f=(Food) getIntent().getSerializableExtra("KEY_NAME");
        title=findViewById(R.id.textView_title);
        brand=findViewById(R.id.textView_brand);
        amount_portion=findViewById(R.id.textView_size_of_the_por);
        amount_portion.setOnClickListener(this);
        assert f != null;
        title.setText(f.getDescription());
        brand.setText(f.getBrandName());

    }

    @Override
    public void onClick(View v) {
        if(v==amount_portion)
        CreateDialog();
        else if(v==cancel)
            d.dismiss();
        else if(v==save){
        amount_portion.setText(portion.getText());
        d.dismiss();
        //Todo affect the amount of everything in the food_details

        }


    }

    private void CreateDialog() {
        d = new Dialog(this);
        d.setContentView(R.layout.custom_dialog);
        d.setTitle("Amount of portions");
        d.setCancelable(true);
        portion = d.findViewById(R.id.editText_portion);
        cancel=d.findViewById(R.id.button_cancel);
        save=d.findViewById(R.id.button_Save);
        cancel.setOnClickListener(this);
        save.setOnClickListener(this);
        d.show();
    }
}