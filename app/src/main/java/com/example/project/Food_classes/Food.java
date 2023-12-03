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

    @JsonProperty("fdcId")
    public long getFDCID() { return fdcID; }
    @JsonProperty("fdcId")
    public void setFDCID(long value) { this.fdcID = value; }

    @JsonProperty("description")
    public String getDescription() { return description; }
    @JsonProperty("description")
    public void setDescription(String value) { this.description = value; }

    @JsonProperty("dataType")
    public String getDataType() { return dataType; }
    @JsonProperty("dataType")
    public void setDataType(String value) { this.dataType = value; }

    @JsonProperty("gtinUpc")
    public String getGtinUpc() { return gtinUpc; }
    @JsonProperty("gtinUpc")
    public void setGtinUpc(String value) { this.gtinUpc = value; }

    @JsonProperty("publishedDate")
    public LocalDate getPublishedDate() { return publishedDate; }
    @JsonProperty("publishedDate")
    public void setPublishedDate(LocalDate value) { this.publishedDate = value; }

    @JsonProperty("brandOwner")
    public String getBrandOwner() { return brandOwner; }
    @JsonProperty("brandOwner")
    public void setBrandOwner(String value) { this.brandOwner = value; }

    @JsonProperty("brandName")
    public String getBrandName() { return brandName; }
    @JsonProperty("brandName")
    public void setBrandName(String value) { this.brandName = value; }

    @JsonProperty("ingredients")
    public String getIngredients() { return ingredients; }
    @JsonProperty("ingredients")
    public void setIngredients(String value) { this.ingredients = value; }

    @JsonProperty("marketCountry")
    public String getMarketCountry() { return marketCountry; }
    @JsonProperty("marketCountry")
    public void setMarketCountry(String value) { this.marketCountry = value; }

    @JsonProperty("foodCategory")
    public String getFoodCategory() { return foodCategory; }
    @JsonProperty("foodCategory")
    public void setFoodCategory(String value) { this.foodCategory = value; }

    @JsonProperty("modifiedDate")
    public LocalDate getModifiedDate() { return modifiedDate; }
    @JsonProperty("modifiedDate")
    public void setModifiedDate(LocalDate value) { this.modifiedDate = value; }

    @JsonProperty("dataSource")
    public String getDataSource() { return dataSource; }
    @JsonProperty("dataSource")
    public void setDataSource(String value) { this.dataSource = value; }

    @JsonProperty("packageWeight")
    public String getPackageWeight() { return packageWeight; }
    @JsonProperty("packageWeight")
    public void setPackageWeight(String value) { this.packageWeight = value; }

    @JsonProperty("servingSizeUnit")
    public String getServingSizeUnit() { return servingSizeUnit; }
    @JsonProperty("servingSizeUnit")
    public void setServingSizeUnit(String value) { this.servingSizeUnit = value; }

    @JsonProperty("servingSize")
    public long getServingSize() { return servingSize; }
    @JsonProperty("servingSize")
    public void setServingSize(long value) { this.servingSize = value; }

    @JsonProperty("tradeChannels")
    public List<String> getTradeChannels() { return tradeChannels; }
    @JsonProperty("tradeChannels")
    public void setTradeChannels(List<String> value) { this.tradeChannels = value; }

    @JsonProperty("allHighlightFields")
    public String getAllHighlightFields() { return allHighlightFields; }
    @JsonProperty("allHighlightFields")
    public void setAllHighlightFields(String value) { this.allHighlightFields = value; }

    @JsonProperty("score")
    public double getScore() { return score; }
    @JsonProperty("score")
    public void setScore(double value) { this.score = value; }

    @JsonProperty("microbes")
    public List<Object> getMicrobes() { return microbes; }
    @JsonProperty("microbes")
    public void setMicrobes(List<Object> value) { this.microbes = value; }

    @JsonProperty("foodNutrients")
    public List<FoodNutrient> getFoodNutrients() { return foodNutrients; }
    @JsonProperty("foodNutrients")
    public void setFoodNutrients(List<FoodNutrient> value) { this.foodNutrients = value; }

    @JsonProperty("finalFoodInputFoods")
    public List<Object> getFinalFoodInputFoods() { return finalFoodInputFoods; }
    @JsonProperty("finalFoodInputFoods")
    public void setFinalFoodInputFoods(List<Object> value) { this.finalFoodInputFoods = value; }

    @JsonProperty("foodMeasures")
    public List<Object> getFoodMeasures() { return foodMeasures; }
    @JsonProperty("foodMeasures")
    public void setFoodMeasures(List<Object> value) { this.foodMeasures = value; }

    @JsonProperty("foodAttributes")
    public List<Object> getFoodAttributes() { return foodAttributes; }
    @JsonProperty("foodAttributes")
    public void setFoodAttributes(List<Object> value) { this.foodAttributes = value; }

    @JsonProperty("foodAttributeTypes")
    public List<Object> getFoodAttributeTypes() { return foodAttributeTypes; }
    @JsonProperty("foodAttributeTypes")
    public void setFoodAttributeTypes(List<Object> value) { this.foodAttributeTypes = value; }

    @JsonProperty("foodVersionIds")
    public List<Object> getFoodVersionIDS() { return foodVersionIDS; }
    @JsonProperty("foodVersionIds")
    public void setFoodVersionIDS(List<Object> value) { this.foodVersionIDS = value; }
}
