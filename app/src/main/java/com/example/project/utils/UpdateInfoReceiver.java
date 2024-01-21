package com.example.project.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.project.Array_class;
import com.google.gson.Gson;

public class UpdateInfoReceiver extends Broadcast_reciever{
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        SharedPreferences sharedPreferences = context.getSharedPreferences("my_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("amount_calories_left",sharedPreferences.getString("amount_calories","0"));
        editor.putString("amount_proteins_left",sharedPreferences.getString("amount_proteins","0"));
        editor.putString("amount_carbs_left",sharedPreferences.getString("amount_carbs","0"));
        editor.putString("amount_fats_left",sharedPreferences.getString("amount_fats","0"));

        Gson gson2 = new Gson();
        String json2 = sharedPreferences.getString("MyObject", "");
        Array_class obj = gson2.fromJson(json2, Array_class.class);
        obj.arrayList.clear();
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        editor.putString("MyObject", json);
        editor.apply();
        // Update your information here
        editor.apply();
    }
}
