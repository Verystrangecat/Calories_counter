package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Setup_account extends AppCompatActivity implements View.OnClickListener {
    EditText txt_height, txt_weight, txt_age;
    SeekBar bar_height, bar_weight;
    RadioButton ser, la, ma, va, sa, notpreg, firsttri, secondtri, thirdtri, lactating;

    boolean isFemale = true, wasClicked = false, nextscreen = false;
    double activity_level_calorie = 0, level_protein = 0;
    int additional_calories = 0;
    int num_age, num_height, num_weight;
     static double calories=0, proteins=0, fats=0, carbohydrates=0;
     private User user;

    Button submit, female, male;

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
        setContentView(R.layout.activity_setup_account);
        setupUi();
        Seekbars();
        savethedate();
        user = new User();



        }

    /**
     * saves the current date
     */
    private void savethedate() {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd.MM");
        String datenow = df.format(c);


        SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("date", datenow);
        editor.apply();
    }



    /**
     * counting the numbers for calories etc after getting all the data
     *     saving the results to shared preference
     *     also writing the data to the database
     */
    private void final_result() {
        if(isFemale) {
            user.setGender("Female");
            calories = Math.round((447.593 + (9.247 * num_weight) + (3.098 * num_height) - (4.330 * num_age)) * activity_level_calorie + additional_calories);
        } else{
            user.setGender("Male");
            user.setPregnant(0);
            calories= Math.round((88.362 + (13.397 *num_weight) + (4.799 * num_height) - (5.677 *num_age))*activity_level_calorie);}



        proteins=Onedigit(num_weight*level_protein);
        fats=Onedigit((calories*0.27)/9);
        carbohydrates=Onedigit(calories*0.55/4);
        String date=getDate(System.currentTimeMillis());


        WriteUserData writeUserData = new WriteUserData();
        Bundle extras = getIntent().getExtras();
        user.setName(extras.getString("Name"));
        user.setAge(num_age);
        user.setHeight(num_height);
        user.setWeight(num_weight);
        user.setEmail(extras.getString("Email"));
        user.setCalories((int)calories);
        user.setProtein(proteins);
        user.setFat(fats);
        user.setCarbs(carbohydrates);
        writeUserData.addUser(user);
        SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", user.getEmail());
        editor.putString(getString(R.string.amount_calories),String.valueOf(calories));
        editor.putString(getString(R.string.amount_proteins),String.valueOf(proteins));
        editor.putString(getString(R.string.amount_carbs),String.valueOf(carbohydrates));
        editor.putString(getString(R.string.amount_fats),String.valueOf(fats));
        editor.putString(getString(R.string.amount_calories_left),String.valueOf(calories));
        editor.putString(getString(R.string.amount_proteins_left),String.valueOf(proteins));
        editor.putString(getString(R.string.amount_carbs_left),String.valueOf(carbohydrates));
        editor.putString(getString(R.string.amount_fats_left),String.valueOf(fats));


        editor.putString("date", date);

        editor.apply();



    }


    /**
     * checks if the gender is chosen
     */
    private void check_male_or_female(){
        if(nextscreen){
            if(!wasClicked){
                nextscreen=false;
                Toast.makeText(this,"Please choose your sex",Toast.LENGTH_SHORT).show();

            }
        }

    }

    /**
     * gets the activity level
     */
    private void checkactivity_level() {
        if (nextscreen) {
            if (ser.isChecked()) {
                activity_level_calorie = 1.2;
                level_protein = 0.8;
                user.setActivity(1);

            } else if (la.isChecked()) {
                activity_level_calorie = 1.375;
                level_protein = 1;
                user.setActivity(2);

            } else if (ma.isChecked()) {
                activity_level_calorie = 1.55;
                level_protein = 1.1;
                user.setActivity(3);

            } else if (va.isChecked()) {
                activity_level_calorie = 1.725;
                level_protein = 1.2;
                user.setActivity(4);

            } else if (sa.isChecked()) {
                activity_level_calorie = 1.9;
                level_protein = 1.5;
                user.setActivity(5);

            } else {
                Toast.makeText(Setup_account.this, "You must select the level of activity", Toast.LENGTH_SHORT).show();
                nextscreen = false;
            }
        }
    }

    /**
     * check if pregnant and what stage
     */
    private void check_pregnant() {
        if (nextscreen) {
            if(isFemale) {
                if (notpreg.isChecked()) {
                    additional_calories = 0;
                    user.setPregnant(0);
                } else if (firsttri.isChecked()) {
                    additional_calories = 0;
                    user.setPregnant(1);
                    if (level_protein < 1.1)
                        level_protein = 1.1;
                } else if (secondtri.isChecked()) {
                    additional_calories = 395;
                    user.setPregnant(2);
                    if (level_protein < 1.2)
                        level_protein = 1.2;
                } else if (thirdtri.isChecked()) {
                    additional_calories = 475;
                    user.setPregnant(3);
                    if (level_protein < 1.3)
                        level_protein = 1.3;
                } else if (lactating.isChecked()) {
                    user.setPregnant(4);
                    additional_calories = 415;
                    if (level_protein < 1.2)
                        level_protein = 1.2;}
                 else {
                    Toast.makeText(Setup_account.this, "You must select if you are pregnant or not", Toast.LENGTH_SHORT).show();
                    nextscreen = false;}
                }

        }
    }

    /**
     * connects ui to the class
     */
    private void setupUi() {
        txt_height = findViewById(R.id.editText_height);
        txt_weight = findViewById(R.id.edittext_weight);
        txt_age = findViewById(R.id.editText_age);
        bar_height = findViewById(R.id.seekBar_height);
        bar_weight = findViewById(R.id.seekBar_weight);
        //activity levels
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
        female.setOnClickListener(this);
        male.setOnClickListener(this);
        submit.setOnClickListener(this);

    }

    /**
     * sets up the seekbars
     */
    private void Seekbars() {
        bar_weight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /**
             *
             * @param seekBar The SeekBar whose progress has changed
             * @param i The current progress level. This will be in the range min..max where min
             *                 and max were set by {@link ProgressBar#setMin(int)} and
             *                 , respectively. (The default values for
             *                 min is 0 and max is 100.)
             * @param b True if the progress change was initiated by the user.
             */
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txt_weight.setText(String.valueOf(i));
            }

            /**
             *
             * @param seekBar The SeekBar in which the touch gesture began
             */
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            /**
             *
             * @param seekBar The SeekBar in which the touch gesture began
             */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });

        bar_height.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /**
             *
             * @param seekBar The SeekBar whose progress has changed
             * @param i The current progress level. This will be in the range min..max where min
             *                 and max were set by {@link ProgressBar#setMin(int)} and
             *                 {@link ProgressBar#setMax(int)}, respectively. (The default values for
             *                 min is 0 and max is 100.)
             * @param b True if the progress change was initiated by the user.
             */
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                txt_height.setText(String.valueOf(i));


            }

            /**
             *
             * @param seekBar The SeekBar in which the touch gesture began
             */
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            /**
             *
             * @param seekBar The SeekBar in which the touch gesture began
             */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });


    }


    /**
     * height, weight and age of the user, does the check if they are good or not
     */
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
            if(num_age < 18 || num_height <= 40 || num_weight < 25 ){
                nextscreen=false;
                if (num_age <18)
                    Toast.makeText(Setup_account.this, "You must be eighteen or older to use this app", Toast.LENGTH_SHORT).show();
                else  if (num_height <= 40)
                    Toast.makeText(Setup_account.this, "Please enter valid height", Toast.LENGTH_SHORT).show();
                else if (num_weight <25)
                    Toast.makeText(Setup_account.this, "Please enter valid weight", Toast.LENGTH_SHORT).show();
            }




        }


    }

    /**
     *
     * @param val-double
     * @return double number
     * returns the double value with 1 digit after the decimal
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

    /**
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v==female){
            notpreg.setEnabled(true);
            firsttri.setEnabled(true);
            secondtri.setEnabled(true);
            thirdtri.setEnabled(true);
            lactating.setEnabled(true);
            isFemale = true;
            wasClicked = true;
        }
        else if(v==male){
            notpreg.setEnabled(false);
            firsttri.setEnabled(false);
            secondtri.setEnabled(false);
            thirdtri.setEnabled(false);
            lactating.setEnabled(false);
            isFemale = false;
            wasClicked = true;
        }
        else if(v==submit){
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

        }

    /**
     *
     * @param time
     * @return date-String
     * gets the current date
     */
    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("dd-MM-yyyy", cal).toString();
        return date;
    }
    }
