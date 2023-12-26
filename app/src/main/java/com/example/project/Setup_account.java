package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Setup_account extends AppCompatActivity {
    EditText txt_height, txt_weight, txt_age;
    SeekBar bar_height, bar_weight;
    RadioButton ser, la, ma, va, sa, notpreg, firsttri, secondtri, thirdtri, lactating;

    boolean isFemale = true, wasClicked = false, nextscreen = false;
    double activity_level_calorie = 0, level_protein = 0;
    int additional_calories = 0;
    int num_age, num_height, num_weight;
     static double calories=0, proteins=0, fats=0, carbohydrates=0;

    Button submit, female, male;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_account);
        setupUi();
        Seekbars();
        male_or_female();
        submit_button();



        }

    private void final_result() {
        if(isFemale)
            calories=Math.round((447.593 + (9.247 *num_weight) + (3.098 * num_height) - (4.330*num_age))*activity_level_calorie+additional_calories);
        else
            calories= Math.round((88.362 + (13.397 *num_weight) + (4.799 * num_height) - (5.677 *num_age))*activity_level_calorie);
        proteins= Math.round(num_weight*level_protein);
        fats=Math.round((calories*0.27)/9);
        carbohydrates=Math.round(calories*0.55/4);
        proteins=Onedigit(proteins);
        fats=Onedigit(fats);
        carbohydrates=Onedigit(carbohydrates);
        SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("amount_calories",String.valueOf(calories));
        editor.putString("amount_proteins",String.valueOf(proteins));
        editor.putString("amount_carbs",String.valueOf(carbohydrates));
        editor.putString("amount_fats",String.valueOf(fats));
        editor.putString("amount_calories_left",String.valueOf(calories));
        editor.putString("amount_proteins_left",String.valueOf(proteins));
        editor.putString("amount_carbs_left",String.valueOf(carbohydrates));
        editor.putString("amount_fats_left",String.valueOf(fats));
        editor.apply();

    }


    private void male_or_female() {
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notpreg.setEnabled(true);
                firsttri.setEnabled(true);
                secondtri.setEnabled(true);
                thirdtri.setEnabled(true);
                lactating.setEnabled(true);
                isFemale = true;
                wasClicked = true;


            }
        });
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notpreg.setEnabled(false);
                firsttri.setEnabled(false);
                secondtri.setEnabled(false);
                thirdtri.setEnabled(false);
                lactating.setEnabled(false);
                isFemale = false;
                wasClicked = true;


            }
        });
    }
    private void check_male_or_female(){
        if(nextscreen){
            if(!wasClicked){
                nextscreen=false;
                Toast.makeText(this,"Please choose your sex",Toast.LENGTH_SHORT).show();

            }
        }

    }

    private void checkactivity_level() {
        if (nextscreen) {
            if (ser.isChecked()) {
                activity_level_calorie = 1.2;
                level_protein = 0.8;

            } else if (la.isChecked()) {
                activity_level_calorie = 1.375;
                level_protein = 1;

            } else if (ma.isChecked()) {
                activity_level_calorie = 1.55;
                level_protein = 1.1;

            } else if (va.isChecked()) {
                activity_level_calorie = 1.725;
                level_protein = 1.2;

            } else if (sa.isChecked()) {
                activity_level_calorie = 1.9;
                level_protein = 1.5;

            } else {
                Toast.makeText(Setup_account.this, "You must select the level of activity", Toast.LENGTH_SHORT).show();
                nextscreen = false;
            }
        }
    }


    private void check_pregnant() {
        if (nextscreen) {
            if(isFemale) {
                if (notpreg.isChecked()) {
                    additional_calories = 0;
                } else if (firsttri.isChecked()) {
                    additional_calories = 0;
                    if (level_protein < 1.1)
                        level_protein = 1.1;
                } else if (secondtri.isChecked()) {
                    additional_calories = 395;
                    if (level_protein < 1.2)
                        level_protein = 1.2;
                } else if (thirdtri.isChecked()) {
                    additional_calories = 475;
                    if (level_protein < 1.3)
                        level_protein = 1.3;
                } else if (lactating.isChecked()) {
                    additional_calories = 415;
                    if (level_protein < 1.2)
                        level_protein = 1.2;}
                 else {
                    Toast.makeText(Setup_account.this, "You must select if you are pregnant or not", Toast.LENGTH_SHORT).show();
                    nextscreen = false;}
                }

        }
    }


    private void setupUi() {
        txt_height = findViewById(R.id.editText_height);
        txt_weight = findViewById(R.id.edittext_weight);
        txt_age = findViewById(R.id.editText_age);
        bar_height = findViewById(R.id.seekBar_height);
        bar_weight = findViewById(R.id.seekBar_weight);
        ser = findViewById(R.id.radio_Sedentary);
        la = findViewById(R.id.radio_Lightlyactive);
        ma = findViewById(R.id.radio_Moderatelyactive);
        va = findViewById(R.id.radio_Veryactive);
        sa = findViewById(R.id.radio_Superactive);
        female = findViewById(R.id.btn_female);
        male = findViewById(R.id.btn_male);
        notpreg = findViewById(R.id.radio_notpregnant);
        firsttri = findViewById(R.id.radio_firsttrimester);
        secondtri = findViewById(R.id.radio_secondtrimester);
        thirdtri = findViewById(R.id.radio_thirdtrimester);
        lactating = findViewById(R.id.radio_lactating);
        submit = findViewById(R.id.button_submittt);

    }

    private void Seekbars() {
        bar_weight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txt_weight.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });

        bar_height.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                txt_height.setText(String.valueOf(i));


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });


    }


    private void submit_button() {


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gettingnumbers();
                checkactivity_level();
                check_male_or_female();
                check_pregnant();
                if(nextscreen) {
                    final_result();
                    Intent i=new Intent(Setup_account.this, Main_screen.class);
                    startActivity(i);

                }


            }
        });


    }

    private void gettingnumbers() {
        String str_age, str_height, str_weight;
        str_age = txt_age.getText().toString();
        str_weight = txt_weight.getText().toString();
        str_height = txt_height.getText().toString();

        if(str_age.isEmpty() || str_height.isEmpty() || str_weight.isEmpty()){
            if (str_age.isEmpty())
                Toast.makeText(Setup_account.this, "Please enter your age", Toast.LENGTH_SHORT).show();
            else if (str_height.isEmpty())
                Toast.makeText(Setup_account.this, "Please enter your height", Toast.LENGTH_SHORT).show();
            else  if (str_weight.isEmpty())
                Toast.makeText(Setup_account.this, "Please enter your weight", Toast.LENGTH_SHORT).show();}
        else{
            nextscreen=true;
            num_age = Integer.parseInt(str_age);
            num_height = Integer.parseInt(str_height);
            num_weight = Integer.parseInt(str_weight);
        }
        if (nextscreen){
            if(num_age <= 14 || num_height <= 40 || num_weight <= 25 ){
                nextscreen=false;
                if (num_age <= 14)
                    Toast.makeText(Setup_account.this, "You must be fourteen or older to use this app", Toast.LENGTH_SHORT).show();
                else  if (num_height <= 40)
                    Toast.makeText(Setup_account.this, "Please enter valid height", Toast.LENGTH_SHORT).show();
                else if (num_weight <= 25)
                    Toast.makeText(Setup_account.this, "Please enter valid weight", Toast.LENGTH_SHORT).show();
            }




        }


    }
    public double Onedigit(double val){
        String vals=String.valueOf(val);
        vals=new DecimalFormat("##.#").format(val);
        val=Double.valueOf(vals);
        return val;
    }
}