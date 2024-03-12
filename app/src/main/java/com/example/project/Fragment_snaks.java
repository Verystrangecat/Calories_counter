package com.example.project;

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

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_snaks#newInstance} factory method to
 * create an instance of this fragment.
 */
/**
 * A fragment representing the snacks screen.
 */
public class Fragment_snaks extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private ArrayList<Food_class_meals> arrayList;
    private ArrayList<Food_class_meals> arraynew;

    // Required empty public constructor
    public Fragment_snaks() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_snaks.
     */
    public static Fragment_snaks newInstance(String param1, String param2) {
        Fragment_snaks fragment = new Fragment_snaks();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Called when the fragment is created.
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
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
     * Called to create the view for the fragment.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment.
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state.
     * @return The inflated view for the fragment.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_snaks, container, false);
    }

    /**
     * Called after the view is created. Deals with the logic of the fragment,
     * such as setting up the RecyclerView and handling button clicks.
     *
     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Reference to the RecyclerView and button in the layout
        RecyclerView recyclerView = view.findViewById(R.id.recycle_view_snacks);
        Button button = view.findViewById(R.id.button_add_snacks);

        // Set up the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        // Button click listener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == button) {
                    // Handle button click (start activity for adding food)
                    Intent i = new Intent(getActivity(), Adding_food_screen.class);
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("my pref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Meal", "s");
                    editor.apply();
                    startActivity(i);
                }
            }
        });

        // Get data from SharedPreferences and populate the RecyclerView
        getdata();
        if (arrayList != null) {
            Adapter_meals adapterMeals = new Adapter_meals(getContext(), arraynew);
            recyclerView.setAdapter(adapterMeals);
            adapterMeals.notifyDataSetChanged();
        }
    }

    /**
     * Retrieves data from SharedPreferences and filters food items for snacks.
     */
    private void getdata() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("my pref", Context.MODE_PRIVATE);

        Gson gson2 = new Gson();
        String json2 = sharedPreferences.getString("MyObject", "");
        Array_class obj = gson2.fromJson(json2, Array_class.class);
        arrayList = obj.arrayList;
        arraynew = new ArrayList<>();
        if (arrayList != null) {
            for (int i = 0; i < arrayList.size(); i++) {
                if (arrayList.get(i).meal.equals("s"))
                    arraynew.add(arrayList.get(i));
            }
        }
    }
}
