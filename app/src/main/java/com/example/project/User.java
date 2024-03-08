package com.example.project;

public class User {
    String name;
    private String id;
    String email;
    int weight;
    int height;
    int age;
    String gender;
    int activity;
    int pregnant;
    int calories;
    double protein;
    double carbs;
    double fat;

    /**
     * constructor that i need for the firebase
     */

    public User() {
    }

    /**
     * Gets the name of the user.
     *
     * @return A string representing the user's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name The name to set for the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the weight of the user.
     *
     * @return An integer representing the user's weight.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Sets the weight of the user.
     *
     * @param weight The weight to set for the user.
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Gets the height of the user.
     *
     * @return An integer representing the user's height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the user.
     *
     * @param height The height to set for the user.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Gets the age of the user.
     *
     * @return An integer representing the user's age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the user.
     *
     * @param age The age to set for the user.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the gender of the user.
     *
     * @return A string representing the user's gender.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender of the user.
     *
     * @param gender The gender to set for the user.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets the activity level of the user.
     *
     * @return An integer representing the user's activity level.
     */
    public int getActivity() {
        return activity;
    }

    /**
     * Sets the activity level of the user.
     *
     * @param activity The activity level to set for the user.
     */
    public void setActivity(int activity) {
        this.activity = activity;
    }

    /**
     * Gets the pregnancy status of the user.
     *
     * @return An integer representing the user's pregnancy status.
     */
    public int getPregnant() {
        return pregnant;
    }

    /**
     * Sets the pregnancy status of the user.
     *
     * @param pregnant The pregnancy status to set for the user.
     */
    public void setPregnant(int pregnant) {
        this.pregnant = pregnant;
    }

    /**
     * Gets the email address of the user.
     *
     * @return A string representing the user's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email The email address to set for the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the unique identifier (ID) of the user.
     *
     * @return A string representing the user's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier (ID) of the user.
     *
     * @param id The ID to set for the user.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the calorie content of the food.
     *
     * @return An integer representing the calories.
     */
    public int getCalories() {
        return calories;
    }

    /**
     * Sets the calorie content of the food.
     *
     * @param calories The calories to set for the food.
     */
    public void setCalories(int calories) {
        this.calories = calories;
    }

    /**
     * Gets the protein content of the food.
     *
     * @return A double representing the protein content.
     */
    public double getProtein() {
        return protein;
    }

    /**
     * Sets the protein content of the food.
     *
     * @param protein The protein content to set for the food.
     */
    public void setProtein(double protein) {
        this.protein = protein;
    }

    /**
     * Gets the carbohydrate content of the food.
     *
     * @return A double representing the carbohydrate content.
     */
    public double getCarbs() {
        return carbs;
    }

    /**
     * Sets the carbohydrate content of the food.
     *
     * @param carbs The carbohydrate content to set for the food.
     */
    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    /**
     * Gets the fat content of the food.
     *
     * @return A double representing the fat content.
     */
    public double getFat() {
        return fat;
    }

    /**
     * Sets the fat content of the food.
     *
     * @param fat The fat content to set for the food.
     */
    public void setFat(double fat) {
        this.fat = fat;
    }
}
