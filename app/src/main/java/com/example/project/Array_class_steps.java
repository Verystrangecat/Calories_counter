package com.example.project;

import java.util.ArrayList;

public class Array_class_steps {
    ArrayList<Integer> arrayList;

    /**
     * constructor of arraylist that has 2 values for now
     */
    public Array_class_steps(){
        arrayList=new ArrayList<>();
        arrayList.add(1000);
        arrayList.add(2000);
        //left as an example
    }

    /**
     * adds the new steps and deletes the first ones of there are more than 7 of them
     * @param steps int
     */
    public void addday(int steps){
        if(arrayList.size()>=7){
            arrayList.remove(0);
            arrayList.add(steps);
        }
        else
            arrayList.add(steps);
    }

    /**
     *
     * @return arraylist
     */
    public ArrayList<Integer> getArrayList() {
        return arrayList;
    }
}
