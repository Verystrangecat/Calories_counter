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

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

        // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<Food_class_meals> arrayList;
    private ArrayList<Food_class_meals> arraynew;

    /**
     *   Required empty public constructor
     */
    public Fragment_breakfast() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_breakfast.
     */
    public static Fragment_breakfast newInstance(String param1, String param2) {
        Fragment_breakfast fragment = new Fragment_breakfast();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     *
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /**
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_breakfast2, container, false);
    }

    /**
     * deals with logic of a fragment such as sets the recycle view
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView=view.findViewById(R.id.recycle_view_breakfast);
        Button button=view.findViewById(R.id.button_add_breakfast);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        button.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param view The view that was clicked.
             */
            @Override
            public void onClick(View view) {
                if(view==button){
                    Intent i=new Intent(getActivity(),Adding_food_screen.class);
                    //so the app will know from which fragment the user came
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("my pref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Meal","b");
                    editor.apply();
                    startActivity(i);

            }}
        });

        getdata();
        if(arraynew!=null){
        Adapter_meals adapterMeals=new Adapter_meals(getContext(),arraynew);
        recyclerView.setAdapter(adapterMeals);
        adapterMeals.notifyDataSetChanged();}
    }

    /**
     * gets the array list of meals from the shared preferences and saves the food from breakfast to
     * array list
     */
    private void getdata() {

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("my pref", Context.MODE_PRIVATE);
        Gson gson2 = new Gson();
        String json2 = sharedPreferences.getString("MyObject", "");
        Array_class obj = gson2.fromJson(json2, Array_class.class);
        if(obj!=null){
        arrayList=obj.arrayList;
        arraynew=new ArrayList<>();
        if(arrayList!=null){
            for(int i=0; i<arrayList.size();i++){
                if(arrayList.get(i).meal.equals("b"))
                    arraynew.add(arrayList.get(i));
            }}
        }

    }
}