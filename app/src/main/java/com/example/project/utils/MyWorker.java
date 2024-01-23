package com.example.project.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.project.Array_class;
import com.google.gson.Gson;

import java.text.DecimalFormat;

public class MyWorker extends Worker {
    public MyWorker( @NonNull Context context,
                     @NonNull WorkerParameters params){
        super(context, params);

    }
    @NonNull
    @Override
    public Result doWork() {
        updateSharedPreferences();
        return Result.success();
    }
    private void updateSharedPreferences(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("my pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("amount_calories_left", sharedPreferences.getString("amount_calories","0"));
        editor.putString("amount_proteins_left",sharedPreferences.getString("amount_proteins","0"));
        editor.putString("amount_fats_left",sharedPreferences.getString("amount_fats","0"));
        editor.putString("amount_carbs_left",sharedPreferences.getString("amount_carbs","0"));
        Gson gson2 = new Gson();
        String json2 = sharedPreferences.getString("MyObject", "");
        Array_class obj = gson2.fromJson(json2, Array_class.class);
        obj.arrayList.clear();
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        editor.putString("MyObject", json);
        editor.apply();
    }

}
