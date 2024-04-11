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

/**
 * Adapter for displaying a list of Food items in a RecyclerView in search results.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
    Context context;
    List<Food> foods;
    onFoodListener monFoodListener;
    private static final long CALORIE_NUTRIENT_ID = 1008;

    /**
     * Constructor for the Adapter class.
     *
     * @param context          The context of the activity or fragment.
     * @param foods            List of Food objects to be displayed.
     * @param monFoodListener  Listener for handling item click events.
     */
    public Adapter(Context context, List<Food> foods, onFoodListener monFoodListener) {
        this.monFoodListener = monFoodListener;
        this.context = context;
        this.foods = foods;
    }

    /**
     * Creates a new ViewHolder by inflating the layout for each item in the RecyclerView.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
        return new Holder(view, monFoodListener);
    }

    /**
     * Binds the data to the views in each item of the RecyclerView.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Food food = foods.get(position);
        boolean got = false;
        holder.food_name.setText(food.getDescription());

        // Retrieve and display calorie information
        for (int i = 0; i < food.getFoodNutrients().size() && !got; i++) {
            if (food.getFoodNutrients().get(i).getNutrientID() == CALORIE_NUTRIENT_ID) {
                holder.calorie.setText(String.valueOf(food.getFoodNutrients().get(i).getValue()));
                got = true;
            }
        }
        if (!got)
            holder.calorie.setText("No information");

        // Display brand information
        if (food.getBrandName() != null) {
            holder.brand.setText(food.getBrandName());
        } else
            holder.brand.setText("Generic");
    }

    /**
     * Gets the number of items in the adapter.
     *
     * @return The total number of items in the adapter.
     */
    @Override
    public int getItemCount() {
        return foods.size();
    }

    /**
     * ViewHolder class for holding the views of each item in the RecyclerView.
     */
    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView food_name, calorie, brand;
        onFoodListener onFoodListener;

        /**
         * Constructor for the ViewHolder class.
         *
         * @param itemView         The View of each item in the RecyclerView.
         * @param onFoodListener   Listener for handling item click events.
         */
        public Holder(@NonNull View itemView, onFoodListener onFoodListener) {
            super(itemView);
            food_name = itemView.findViewById(R.id.textView_foodname);
            calorie = itemView.findViewById(R.id.textView_calories);
            brand = itemView.findViewById(R.id.textView_brand);
            this.onFoodListener = onFoodListener;
            itemView.setOnClickListener(this);
        }

        /**
         * Handles item click events.
         *
         * @param v The clicked View.
         */
        @Override
        public void onClick(View v) {
            onFoodListener.OnfoodClick(getAdapterPosition());
        }
    }

    /**
     * Interface for handling item click events.
     */
    public interface onFoodListener {
        void OnfoodClick(int position);
    }
}


