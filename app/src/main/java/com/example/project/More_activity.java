package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class More_activity extends AppCompatActivity implements View.OnClickListener {
    BottomNavigationView bottomNavigationView;
    TextView link_to_food, name_person, agev, heightv, weightv;
    Button button_change;


    /**
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        setupUi();
        getdatabase();
        bottom_navigation();

    }

    /**
     * connects ui to the class
     */
    private void setupUi() {
        link_to_food = findViewById(R.id.textView22);
        link_to_food.setOnClickListener(this);
        name_person = findViewById(R.id.textView_name_person);
        agev = findViewById(R.id.textView_age);
        weightv = findViewById(R.id.textView_weight);
        heightv = findViewById(R.id.textView_height);
        button_change = findViewById(R.id.button_change);
        button_change.setOnClickListener(this);

    }


    /**
     * sets up a bottom menu
     */
    private void bottom_navigation() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_more);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_home) {
                startActivity(new Intent(More_activity.this, Main_screen.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.navigation_more) {
                return true;
            } else if (item.getItemId() == R.id.navigation_graphs) {
                startActivity(new Intent(More_activity.this, Graphs.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.navigation_diary) {
                startActivity(new Intent(More_activity.this, Diary_screen_tabs.class));
                finish();
                return true;
            }
            return false;
        });
    }

    /**
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v == link_to_food) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://fdc.nal.usda.gov/"));
            startActivity(intent);
        } else if (v == button_change) {
            {
                Intent i = new Intent(More_activity.this, Change_activity.class);
                startActivity(i);
            }

        }
    }

        /**
         *gets the information from database and sets it in the textviews
         */
         public void getdatabase() {
            SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);
            String email = sharedPreferences.getString("email", "kutsvet100com@gmail.com");
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            Query query = databaseReference.child("users").orderByChild("email").equalTo(email);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                /**
                 *
                 * @param dataSnapshot The current data at the location
                 *                     gets the specific user
                 */
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // Iterate through the results
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Extract the User object
                        User user = snapshot.getValue(User.class);

                        if (user != null) {
                            // Found the user, you can access the user details

                            name_person.setText("Hello, " + user.getName());
                            agev.setText(String.valueOf(user.getAge()));
                            heightv.setText(String.valueOf(user.getHeight()));
                            weightv.setText(String.valueOf(user.getWeight()));
                            // ... (access other user details)
                            break; // Assuming there's only one user with the given email
                        }
                    }
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


