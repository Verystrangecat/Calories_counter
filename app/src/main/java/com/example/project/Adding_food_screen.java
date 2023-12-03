package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.Food_classes.Converter;
import com.example.project.Food_classes.Food;
import com.example.project.Food_classes.Welcome;
import com.example.project.utils.Network_utils;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Adding_food_screen extends AppCompatActivity implements View.OnClickListener, Adapter.onFoodListener, Serializable {
    RecyclerView recyclerView;
    Button search;
    EditText query;
    List<Food> f=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_food_screen);
        recyclerView=findViewById(R.id.recycle_view);
        search=findViewById(R.id.button_search);
        query=findViewById(R.id.editText_query);
        search.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void querydata(String s) throws MalformedURLException {
        String ur="https://api.nal.usda.gov/fdc/v1/foods/search?query=";
        s=s.trim();
        s=s.replace(" ","%20");
        s=ur+s+"&dataType=&pageSize=25&pageNumber=1&requireAllWords=true&api_key=IDReNNKpRHGD1el7mIQXJkqklYtzVV0ZaWOJCAuf";
        Uri uri=Uri.parse(s).buildUpon().build();
        URL url=new URL(uri.toString());
        new Dotask().execute(url);
    }

    @Override
    public void onClick(View v) {
        String s=query.getText().toString();
        if (s.equals(""))
            Toast.makeText(this, "Can't search for the product", Toast.LENGTH_SHORT).show();
        else
            try {
                querydata(s);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }



    }

    @Override
    public void OnfoodClick(int position) {
        Intent intent=new Intent(Adding_food_screen.this, Food_details.class);
        intent.putExtra("KEY_NAME",f.get(position) );
        startActivity(intent);


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
        protected void onPostExecute(String s) {
            f = parseJson(s);
            if (f == null)
                Toast.makeText(Adding_food_screen.this, "Please check the spelling", Toast.LENGTH_SHORT).show();
            else {
                Adapter adapter = new Adapter(Adding_food_screen.this, f,Adding_food_screen.this::OnfoodClick);
                recyclerView.setAdapter(adapter);
            }
        }

        private List<Food> parseJson(String data) {
            Welcome food=null;
            try {
                food= Converter.fromJsonString(data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return food.getFoods();
//Todo check why i dont get the toast about bad spelling

        }
        }
    }
    //Todo add finish to activities like login and dign up and so on
