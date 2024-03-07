package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Adapter for displaying a list of ViewpagerItem objects in a RecyclerView.
 */
public class Vp_adapter extends RecyclerView.Adapter<Vp_adapter.ViewHolder> {
    ArrayList<ViewpagerItem> viewpagerItemArrayList;

    /**
     * Constructor for the Vp_adapter class.
     *
     * @param viewpagerItemArrayList List of ViewpagerItem objects to be displayed.
     */
    public Vp_adapter(ArrayList<ViewpagerItem> viewpagerItemArrayList) {
        this.viewpagerItemArrayList = viewpagerItemArrayList;
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Binds the data to the views in each item of the RecyclerView.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewpagerItem viewpagerItem = viewpagerItemArrayList.get(position);
        holder.imageView.setImageResource(viewpagerItem.getImage());
        holder.heading.setText(viewpagerItem.getHeading());
        holder.numleft.setText(viewpagerItem.getNumleft());
        holder.numtotal.setText(viewpagerItem.getNumtotal());
    }

    /**
     * Gets the number of items in the adapter.
     *
     * @return The total number of items in the adapter.
     */
    @Override
    public int getItemCount() {
        return viewpagerItemArrayList.size();
    }

    /**
     * ViewHolder class for holding the views of each item in the RecyclerView.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView heading, numtotal, numleft;

        /**
         * Constructor for the ViewHolder class.
         *
         * @param itemView The View of each item in the RecyclerView.
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView_viewpager);
            heading = itemView.findViewById(R.id.textView_heading);
            numtotal = itemView.findViewById(R.id.textView_numtotal);
            numleft = itemView.findViewById(R.id.textView_numleft);
        }
    }
}
