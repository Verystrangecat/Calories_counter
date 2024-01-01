package com.example.project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_breakfast#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_breakfast extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

        // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<Food_class_meals> arrayList;

    public Fragment_breakfast() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_breakfast.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_breakfast newInstance(String param1, String param2) {
        Fragment_breakfast fragment = new Fragment_breakfast();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_breakfast2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView=view.findViewById(R.id.recycle_view_breakfast);
        Button button=view.findViewById(R.id.button_add_breakfast);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view==button)
                    startActivity(new Intent(getActivity(), Adding_food_screen.class));
            }
        });

        getdata();
        if(arrayList!=null){
        Adapter_meals adapterMeals=new Adapter_meals(getContext(),arrayList);
        recyclerView.setAdapter(adapterMeals);
        adapterMeals.notifyDataSetChanged();}
    }

    private void getdata() {

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("my pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson2 = new Gson();
        String json2 = sharedPreferences.getString("MyObject", "");
        Array_class obj = gson2.fromJson(json2, Array_class.class);
        arrayList=obj.arrayList;
        // TODO: 28/12/2023 get from shared preference an array

    }
}