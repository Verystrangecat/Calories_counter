package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_meals extends RecyclerView.Adapter<Adapter_meals.ViewHolder_meals> {
    Context context;
    ArrayList<Food_class_meals> arrayList;

    public Adapter_meals(Context context, ArrayList<Food_class_meals> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder_meals onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item_recylcle_meals, parent,false);

        return new ViewHolder_meals(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_meals holder, int position) {
        Food_class_meals food_class_meals=arrayList.get(position);
        holder.tportion.setText(food_class_meals.getPortion());
        holder.tname.setText(food_class_meals.getName());
        holder.tbrand.setText(food_class_meals.getBrand());
        holder.tcal.setText(food_class_meals.getCalories());
        holder.tcarb.setText(food_class_meals.getCarbs());
        holder.tpro.setText(food_class_meals.getProteins());
        holder.tfat.setText(food_class_meals.getFats());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder_meals extends RecyclerView.ViewHolder{
        TextView tcal, tcarb, tpro, tfat, tname, tbrand, tportion;


        public ViewHolder_meals(@NonNull View itemView) {
            super(itemView);
            tcarb=itemView.findViewById(R.id.textView_carbs_meals);
            tcal=itemView.findViewById(R.id.textView_calories_meals);
            tpro=itemView.findViewById(R.id.textView_proteins_meals);
            tfat=itemView.findViewById(R.id.textView_fats_meals);
            tname=itemView.findViewById(R.id.textView_foodname);
            tbrand=itemView.findViewById(R.id.textView_brand_meals);
            tportion=itemView.findViewById(R.id.portion_meals);




        }
    }
}
