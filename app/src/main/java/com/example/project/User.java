package com.example.project;

public class User {
    String name;
    private String id;
    String email;
    int weiht;
    int height;
    int age;
    String gender;
    int activity;
    int pregnant;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getWeiht() {
        return weiht;
    }

    public void setWeiht(int weiht) {
        this.weiht = weiht;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    public int getPregnant() {
        return pregnant;
    }

    public void setPregnant(int pregnant) {
        this.pregnant = pregnant;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
