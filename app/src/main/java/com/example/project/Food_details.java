package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.project.Food_classes.Food;
import com.google.gson.Gson;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Food_details extends AppCompatActivity implements Serializable, View.OnClickListener {
    TextView title,brand, amount_portion, txt_cal, txt_carb, txt_fat, txt_pro;
    Food f;
    Dialog d;
    Button cancel, save, add;
    EditText portion;
    double cal=0, pro=0, carb=0, fat=0; //the amount for 100 gramms, doesnt change
    double cal_here=0,pro_here=0, carb_here=0, fat_here=0;


    /**
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        setup();
        gettingdetails(); //values for calories proteins etc

    }

    /**
     * connects ui to the class
     * and gets the food object
     */
    private void setup(){
        f=(Food) getIntent().getSerializableExtra("KEY_NAME");
        title=findViewById(R.id.textView_title);
        brand=findViewById(R.id.textView_brand);
        txt_cal=findViewById(R.id.textView_calorie);
        txt_carb=findViewById(R.id.textView_carbs);
        txt_fat=findViewById(R.id.textView_fats);
        txt_pro=findViewById(R.id.textView_pro);
        add=findViewById(R.id.button_add);
        add.setOnClickListener(this);
        amount_portion=findViewById(R.id.textView_size_of_the_por);
        amount_portion.setOnClickListener(this);
        assert f != null;
        title.setText(f.getDescription());
        brand.setText(f.getBrandName());

    }

    /**
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if(v==amount_portion)
        CreateDialog();
        else if(v==cancel)
            d.dismiss();
        else if(v==save){
            if(portion.getText().toString().equals(""))
                amount_portion.setText("1");
            else
             amount_portion.setText(portion.getText());
        double damount=Double.parseDouble(amount_portion.getText().toString());
            cal_here=Onedigit(cal*damount);
            carb_here=Onedigit(carb*damount);
            pro_here=Onedigit(pro*damount);
            fat_here=Onedigit(fat*damount);
            txt_cal.setText(String.valueOf(cal_here));
        txt_carb.setText(String.valueOf(carb_here));
        txt_pro.setText(String.valueOf(pro_here));
        txt_fat.setText(String.valueOf(fat_here));

        d.dismiss();

        } else if (v==add)
        {//changes in amount of elements left
            SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            double calori = Double.parseDouble(sharedPreferences.getString(getString(R.string.amount_calories_left), "0"));//Скорее всего неправильный стринг
            double protein=Double.parseDouble(sharedPreferences.getString(getString(R.string.amount_proteins_left),"0"));
            double fats=Double.parseDouble(sharedPreferences.getString(getString(R.string.amount_fats_left),"0"));
            double carbs=Double.parseDouble(sharedPreferences.getString(getString(R.string.amount_carbs_left),"0"));
            calori=calori-cal_here;
            protein=protein-pro_here;
            fats=fats-fat_here;
            carbs=carbs-carb_here;
            editor.putString(getString(R.string.amount_calories_left),String.valueOf(Onedigit(calori)));
            editor.putString(getString(R.string.amount_proteins_left),String.valueOf(Onedigit(protein)));
            editor.putString(getString(R.string.amount_fats_left),String.valueOf(Onedigit(fats)));
            editor.putString(getString(R.string.amount_carbs_left),String.valueOf(Onedigit(carbs)));
            editor.apply();
            saving_object();
              startActivity(new Intent(Food_details.this,Main_screen.class));
            finish();



        }


    }

    /**
     * creates the dialog that gets the new amount of portions
     */
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

    /**
     * getting the de about the food from the object and setting it text views
     */
    private void gettingdetails() {
        for (int i = 0; i < f.getFoodNutrients().size() ; i++) {
            if (f.getFoodNutrients().get(i).getNutrientID() == 1008) {
                txt_cal.setText(String.valueOf(f.getFoodNutrients().get(i).getValue()));
                cal=f.getFoodNutrients().get(i).getValue();
            } else if (f.getFoodNutrients().get(i).getNutrientID() == 1003) {
                txt_pro.setText(String.valueOf(f.getFoodNutrients().get(i).getValue()));
                pro=f.getFoodNutrients().get(i).getValue();
            }   else if (f.getFoodNutrients().get(i).getNutrientID() == 1005) {
                txt_carb.setText(String.valueOf(f.getFoodNutrients().get(i).getValue()));
                carb=f.getFoodNutrients().get(i).getValue();
            } else if (f.getFoodNutrients().get(i).getNutrientID() == 1004) {
            txt_fat.setText(String.valueOf(f.getFoodNutrients().get(i).getValue()));
                fat=f.getFoodNutrients().get(i).getValue();
            }
        }
        cal_here=cal;
        pro_here=pro;
        fat_here=fat;
        carb_here=carb;
        if (txt_cal.getText().toString().equals("TextView"))
            txt_cal.setText("No information");
        if (txt_carb.getText().toString().equals("TextView"))
            txt_carb.setText("No information");
        if (txt_pro.getText().toString().equals("TextView"))
            txt_pro.setText("No information");
        if (txt_fat.getText().toString().equals("TextView"))
            txt_fat.setText("No information");

        }


    /**
     * saving the food object to the array with eaten food
     */

            public void saving_object(){
                SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Food_class_meals food_class_meals=new Food_class_meals(txt_cal.getText().toString(), txt_pro.getText().toString(),
                        txt_fat.getText().toString(),txt_carb.getText().toString(),amount_portion.getText().toString(),brand.getText().toString(),title.getText().toString(),
                        sharedPreferences.getString("Meal","b"));

                Gson gson2 = new Gson();
                String json2 = sharedPreferences.getString(getString(R.string.array_meals), "");
                    Array_class obj = gson2.fromJson(json2, Array_class.class);
                    obj.arrayList.add(food_class_meals);
                    Gson gson = new Gson();
                    String json = gson.toJson(obj);
                    editor.putString(getString(R.string.array_meals), json);
                    editor.apply();


            }

    /**
     * leaves only one digit in the decimal
     * @param val
     * @return number double
     */
    public double Onedigit(double val){
        String vals=String.valueOf(val);
        String n="";
        while(vals.charAt(0)!='.'){
            n=n+vals.charAt(0);
            vals= vals.substring(1);
        }
        n=n+vals.substring(0,2);
        val=Double.parseDouble(n);
        Log.e("I", n);
        return val;
    }
    }