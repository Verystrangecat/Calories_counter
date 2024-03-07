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

    /**
     * constructor
     * @param context
     * @param arrayList
     */
    public Adapter_meals(Context context, ArrayList<Food_class_meals> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    /**
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return
     */
    @NonNull
    @Override
    public ViewHolder_meals onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item_recylcle_meals, parent,false);

        return new ViewHolder_meals(v);
    }

    /**
     * sets the information of the item
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder_meals holder, int position) {
        Food_class_meals food_class_meals=arrayList.get(position);
        if(food_class_meals!=null){
        holder.tportion.setText(food_class_meals.getPortion());
        holder.tname.setText(food_class_meals.getName());
        holder.tbrand.setText(food_class_meals.getBrand());
        holder.tcal.setText(food_class_meals.getCalories()+"(kc)");
        holder.tcarb.setText(food_class_meals.getCarbs()+"(c)");
        holder.tpro.setText(food_class_meals.getProteins()+"(p)");
        holder.tfat.setText(food_class_meals.getFats()+"(f)");}
    }

    /**
     *
     * @return arraylist size
     */
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder_meals extends RecyclerView.ViewHolder{
        TextView tcal, tcarb, tpro, tfat, tname, tbrand, tportion;

        /**
         * connects the views with the variables
         * @param itemView
         */
        public ViewHolder_meals(@NonNull View itemView) {
            super(itemView);
            tcarb=itemView.findViewById(R.id.textView_carbs_meals);
            tcal=itemView.findViewById(R.id.textView_calories_meals);
            tpro=itemView.findViewById(R.id.textView_proteins_meals);
            tfat=itemView.findViewById(R.id.textView_fats_meals);
            tname=itemView.findViewById(R.id.textView_foodname_meals);
            tbrand=itemView.findViewById(R.id.textView_brand_meals);
            tportion=itemView.findViewById(R.id.portion_meals);




        }
    }
}
