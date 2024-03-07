package com.example.project.Food_classes;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Food implements Serializable {
    private long fdcID;
    private String description;
    private String dataType;
    private String gtinUpc;
    private LocalDate publishedDate;
    private String brandOwner;
    private String brandName;
    private String ingredients;
    private String marketCountry;
    private String foodCategory;
    private LocalDate modifiedDate;
    private String dataSource;
    private String packageWeight;
    private String servingSizeUnit;
    private long servingSize;
    private List<String> tradeChannels;
    private String allHighlightFields;
    private double score;
    private List<Object> microbes;
    private List<FoodNutrient> foodNutrients;
    private List<Object> finalFoodInputFoods;
    private List<Object> foodMeasures;
    private List<Object> foodAttributes;
    private List<Object> foodAttributeTypes;
    private List<Object> foodVersionIDS;

    /**
     *
     * @return long fdcID
     * object property
     */
    @JsonProperty("fdcId")
    public long getFDCID() { return fdcID; }

    /**
     *
     * @param value-long fdcID
     */
    @JsonProperty("fdcId")
    public void setFDCID(long value) { this.fdcID = value; }

    /**
     * Gets the description of the food.
     *
     * @return The description of the food.
     */
    @JsonProperty("description")
    public String getDescription() { return description; }

    /**
     * Sets the description of the food.
     *
     * @param value The description to set for the food.
     */
    @JsonProperty("description")
    public void setDescription(String value) { this.description = value; }

    /**
     * Gets the data type of the food.
     *
     * @return The data type of the food.
     */
    @JsonProperty("dataType")
    public String getDataType() { return dataType; }

    /**
     * Sets the data type of the food.
     *
     * @param value The data type to set for the food.
     */
    @JsonProperty("dataType")
    public void setDataType(String value) { this.dataType = value; }

    /**
     * Gets the GTIN/UPC code of the food.
     *
     * @return The GTIN/UPC code of the food.
     */
    @JsonProperty("gtinUpc")
    public String getGtinUpc() { return gtinUpc; }

    /**
     * Sets the GTIN/UPC code of the food.
     *
     * @param value The GTIN/UPC code to set for the food.
     */
    @JsonProperty("gtinUpc")
    public void setGtinUpc(String value) { this.gtinUpc = value; }

    /**
     * Gets the published date of the food.
     *
     * @return The published date of the food.
     */
    @JsonProperty("publishedDate")
    public LocalDate getPublishedDate() { return publishedDate; }

    /**
     * Sets the published date of the food.
     *
     * @param value The published date to set for the food.
     */
    @JsonProperty("publishedDate")
    public void setPublishedDate(LocalDate value) { this.publishedDate = value; }

    /**
     * Gets the brand owner of the food.
     *
     * @return The brand owner of the food.
     */
    @JsonProperty("brandOwner")
    public String getBrandOwner() { return brandOwner; }

    /**
     * Sets the brand owner of the food.
     *
     * @param value The brand owner to set for the food.
     */
    @JsonProperty("brandOwner")
    public void setBrandOwner(String value) { this.brandOwner = value; }

    /**
     * Gets the brand name of the food.
     *
     * @return The brand name of the food.
     */
    @JsonProperty("brandName")
    public String getBrandName() { return brandName; }

    /**
     * Sets the brand name of the food.
     *
     * @param value The brand name to set for the food.
     */
    @JsonProperty("brandName")
    public void setBrandName(String value) { this.brandName = value; }

    /**
     * Gets the ingredients of the food.
     *
     * @return The ingredients of the food.
     */
    @JsonProperty("ingredients")
    public String getIngredients() { return ingredients; }

    /**
     * Sets the ingredients of the food.
     *
     * @param value The ingredients to set for the food.
     */
    @JsonProperty("ingredients")
    public void setIngredients(String value) { this.ingredients = value; }

    /**
     * Gets the market country of the food.
     *
     * @return The market country of the food.
     */
    @JsonProperty("marketCountry")
    public String getMarketCountry() { return marketCountry; }

    /**
     * Sets the market country of the food.
     *
     * @param value The market country to set for the food.
     */
    @JsonProperty("marketCountry")
    public void setMarketCountry(String value) { this.marketCountry = value; }

    /**
     * Gets the food category.
     *
     * @return The food category.
     */
    @JsonProperty("foodCategory")
    public String getFoodCategory() { return foodCategory; }

    /**
     * Sets the food category.
     *
     * @param value The food category to set.
     */
    @JsonProperty("foodCategory")
    public void setFoodCategory(String value) { this.foodCategory = value; }

    /**
     * Gets the modified date of the food.
     *
     * @return The modified date of the food.
     */
    @JsonProperty("modifiedDate")
    public LocalDate getModifiedDate() { return modifiedDate; }

    /**
     * Sets the modified date of the food.
     *
     * @param value The modified date to set for the food.
     */
    @JsonProperty("modifiedDate")
    public void setModifiedDate(LocalDate value) { this.modifiedDate = value; }

    /**
     * Gets the data source of the food.
     *
     * @return The data source of the food.
     */
    @JsonProperty("dataSource")
    public String getDataSource() { return dataSource; }

    /**
     * Sets the data source of the food.
     *
     * @param value The data source to set for the food.
     */
    @JsonProperty("dataSource")
    public void setDataSource(String value) { this.dataSource = value; }

    /**
     * Gets the package weight of the food.
     *
     * @return The package weight of the food.
     */
    @JsonProperty("packageWeight")
    public String getPackageWeight() { return packageWeight; }

    /**
     * Sets the package weight of the food.
     *
     * @param value The package weight to set for the food.
     */
    @JsonProperty("packageWeight")
    public void setPackageWeight(String value) { this.packageWeight = value; }

    /**
     * Gets the serving size unit of the food.
     *
     * @return The serving size unit of the food.
     */
    @JsonProperty("servingSizeUnit")
    public String getServingSizeUnit() { return servingSizeUnit; }

    /**
     * Sets the serving size unit of the food.
     *
     * @param value The serving size unit to set for the food.
     */
    @JsonProperty("servingSizeUnit")
    public void setServingSizeUnit(String value) { this.servingSizeUnit = value; }

    /**
     * Gets the serving size of the food.
     *
     * @return The serving size of the food.
     */
    @JsonProperty("servingSize")
    public long getServingSize() { return servingSize; }

    /**
     * Sets the serving size of the food.
     *
     * @param value The serving size to set for the food.
     */
    @JsonProperty("servingSize")
    public void setServingSize(long value) { this.servingSize = value; }

    /**
     * Gets the trade channels associated with the food.
     *
     * @return The trade channels associated with the food.
     */
    @JsonProperty("tradeChannels")
    public List<String> getTradeChannels() { return tradeChannels; }

    /**
     * Sets the trade channels associated with the food.
     *
     * @param value The trade channels to set for the food.
     */
    @JsonProperty("tradeChannels")
    public void setTradeChannels(List<String> value) { this.tradeChannels = value; }

    /**
     * Gets all highlight fields associated with the food.
     *
     * @return All highlight fields associated with the food.
     */
    @JsonProperty("allHighlightFields")
    public String getAllHighlightFields() { return allHighlightFields; }

    /**
     * Sets all highlight fields associated with the food.
     *
     * @param value All highlight fields to set for the food.
     */
    @JsonProperty("allHighlightFields")
    public void setAllHighlightFields(String value) { this.allHighlightFields = value; }

    /**
     * Gets the score associated with the food.
     *
     * @return The score associated with the food.
     */
    @JsonProperty("score")
    public double getScore() { return score; }

    /**
     * Sets the score associated with the food.
     *
     * @param value The score to set for the food.
     */
    @JsonProperty("score")
    public void setScore(double value) { this.score = value; }

    /**
     * Gets the list of microbes associated with the food.
     *
     * @return The list of microbes associated with the food.
     */
    @JsonProperty("microbes")
    public List<Object> getMicrobes() { return microbes; }

    /**
     * Sets the list of microbes associated with the food.
     *
     * @param value The list of microbes to set for the food.
     */
    @JsonProperty("microbes")
    public void setMicrobes(List<Object> value) { this.microbes = value; }

    /**
     * Gets the list of food nutrients associated with the food.
     *
     * @return The list of food nutrients associated with the food.
     */
    @JsonProperty("foodNutrients")
    public List<FoodNutrient> getFoodNutrients() { return foodNutrients; }

    /**
     * Sets the list of food nutrients associated with the food.
     *
     * @param value The list of food nutrients to set for the food.
     */
    @JsonProperty("foodNutrients")
    public void setFoodNutrients(List<FoodNutrient> value) { this.foodNutrients = value; }

    /**
     * Gets the list of final food input foods associated with the food.
     *
     * @return The list of final food input foods associated with the food.
     */
    @JsonProperty("finalFoodInputFoods")
    public List<Object> getFinalFoodInputFoods() { return finalFoodInputFoods; }

    /**
     * Sets the list of final food input foods associated with the food.
     *
     * @param value The list of final food input foods to set for the food.
     */
    @JsonProperty("finalFoodInputFoods")
    public void setFinalFoodInputFoods(List<Object> value) { this.finalFoodInputFoods = value; }

    /**
     * Gets the list of food measures associated with the food.
     *
     * @return The list of food measures associated with the food.
     */
    @JsonProperty("foodMeasures")
    public List<Object> getFoodMeasures() { return foodMeasures; }

    /**
     * Sets the list of food measures associated with the food.
     *
     * @param value The list of food measures to set for the food.
     */
    @JsonProperty("foodMeasures")
    public void setFoodMeasures(List<Object> value) { this.foodMeasures = value; }

    /**
     * Gets the list of food attributes associated with the food.
     *
     * @return The list of food attributes associated with the food.
     */
    @JsonProperty("foodAttributes")
    public List<Object> getFoodAttributes() { return foodAttributes; }

    /**
     * Sets the list of food attributes associated with the food.
     *
     * @param value The list of food attributes to set for the food.
     */
    @JsonProperty("foodAttributes")
    public void setFoodAttributes(List<Object> value) { this.foodAttributes = value; }

    /**
     * Gets the list of food attribute types associated with the food.
     *
     * @return The list of food attribute types associated with the food.
     */
    @JsonProperty("foodAttributeTypes")
    public List<Object> getFoodAttributeTypes() { return foodAttributeTypes; }

    /**
     * Sets the list of food attribute types associated with the food.
     *
     * @param value The list of food attribute types to set for the food.
     */
    @JsonProperty("foodAttributeTypes")
    public void setFoodAttributeTypes(List<Object> value) { this.foodAttributeTypes = value; }

    /**
     * Gets the list of food version IDs associated with the food.
     *
     * @return The list of food version IDs associated with the food.
     */
    @JsonProperty("foodVersionIds")
    public List<Object> getFoodVersionIDS() { return foodVersionIDS; }

    /**
     * Sets the list of food version IDs associated with the food.
     *
     * @param value The list of food version IDs to set for the food.
     */
    @JsonProperty("foodVersionIds")
    public void setFoodVersionIDS(List<Object> value) { this.foodVersionIDS = value; }

}
