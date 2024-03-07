package com.example.project;

public class Food_class_meals {
    String calories, proteins, fats, carbs, portion, brand, name, meal;

    /**
     * constructor
     * everything is string
     * @param calories
     * @param proteins
     * @param fats
     * @param carbs
     * @param portion
     * @param brand
     * @param name
     * @param meal
     */

    public Food_class_meals(String calories, String proteins, String fats, String carbs, String portion, String brand, String name,String meal) {
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbs = carbs;
        this.portion = portion;
        this.brand = brand;
        this.name = name;
        this.meal=meal;
    }

    /**
     * Gets the calories for the food item.
     *
     * @return A string representing the calories for the food item.
     */
    public String getCalories() {
        return calories;
    }

    /**
     * Sets the calories for the food item.
     *
     * @param calories The calories to set for the food item.
     */
    public void setCalories(String calories) {
        this.calories = calories;
    }

    /**
     * Gets the proteins for the food item.
     *
     * @return A string representing the proteins for the food item.
     */
    public String getProteins() {
        return proteins;
    }

    /**
     * Sets the proteins for the food item.
     *
     * @param proteins The proteins to set for the food item.
     */
    public void setProteins(String proteins) {
        this.proteins = proteins;
    }

    /**
     * Gets the fats for the food item.
     *
     * @return A string representing the fats for the food item.
     */
    public String getFats() {
        return fats;
    }

    /**
     * Sets the fats for the food item.
     *
     * @param fats The fats to set for the food item.
     */
    public void setFats(String fats) {
        this.fats = fats;
    }

    /**
     * Gets the carbohydrates for the food item.
     *
     * @return A string representing the carbohydrates for the food item.
     */
    public String getCarbs() {
        return carbs;
    }

    /**
     * Sets the carbohydrates for the food item.
     *
     * @param carbs The carbohydrates to set for the food item.
     */
    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    /**
     * Gets the portion size for the food item.
     *
     * @return A string representing the portion size for the food item.
     */
    public String getPortion() {
        return portion;
    }

    /**
     * Sets the portion size for the food item.
     *
     * @param portion The portion size to set for the food item.
     */
    public void setPortion(String portion) {
        this.portion = portion;
    }

    /**
     * Gets the brand of the food item.
     *
     * @return A string representing the brand of the food item.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the brand of the food item.
     *
     * @param brand The brand to set for the food item.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Gets the name of the food item.
     *
     * @return A string representing the name of the food item.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the food item.
     *
     * @param name The name to set for the food item.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the type of meal associated with the food item.
     *
     * @return A string representing the type of meal for the food item.
     */
    public String getMeal() {
        return meal;
    }

    /**
     * Sets the type of meal for the food item.
     *
     * @param meal The type of meal to set for the food item.
     */
    public void setMeal(String meal) {
        this.meal = meal;
    }

}
