package com.example.project;

import java.util.ArrayList;

public class Array_class_steps {
    ArrayList<Step> arrayList;

    public Array_class_steps(){
        arrayList=new ArrayList<>();
        arrayList.add(new Step(1000, 29.02));
        arrayList.add(new Step(2000, 1.03));
        //todo do not forget to delete the line above
    }
    public void addday(int steps, double date){
        if(arrayList.size()>=7){
            arrayList.remove(0);
            arrayList.add(new Step(steps, date));
        }
        else
            arrayList.add(new Step(steps, date));
    }

    public ArrayList<Step> getArrayList() {
        return arrayList;
    }
}
//todo new classes to tik project