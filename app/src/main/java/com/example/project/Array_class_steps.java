package com.example.project;

import java.util.ArrayList;

public class Array_class_steps {
    ArrayList<Integer> arrayList;

    /**
     * constructor of arraylist that has to values
     */
    public Array_class_steps(){
        arrayList=new ArrayList<>();
        arrayList.add(1000);
        arrayList.add(2000);
        //todo do not forget to delete the line above
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
//todo the use case diagram (link in the moodle)
//todo new classes to tik project