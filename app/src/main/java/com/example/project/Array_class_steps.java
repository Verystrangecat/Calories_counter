package com.example.project;

import java.util.ArrayList;

public class Array_class_steps {
    ArrayList<Integer> arrayList;

    public Array_class_steps(){
        arrayList=new ArrayList<>();
        arrayList.add(1000);
        arrayList.add(2000);
        //todo do not forget to delete the line above
    }
    public void addday(int steps){
        if(arrayList.size()>=7){
            arrayList.remove(0);
            arrayList.add(steps);
        }
        else
            arrayList.add(steps);
    }

    public ArrayList<Integer> getArrayList() {
        return arrayList;
    }
}
//todo the use case diagram (link in the moodle)
//todo new classes to tik project