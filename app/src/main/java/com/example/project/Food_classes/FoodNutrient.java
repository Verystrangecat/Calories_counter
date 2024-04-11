package com.example.project.Food_classes;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * foodnutrient class has information about nutrients
 */
public class FoodNutrient implements Serializable {
    private long nutrientID;
    private String nutrientName;
    private String nutrientNumber;
    private double value;
    private long foodNutrientSourceID;
    private String foodNutrientSourceCode;
    private long rank;
    private long indentLevel;
    private long foodNutrientID;
    private Long percentDailyValue;

    /**
     * Gets the nutrient ID.
     *
     * @return The nutrient ID.
     */
    @JsonProperty("nutrientId")
    public long getNutrientID() {
        return nutrientID;
    }

    /**
     * Sets the nutrient ID.
     *
     * @param value The nutrient ID to set.
     */
    @JsonProperty("nutrientId")
    public void setNutrientID(long value) {
        this.nutrientID = value;
    }

    /**
     * Gets the nutrient name.
     *
     * @return The nutrient name.
     */
    @JsonProperty("nutrientName")
    public String getNutrientName() {
        return nutrientName;
    }

    /**
     * Sets the nutrient name.
     *
     * @param value The nutrient name to set.
     */
    @JsonProperty("nutrientName")
    public void setNutrientName(String value) {
        this.nutrientName = value;
    }

    /**
     * Gets the nutrient number.
     *
     * @return The nutrient number.
     */
    @JsonProperty("nutrientNumber")
    public String getNutrientNumber() {
        return nutrientNumber;
    }

    /**
     * Sets the nutrient number.
     *
     * @param value The nutrient number to set.
     */
    @JsonProperty("nutrientNumber")
    public void setNutrientNumber(String value) {
        this.nutrientNumber = value;
    }

    /**
     * Gets the value of the nutrient.
     *
     * @return The value of the nutrient.
     */
    @JsonProperty("value")
    public double getValue() {
        return value;
    }

    /**
     * Sets the value of the nutrient.
     *
     * @param value The value of the nutrient to set.
     */
    @JsonProperty("value")
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Gets the food nutrient source ID.
     *
     * @return The food nutrient source ID.
     */
    @JsonProperty("foodNutrientSourceId")
    public long getFoodNutrientSourceID() {
        return foodNutrientSourceID;
    }

    /**
     * Sets the food nutrient source ID.
     *
     * @param value The food nutrient source ID to set.
     */
    @JsonProperty("foodNutrientSourceId")
    public void setFoodNutrientSourceID(long value) {
        this.foodNutrientSourceID = value;
    }

    /**
     * Gets the food nutrient source code.
     *
     * @return The food nutrient source code.
     */
    @JsonProperty("foodNutrientSourceCode")
    public String getFoodNutrientSourceCode() {
        return foodNutrientSourceCode;
    }

    /**
     * Sets the food nutrient source code.
     *
     * @param value The food nutrient source code to set.
     */
    @JsonProperty("foodNutrientSourceCode")
    public void setFoodNutrientSourceCode(String value) {
        this.foodNutrientSourceCode = value;
    }

    /**
     * Gets the rank of the nutrient.
     *
     * @return The rank of the nutrient.
     */
    @JsonProperty("rank")
    public long getRank() {
        return rank;
    }

    /**
     * Sets the rank of the nutrient.
     *
     * @param value The rank of the nutrient to set.
     */
    @JsonProperty("rank")
    public void setRank(long value) {
        this.rank = value;
    }

    /**
     * Gets the indent level of the nutrient.
     *
     * @return The indent level of the nutrient.
     */
    @JsonProperty("indentLevel")
    public long getIndentLevel() {
        return indentLevel;
    }

    /**
     * Sets the indent level of the nutrient.
     *
     * @param value The indent level of the nutrient to set.
     */
    @JsonProperty("indentLevel")
    public void setIndentLevel(long value) {
        this.indentLevel = value;
    }

    /**
     * Gets the food nutrient ID.
     *
     * @return The food nutrient ID.
     */
    @JsonProperty("foodNutrientId")
    public long getFoodNutrientID() {
        return foodNutrientID;
    }

    /**
     * Sets the food nutrient ID.
     *
     * @param value The food nutrient ID to set.
     */
    @JsonProperty("foodNutrientId")
    public void setFoodNutrientID(long value) {
        this.foodNutrientID = value;
    }

    /**
     * Gets the percent daily value of the nutrient.
     *
     * @return The percent daily value of the nutrient.
     */
    @JsonProperty("percentDailyValue")
    public Long getPercentDailyValue() {
        return percentDailyValue;
    }

    /**
     * Sets the percent daily value of the nutrient.
     *
     * @param value The percent daily value of the nutrient to set.
     */
    @JsonProperty("percentDailyValue")
    public void setPercentDailyValue(Long value) {
        this.percentDailyValue = value;
    }
}

