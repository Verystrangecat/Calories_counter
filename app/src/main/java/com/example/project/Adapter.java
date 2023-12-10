package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Food_classes.Food;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
    Context context;
    List<Food> foods;
    onFoodListener monFoodListener;

    public Adapter(Context context, List<Food> foods, onFoodListener monFoodListener) {
        this.monFoodListener=monFoodListener;
        this.context = context;
        this.foods = foods;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_view, parent,false);
        return new Holder(view,monFoodListener);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Food food=foods.get(position);
        holder.food_name.setText(food.getDescription());
      //  for (int i=0; i<food.)
        holder.calorie.setText(String.valueOf(food.getFoodNutrients().get(3).getValue()));

        if(food.getBrandName()!=null){
            holder.brand.setText(food.getBrandName());
        }
        else
            holder.brand.setText("Generic");

    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView food_name, calorie, brand;
        onFoodListener onFoodListener;
        public Holder(@NonNull View itemView, onFoodListener onFoodListener) {
            super(itemView);
            food_name=itemView.findViewById(R.id.textView_foodname);
            calorie=itemView.findViewById(R.id.textView_calories);
            brand=itemView.findViewById(R.id.textView_brand);
            this.onFoodListener=onFoodListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onFoodListener.OnfoodClick(getAdapterPosition());

        }
    }
    public interface onFoodListener{
        void OnfoodClick(int position);

    }
}
