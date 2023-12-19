package com.example.project;

import android.app.Application;

public class My_information extends Application {
    double calories, proteins, fats, carbs;
    double cal_left, protein_left, fats_left, carb_left;
    public My_information(double calories, double proteins, double fats, double carbs) {
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbs = carbs;
        cal_left=calories;
        protein_left=proteins;
        fats_left=fats;
        carb_left=carbs;
    }
    public My_information(){
        this.calories = 0;
        this.proteins = 0;
        this.fats = 0;
        this.carbs = 0;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getCal_left() {
        return cal_left;
    }

    public void setCal_left(double cal_left) {
        this.cal_left = cal_left;
    }

    public double getProtein_left() {
        return protein_left;
    }

    public void setProtein_left(double protein_left) {
        this.protein_left = protein_left;
    }

    public double getFats_left() {
        return fats_left;
    }

    public void setFats_left(double fats_left) {
        this.fats_left = fats_left;
    }

    public double getCarb_left() {
        return carb_left;
    }

    public void setCarb_left(double carb_left) {
        this.carb_left = carb_left;
    }
}
