package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.project.Food_classes.Converter;
import com.example.project.Food_classes.FoodData;
import com.example.project.Food_classes.Welcome;
import com.example.project.utils.Network_utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class Adding_food_screen extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_food_screen);
        recyclerView=findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        try {
            querydata();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public void querydata() throws MalformedURLException {
        Uri uri=Uri.parse("https://api.nal.usda.gov/fdc/v1/foods/search?query=cheddar%20cheese&dataType=&pageSize=25&pageNumber=1&api_key=DEMO_KEY").buildUpon().build();
//Todo:Change the string
        URL url=new URL(uri.toString());
        new Dotask().execute(url);
    }
    class Dotask extends AsyncTask<URL,Void,String>{

        @Override
        protected String doInBackground(URL... urls) {
            URL url=urls[0];
            String data=null;
            try {
                 data= Network_utils.makeHttpsrequest(url);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return data;
        }
        protected void onPostExecute(String s){
          FoodData [] foodData=  parseJson(s);
          Adapter adapter=new Adapter(Adding_food_screen.this,Arrays.asList(foodData));
          recyclerView.setAdapter(adapter);
        }

        private FoodData[] parseJson(String data) {
            Welcome food=null;
            try {
                food= Converter.fromJsonString(data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return food.getFoods();



        }
        }
    }
