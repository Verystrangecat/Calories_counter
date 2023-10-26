package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Toast;

public class Setup_account extends AppCompatActivity {
    EditText txt_height, txt_weight, txt_age;
    SeekBar bar_height, bar_weight;
    RadioButton ser, la, ma, va, sa, notpreg, firsttri, secondtri, thirdtri, lactating;

    boolean isFemale = true, wasClicked = false, nextscreen = false;
    double activity_level_calorie = 0, level_protein = 0;
    int additional_calories = 0;
    int num_age, num_height, num_weight;
    int calories, proteins, fats, carbohydrates;

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
            calories=(int) Math.round((447.593 + (9.247 *num_weight) + (3.098 * num_height) - (4.330*num_age))*activity_level_calorie+additional_calories);
        else
            calories=(int) Math.round((88.362 + (13.397 *num_weight) + (4.799 * num_height) - (5.677 *num_age))*activity_level_calorie);
        proteins=(int) Math.round(num_weight*level_protein);
        fats=(int) Math.round((calories*0.27)/9);
        carbohydrates=(int) Math.round(calories*0.55/4);
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

    private void checkactivity_level() {
        if (ser.isChecked()) {
            activity_level_calorie = 1.2;
            level_protein = 0.8;
            nextscreen = true;
        } else if (la.isChecked()) {
            activity_level_calorie = 1.375;
            level_protein = 1;
            nextscreen = true;
        } else if (ma.isChecked()) {
            activity_level_calorie = 1.55;
            level_protein = 1.1;
            nextscreen = true;
        } else if (va.isChecked()) {
            activity_level_calorie = 1.725;
            level_protein = 1.2;
            nextscreen = true;
        } else if (sa.isChecked()) {
            activity_level_calorie = 1.9;
            level_protein = 1.5;
            nextscreen = true;
        } else {
            Toast.makeText(Setup_account.this, "You must select the level of activity", Toast.LENGTH_SHORT).show();
            nextscreen = false;
        }


    }

    private void check_pregnant() {
        if (notpreg.isChecked()) {
            additional_calories = 0;
            nextscreen = true;
        } else if (firsttri.isChecked()) {
            additional_calories = 0;
            nextscreen = true;
            if (level_protein < 1.1)
                level_protein = 1.1;
        } else if (secondtri.isChecked()) {
            additional_calories = 395;
            nextscreen = true;
            if (level_protein < 1.2)
                level_protein = 1.2;
        } else if (thirdtri.isActivated()) {
            additional_calories = 475;
            nextscreen = true;
            if (level_protein < 1.3)
                level_protein = 1.3;
        } else if (lactating.isChecked()) {
            additional_calories = 415;
            nextscreen = true;
            if (level_protein < 1.2)
                level_protein = 1.2;
        } else {
            Toast.makeText(Setup_account.this, "You must select if you are pregnant or not", Toast.LENGTH_SHORT).show();

            nextscreen = false;
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
                male_or_female();
                check_pregnant();
                if(nextscreen) {
                    final_result();
                    Intent i=new Intent(Setup_account.this, Main_screen.class);
                    startActivity(i);
                    //Todo:go to the next screen
                }


            }
        });


    }

    private void gettingnumbers() {
        String str_age, str_height, str_weight;
        str_age = txt_age.getText().toString();
        str_weight = txt_weight.getText().toString();
        str_height = txt_height.getText().toString();

        if (str_age.isEmpty()) {
            Toast.makeText(Setup_account.this, "Please enter your age", Toast.LENGTH_SHORT).show();
            nextscreen = false;
        } else {
            num_age = Integer.parseInt(str_age);
            nextscreen = true;
        }
        if (str_height.isEmpty()) {
            Toast.makeText(Setup_account.this, "Please enter your height", Toast.LENGTH_SHORT).show();
            nextscreen = false;
        } else {
            num_height = Integer.parseInt(str_height);
            nextscreen = true;
        }
        if (str_weight.isEmpty()) {
            Toast.makeText(Setup_account.this, "Please enter your weight", Toast.LENGTH_SHORT).show();
            nextscreen = false;
        } else {
            num_weight = Integer.parseInt(str_weight);
            nextscreen = true;
            if (!wasClicked) {
                Toast.makeText(Setup_account.this, "Please choose your gender", Toast.LENGTH_SHORT).show();
                nextscreen = false;

            } else
                nextscreen = true;
            //Check if information is entered at all

            if (num_age <= 14) {
                Toast.makeText(Setup_account.this, "You must be fourteen or older to use this app", Toast.LENGTH_SHORT).show();
                nextscreen = false;
            } else
                nextscreen = true;
            if (num_height <= 40) {
                Toast.makeText(Setup_account.this, "Please enter valid height", Toast.LENGTH_SHORT).show();
                nextscreen = false;
            } else
                nextscreen = true;
            if (num_weight <= 25) {
                Toast.makeText(Setup_account.this, "Please enter valid weight", Toast.LENGTH_SHORT).show();
                nextscreen = false;
            } else
                nextscreen = true;


        }


    }
    //TOdo change the color of the button
}