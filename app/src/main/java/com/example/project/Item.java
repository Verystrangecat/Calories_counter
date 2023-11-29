package com.example.project;

public class Item {
    String food_name;
    String Brand;
    int calorie;

    public Item(String food_name, String brand, int calorie) {
        this.food_name = food_name;
        Brand = brand;
        this.calorie = calorie;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }
}
