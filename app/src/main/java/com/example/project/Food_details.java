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
import java.text.DecimalFormat;

public class Food_details extends AppCompatActivity implements Serializable, View.OnClickListener {
    TextView title,brand, amount_portion, txt_cal, txt_carb, txt_fat, txt_pro;
    Food f;
    Dialog d;
    Button cancel, save;
    EditText portion;
    double cal=0, pro=0, carb=0, fat=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        setup();
        gettingdetails();
        gettngnumbers();

    }


    private void setup(){
        f=(Food) getIntent().getSerializableExtra("KEY_NAME");
        title=findViewById(R.id.textView_title);
        brand=findViewById(R.id.textView_brand);
        txt_cal=findViewById(R.id.textView_calorie);
        txt_carb=findViewById(R.id.textView_carbs);
        txt_fat=findViewById(R.id.textView_fats);
        txt_pro=findViewById(R.id.textView_pro);
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
            if(portion.getText().equals(""))
                amount_portion.setText("1");
            else
             amount_portion.setText(portion.getText());
        double damount=Double.valueOf(amount_portion.getText().toString());
        txt_cal.setText(new DecimalFormat("##.#").format(cal*damount));
        txt_carb.setText(new DecimalFormat("##.#").format(carb*damount));
        txt_pro.setText(new DecimalFormat("##.#").format(pro*damount));
        txt_fat.setText(new DecimalFormat("##.#").format(fat*damount));
        d.dismiss();
        //Todo the field wth all the infromaton

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
    private void gettingdetails() {
        for (int i = 0; i < f.getFoodNutrients().size() ; i++) {
            if (f.getFoodNutrients().get(i).getNutrientID() == 1008) {
                txt_cal.setText(String.valueOf(f.getFoodNutrients().get(i).getValue()));
            } else if (f.getFoodNutrients().get(i).getNutrientID() == 1003) {
                txt_pro.setText(String.valueOf(f.getFoodNutrients().get(i).getValue()));

            }   else if (f.getFoodNutrients().get(i).getNutrientID() == 1005) {
            txt_carb.setText(String.valueOf(f.getFoodNutrients().get(i).getValue()));
            } else if (f.getFoodNutrients().get(i).getNutrientID() == 1004) {
            txt_fat.setText(String.valueOf(f.getFoodNutrients().get(i).getValue()));
            }
        }
        if (txt_cal.getText().toString().equals("TextView"))
            txt_cal.setText("No information");
        if (txt_carb.getText().toString().equals("TextView"))
            txt_carb.setText("No information");
        if (txt_pro.getText().toString().equals("TextView"))
            txt_pro.setText("No information");
        if (txt_fat.getText().toString().equals("TextView"))
            txt_fat.setText("No information");

        }
        public void gettngnumbers(){
            for (int i=0; i<f.getFoodNutrients().size(); i++){
                if(f.getFoodNutrients().get(i).getNutrientID()==1008){
                    cal=f.getFoodNutrients().get(i).getValue();
                } else if (f.getFoodNutrients().get(i).getNutrientID()==1003) {
                    pro=f.getFoodNutrients().get(i).getValue();
                }
                else if (f.getFoodNutrients().get(i).getNutrientID()==1005) {
                    carb=f.getFoodNutrients().get(i).getValue();
                }
                else if (f.getFoodNutrients().get(i).getNutrientID()==1004) {
                    fat=f.getFoodNutrients().get(i).getValue();
                }

                }

            }
    }