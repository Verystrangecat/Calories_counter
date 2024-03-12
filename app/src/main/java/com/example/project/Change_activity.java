package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Change_activity extends AppCompatActivity implements View.OnClickListener {
    EditText txt_height, txt_weight, txt_age;
    SeekBar bar_height, bar_weight;
    RadioButton ser, la, ma, va, sa, notpreg, firsttri, secondtri, thirdtri, lactating;

    boolean isFemale = true, wasClicked = false, nextscreen = false;
    double activity_level_calorie = 0, level_protein = 0;
    int additional_calories = 0;
    int num_age, num_height, num_weight;
    static double calories=0, proteins=0, fats=0, carbohydrates=0;


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
        setContentView(R.layout.activity_change);
        setupUi();
        Seekbars();

    }





    /**
     * counting the numbers for calories etc after getting all the data
     *     saving the results to shared preference
     *     also writing the data to the database
     */
    private void final_result() {
        if(isFemale) {

            calories = Math.round((447.593 + (9.247 * num_weight) + (3.098 * num_height) - (4.330 * num_age)) * activity_level_calorie + additional_calories);
        } else{

            calories= Math.round((88.362 + (13.397 *num_weight) + (4.799 * num_height) - (5.677 *num_age))*activity_level_calorie);}



        proteins=Onedigit(num_weight*level_protein);
        fats=Onedigit((calories*0.27)/9);
        carbohydrates=Onedigit(calories*0.55/4);


        //todo deal with amount calories left
        SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //getting all
        double calories_before =  Double.valueOf(sharedPreferences.getString("amount_calories","0"));
        double proteins_before =  Double.valueOf(sharedPreferences.getString("amount_proteins","0"));
        double fats_before =  Double.valueOf(sharedPreferences.getString("amount_fats","0"));
        double carbohydrates_before =  Double.valueOf(sharedPreferences.getString("amount_carbs","0"));
        //getting how much left
        double calories_left =  Double.valueOf(sharedPreferences.getString("amount_calories_left","0"));
        double proteins_left =  Double.valueOf(sharedPreferences.getString("amount_proteins_left","0"));
        double fats_left =  Double.valueOf(sharedPreferences.getString("amount_fats_left","0"));
        double carbohydrates_left =  Double.valueOf(sharedPreferences.getString("amount_carbs_left","0"));
        //counting hoiw much was used
        double calorie_difference=calories_before-calories_left;
        double protein_difference=proteins_before-proteins_left;
        double fat_difference=fats_before-fats_left;
        double carbohydrate_difference=carbohydrates_before-carbohydrates_left;
        calories_left=calories-calorie_difference;
        proteins_left=proteins-protein_difference;
        fats_left=fats-fat_difference;
        carbohydrates_left=carbohydrates-carbohydrate_difference;

        editor.putString("amount_calories",String.valueOf(calories));
        editor.putString("amount_proteins",String.valueOf(proteins));
        editor.putString("amount_carbs",String.valueOf(carbohydrates));
        editor.putString("amount_fats",String.valueOf(fats));
        editor.putString("amount_calories_left",String.valueOf(calories_left));
        editor.putString("amount_proteins_left",String.valueOf(proteins_left));
        editor.putString("amount_carbs_left",String.valueOf(carbohydrates_left));
        editor.putString("amount_fats_left",String.valueOf(fats_left));
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
                Toast.makeText(Change_activity.this, "You must select the level of activity", Toast.LENGTH_SHORT).show();
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

                } else if (firsttri.isChecked()) {
                    additional_calories = 0;
                  ;
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
                    Toast.makeText(Change_activity.this, "You must select if you are pregnant or not", Toast.LENGTH_SHORT).show();
                    nextscreen = false;}
            }

        }
    }

    /**
     * connects ui to the class
     */
    private void setupUi() {
        txt_height = findViewById(R.id.editText_height_change);
        txt_weight = findViewById(R.id.edittext_weight_change);
        txt_age = findViewById(R.id.editText_age_change);
        bar_height = findViewById(R.id.seekBar_height_change);
        bar_weight = findViewById(R.id.seekBar_weight_change);
        //activity levels
        ser = findViewById(R.id.radio_Sedentary_change);
        la = findViewById(R.id.radio_Lightlyactive_change);
        ma = findViewById(R.id.radio_Moderatelyactive_change);
        va = findViewById(R.id.radio_Veryactive_change);
        sa = findViewById(R.id.radio_Superactive_change);
        female = findViewById(R.id.btn_female_change);
        male = findViewById(R.id.btn_male_change);
        notpreg = findViewById(R.id.radio_notpregnant_change);
        firsttri = findViewById(R.id.radio_firsttrimester_change);
        secondtri = findViewById(R.id.radio_secondtrimester_change);
        thirdtri = findViewById(R.id.radio_thirdtrimester_change);
        lactating = findViewById(R.id.radio_lactating_change);
        submit = findViewById(R.id.button_submittt_change);
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
                Toast.makeText(Change_activity.this, "Please enter your age", Toast.LENGTH_SHORT).show();
            else if (str_height.isEmpty())
                Toast.makeText(Change_activity.this, "Please enter your height", Toast.LENGTH_SHORT).show();
            else  if (str_weight.isEmpty())
                Toast.makeText(Change_activity.this, "Please enter your weight", Toast.LENGTH_SHORT).show();}
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
                    Toast.makeText(Change_activity.this, "You must be eighteen or older to use this app", Toast.LENGTH_SHORT).show();
                else  if (num_height <= 40)
                    Toast.makeText(Change_activity.this, "Please enter valid height", Toast.LENGTH_SHORT).show();
                else if (num_weight <25)
                    Toast.makeText(Change_activity.this, "Please enter valid weight", Toast.LENGTH_SHORT).show();
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
                updatethedatabase();
                Intent i=new Intent(Change_activity.this, Main_screen.class);
                startActivity(i);
            }
        }

    }

    /**
     * updates the database
     */
    public void updatethedatabase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);
        Query query = databaseReference.child("users").orderByChild("email").equalTo(sharedPreferences.getString("email", ""));
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            /**
             *
             * @param dataSnapshot The current data at the location
             *                     gets the specific user
             */
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String userid="-NsSzX8cDIeqHioirLZD";
                // Iterate through the results
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Extract the User object

                    User user = snapshot.getValue(User.class);

                    if (user != null) {
                        userid=user.getId();
                        break; // Assuming there's only one user with the given email
                    }
                }
                DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(userid);

// Step 2: Update the values
                Map<String, Object> updates = new HashMap<>();
                updates.put("calories", (int)calories);
                updates.put("protein", proteins);
                updates.put("carbs", carbohydrates);
                updates.put("fat", fats);
                updates.put("height", num_height);
                updates.put("weight", num_weight);
                updates.put("age", num_age);


// ... (add other fields as needed)

// Step 3: Apply the changes
                userRef.updateChildren(updates, new DatabaseReference.CompletionListener() {
                    /**
                     *
                     * @param databaseError A description of any errors that occurred or null on success
                     * @param databaseReference A reference to the specified Firebase Database location
                     */
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        if (databaseError != null) {
                            // Update failed
                            Log.e("FirebaseUpdate", "Data could not be updated. " + databaseError.getMessage());
                        } else {
                            // Update successful
                            Log.d("FirebaseUpdate", "Data updated successfully.");
                        }
                    }
                });
            }

            /**
             *
             * @param databaseError A description of the error that occurred
             */


            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
            }
        });
    }

    }
//todo think about changing the user




