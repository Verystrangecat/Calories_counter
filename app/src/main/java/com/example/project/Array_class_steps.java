package com.example.project;

import java.util.ArrayList;

public class Array_class_steps {
    ArrayList<Step> arrayList;

    public Array_class_steps(){
        arrayList=new ArrayList<>();
    }
    public void addday(int steps, String date){
        if(arrayList.size()>=7){
            arrayList.remove(0);
            arrayList.add(new Step(steps, date));
        }
        else
            arrayList.add(new Step(steps, date));
    }
}
//todo new classes to tik project