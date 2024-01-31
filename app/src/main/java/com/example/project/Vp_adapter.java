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

public class Vp_adapter extends RecyclerView.Adapter<Vp_adapter.ViewHolder> {
    ArrayList<ViewpagerItem> viewpagerItemArrayList;


    public Vp_adapter(ArrayList<ViewpagerItem> viewpagerItemArrayList) {
        this.viewpagerItemArrayList = viewpagerItemArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewpagerItem viewpagerItem = viewpagerItemArrayList.get(position);
        holder.imageView.setImageResource(viewpagerItem.getImage());
        holder.heading.setText(viewpagerItem.getHeading());
        holder.numleft.setText(viewpagerItem.getNumleft());
        holder.numtotal.setText(viewpagerItem.getNumtotal());


    }

    @Override
    public int getItemCount() {
        return viewpagerItemArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView heading, numtotal, numleft;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            imageView = itemView.findViewById(R.id.imageView_viewpager);
            heading = itemView.findViewById(R.id.textView_heading);
            numtotal = itemView.findViewById(R.id.textView_numtotal);
            numleft = itemView.findViewById(R.id.textView_numleft);

        }
    }
}
