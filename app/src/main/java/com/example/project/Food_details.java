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
import android.widget.Toast;

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
    double cal=0, pro=0, carb=0, fat=0;
    double cal_here=0,pro_here=0, carb_here=0, fat_here=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        setup();
        gettingdetails();
        gettngnumbers();
        settingdeafultfor_left();

    }


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
//        cal_here=Double.valueOf(new DecimalFormat("##.#").format(cal*damount));
//        pro_here=Double.valueOf(new DecimalFormat("##.#").format(pro*damount));
//        carb_here=Double.valueOf(new DecimalFormat("##.#").format(carb*damount));
//        fat_here=Double.valueOf(new DecimalFormat("##.#").format(fat*damount));
            cal_here=cal*damount;
            carb_here=carb*damount;
            pro_here=pro*damount;
            fat_here=fat*damount;
            txt_cal.setText(new DecimalFormat("##.#").format(cal*damount));
        txt_carb.setText(new DecimalFormat("##.#").format(carb*damount));
        txt_pro.setText(new DecimalFormat("##.#").format(pro*damount));
        txt_fat.setText(new DecimalFormat("##.#").format(fat*damount));
        String s=new DecimalFormat("##.#").format(23.555);
        d.dismiss();

        } else if (v==add)
        {
            SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            //todo get the calories left then to number - cal_here and back to sharedpreference

            double calori = Double.valueOf(sharedPreferences.getString("amount_calories_left", "0"));//Скорее всего неправильный стринг
            double protein=Double.valueOf(sharedPreferences.getString("amount_proteins_left","0"));
            double fats=Double.valueOf(sharedPreferences.getString("amount_fats_left","0"));
            double carbs=Double.valueOf(sharedPreferences.getString("amount_carbs_left","0"));
            calori=calori-cal_here;
            protein=protein-pro_here;
            fats=fats-fat_here;
            carbs=carbs-carb_here;
            Toast.makeText(this, String.valueOf(calori), Toast.LENGTH_SHORT);
            editor.putString("amount_calories_left",new DecimalFormat("##.#").format(calori));
            editor.putString("amount_proteins_left",new DecimalFormat("##.#").format(protein));
            editor.putString("amount_fats_left",new DecimalFormat("##.#").format(fats));
            editor.putString("amount_carbs_left",new DecimalFormat("##.#").format(carbs));
            editor.apply();

              startActivity(new Intent(Food_details.this, Diary_screen.class));
            finish();



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
            public void settingdeafultfor_left(){
        cal_here=Double.parseDouble(txt_cal.getText().toString());
                pro_here=Double.parseDouble(txt_pro.getText().toString());
                fat_here=Double.parseDouble(txt_fat.getText().toString());
                carb_here=Double.parseDouble(txt_carb.getText().toString());

            }
            public void saving_object(String portion){
                Food_class_meals food_class_meals=new Food_class_meals(txt_cal.getText().toString(), txt_pro.getText().toString(),
                        txt_fat.getText().toString(),txt_carb.getText().toString(),amount_portion.getText().toString(),brand.getText().toString(),title.getText().toString());
                SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson2 = new Gson();
                String json2 = sharedPreferences.getString("MyObject", "");
                Array_class obj = gson2.fromJson(json2, Array_class.class);
                obj.arrayList.add(food_class_meals);
                Gson gson = new Gson();
                String json = gson.toJson(obj);
                editor.putString("MyObject", json);
                editor.commit();


            }
    }