package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Food_classes.FoodData;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
    Context context;
    List<FoodData> foods;

    public Adapter(Context context, List<FoodData> foods) {
        this.context = context;
        this.foods = foods;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_view, parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        FoodData foodData=foods.get(position);
        holder.food_name.setText(foodData.getDescription());
        //Todo check if it's the way to get the calorie
        if(foodData.getBrandName()!=null){
            holder.brand.setText(foodData.getBrandName());
        }
        else
            holder.brand.setText("Generic");

    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        TextView food_name, calorie, brand;
        public Holder(@NonNull View itemView) {
            super(itemView);
            food_name=itemView.findViewById(R.id.textView_foodname);
            calorie=itemView.findViewById(R.id.textView_calories);
            brand=itemView.findViewById(R.id.textView_brand);
        }
    }
}
