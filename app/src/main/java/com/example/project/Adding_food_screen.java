package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.project.Food_classes.Converter;
import com.example.project.Food_classes.FoodData;
import com.example.project.Food_classes.Welcome;
import com.example.project.utils.Network_utils;

import java.io.IOException;
import java.net.URL;

public class Adding_food_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_food_screen);
        querydata();
    }

    public void querydata() {
        Uri uri=Uri.parse("ksdk").buildUpon().build();

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
            parseJson(s);
        }

        private void parseJson(String data) {
            Welcome food=null;
            try {
                food= Converter.fromJsonString(data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        }
    }
