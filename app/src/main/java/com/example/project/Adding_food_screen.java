package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.project.Food_classes.Converter;
import com.example.project.Food_classes.Food;
import com.example.project.Food_classes.Welcome;
import com.example.project.utils.Network_utils;

import java.io.IOException;
import java.io.Serializable;

import java.net.URL;
import java.util.List;
;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Adding_food_screen extends AppCompatActivity implements View.OnClickListener, Adapter.onFoodListener, Serializable {
    RecyclerView recyclerView;
    Button search;
    EditText query;
    List<Food> food_list=null;

    private ProgressBar progressBar;

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
        setContentView(R.layout.activity_adding_food_screen);
        recyclerView=findViewById(R.id.recycle_view);
        search=findViewById(R.id.button_search);
        query=findViewById(R.id.editText_query);
        search.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar=findViewById(R.id.progressBar);



    }

    /**
     * sends the quary to the database
     * @param s- string of the food
     * n
     */

    public void buildstring(String s)  {
        String ur="https://api.nal.usda.gov/fdc/v1/foods/search?query=";
        s=s.trim();
        s=s.replace(" ","%20");
        s=ur+s+"&dataType=&pageSize=25&pageNumber=1&requireAllWords=true&api_key=IDReNNKpRHGD1el7mIQXJkqklYtzVV0ZaWOJCAuf";
        showLoadingDialog();
        Perform_search(s);
    }

    /**
     *
     * @param v The view that was clicked.
     */

    @Override
    public void onClick(View v) {
        String s=query.getText().toString();
        if (s.equals(""))
            Toast.makeText(this, "Can't search for the product", Toast.LENGTH_SHORT).show();
        else
            buildstring(s);



    }

    /**
     * click on one of items in the recycle view
     * @param position
     */
    @Override
    public void OnfoodClick(int position) {
        Intent intent=new Intent(Adding_food_screen.this, Food_details.class);
        intent.putExtra("KEY_NAME",food_list.get(position) );
        startActivity(intent);


    }
    /**
     *  runs the task in the background
     *  @param //urls
     * @return json string
     */
    /**
     * sends the quary to the database
     * @param url
     */
    public void Perform_search(String url) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Log.e("A","single thread is working");
        Callable<String> callable=new Callable<String>() {
            @Override
            public String call() throws Exception {
                return Network_utils.makeHttpsrequest(new URL(url));
            }
        };
        Future<String> future = executor.submit(callable);
        executor.shutdown();
        Log.e("S","after the shutdown");

        try {
            String result = future.get();
            Log.e("String result","the result is"+result);
            onDataLoaded(result);
        } catch (Exception e) {
            hideLoadingDialog();
            Log.e("Error", e.getMessage(), e);
            Toast.makeText(this, "There was a problem getting the data", Toast.LENGTH_SHORT).show();
        }
    }




    /**
         * json string to java class
         * @param data
         * @return list of food
         */

        private List<Food> parseJson(String data) {
            Welcome food=null;
            try {
                Log.e("Parse Json", "got to the function for parsing data");
                food= Converter.fromJsonString(data); //special class
            } catch (IOException e) {
                Log.e("Error", e.getMessage(), e);
                Toast.makeText(this, "There was a problem", Toast.LENGTH_SHORT).show();
            }
            hideLoadingDialog();
            return food.getFoods();


        }

    /**
     * sets up tha recycler view if the data is not null
     * @param data
     */
    private void onDataLoaded(String data) {
        food_list = parseJson(data);
        Log.e("food list", food_list.toString());
        Log.e("food list size ", String.valueOf(food_list.size()));
        if (food_list.size()==0 || food_list == null) {
            Toast.makeText(this, "Please check the spelling", Toast.LENGTH_SHORT).show();
        } else {
            Adapter adapter = new Adapter(Adding_food_screen.this, food_list, Adding_food_screen.this::OnfoodClick);
            recyclerView.setAdapter(adapter);
        }
    }

    /**
     * Show the progress bar
     */
    private void showLoadingDialog() {
        progressBar.setVisibility(View.VISIBLE);
        Log.e("Dialog","dialog is shown");
    }

    /**
     * Hide the progress bar
     */
    private void hideLoadingDialog() {
        progressBar.setVisibility(View.GONE);
    }

}
        //todo fix tha tik acorrding to the changes


    

