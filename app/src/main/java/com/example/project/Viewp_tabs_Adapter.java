package com.example.project;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class Viewp_tabs_Adapter extends FragmentPagerAdapter {
    private final ArrayList<Fragment> fragmentArrayList=new ArrayList<>();
    private final ArrayList<String> fragmentTitles = new ArrayList<>();

    /**
     * constructor initializes an instance of the Viewp_tabs_Adapter class with the specified
     * FragmentManager and behavior.
     * @param fm
     * @param behavior
     */
    public Viewp_tabs_Adapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    /**
     *
     * @param position
     * @return Fragment at specific position
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    /**
     *
     * @return array list size
     */
    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    /**
     * adds the fragment to array list
     * @param fragment
     * @param title
     */
    public void addFragment(Fragment fragment, String title){
        fragmentArrayList.add(fragment);
        fragmentTitles.add(title);
    }

    /**
     *
     * @param position The position of the title requested
     * @return Charsequence at specific position
     */
    public CharSequence getPageTitle(int position) {
        return fragmentTitles.get(position);
    }


}
