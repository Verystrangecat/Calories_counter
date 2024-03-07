package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TableLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class Diary_screen_tabs extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    BottomNavigationView bottomNavigationView;

    /**
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_screen_tabs);
        setupUI();
        bottom_navigation();
    }

    /**
     * connects ui to the class
     * and creates fragments
     */
    private void setupUI() {
        tabLayout=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.viewpager_tabs);
        tabLayout.setupWithViewPager(viewPager);
        Viewp_tabs_Adapter viewpTabsAdapter=new Viewp_tabs_Adapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewpTabsAdapter.addFragment(new Fragment_breakfast(),"Breakfast");
        viewpTabsAdapter.addFragment(new Fragment_lunch(),"Lunch");
        viewpTabsAdapter.addFragment(new Fragment_dinner(),"Dinner");
        viewpTabsAdapter.addFragment(new Fragment_snaks(),"Snacks");
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        viewPager.setAdapter(viewpTabsAdapter);
    }

    /**
     * sets up a bottom menu
     */
    private void bottom_navigation(){

        bottomNavigationView.setSelectedItemId(R.id.navigation_diary);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId()==R.id.navigation_home){
                startActivity(new Intent(Diary_screen_tabs.this, Main_screen.class));
                finish();
                return true;}
            else if (item.getItemId()==R.id.navigation_diary){

                return true;
            }
            else if (item.getItemId()==R.id.navigation_graphs){
                startActivity(new Intent(Diary_screen_tabs.this, Graphs.class));
                finish();
                return true;
            }
            else if (item.getItemId()==R.id.navigation_more){
                startActivity(new Intent(Diary_screen_tabs.this, More_activity.class));
                finish();
                return true;
            }
            return false;
             });
    }
}