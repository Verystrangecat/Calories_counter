package com.example.project.Food_classes;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class FoodNutrient implements Serializable {
    private long nutrientID;
    private String nutrientName;
    private String nutrientNumber;
    private UnitName unitName;
    private DerivationCode derivationCode;
    private String derivationDescription;
    private long derivationID;
    private double value;
    private long foodNutrientSourceID;
    private String foodNutrientSourceCode;
    private FoodNutrientSourceDescription foodNutrientSourceDescription;
    private long rank;
    private long indentLevel;
    private long foodNutrientID;
    private Long percentDailyValue;

    @JsonProperty("nutrientId")
    public long getNutrientID() { return nutrientID; }
    @JsonProperty("nutrientId")
    public void setNutrientID(long value) { this.nutrientID = value; }

    @JsonProperty("nutrientName")
    public String getNutrientName() { return nutrientName; }
    @JsonProperty("nutrientName")
    public void setNutrientName(String value) { this.nutrientName = value; }

    @JsonProperty("nutrientNumber")
    public String getNutrientNumber() { return nutrientNumber; }
    @JsonProperty("nutrientNumber")
    public void setNutrientNumber(String value) { this.nutrientNumber = value; }

    @JsonProperty("unitName")
    public UnitName getUnitName() { return unitName; }
    @JsonProperty("unitName")
    public void setUnitName(UnitName value) { this.unitName = value; }

    @JsonProperty("derivationCode")
    public DerivationCode getDerivationCode() { return derivationCode; }
    @JsonProperty("derivationCode")
    public void setDerivationCode(DerivationCode value) { this.derivationCode = value; }

    @JsonProperty("derivationDescription")
    public String getDerivationDescription() { return derivationDescription; }
    @JsonProperty("derivationDescription")
    public void setDerivationDescription(String value) { this.derivationDescription = value; }

    @JsonProperty("derivationId")
    public long getDerivationID() { return derivationID; }
    @JsonProperty("derivationId")
    public void setDerivationID(long value) { this.derivationID = value; }

    @JsonProperty("value")
    public double getValue() { return value; }
    @JsonProperty("value")
    public void setValue(double value) { this.value = value; }

    @JsonProperty("foodNutrientSourceId")
    public long getFoodNutrientSourceID() { return foodNutrientSourceID; }
    @JsonProperty("foodNutrientSourceId")
    public void setFoodNutrientSourceID(long value) { this.foodNutrientSourceID = value; }

    @JsonProperty("foodNutrientSourceCode")
    public String getFoodNutrientSourceCode() { return foodNutrientSourceCode; }
    @JsonProperty("foodNutrientSourceCode")
    public void setFoodNutrientSourceCode(String value) { this.foodNutrientSourceCode = value; }

    @JsonProperty("foodNutrientSourceDescription")
    public FoodNutrientSourceDescription getFoodNutrientSourceDescription() { return foodNutrientSourceDescription; }
    @JsonProperty("foodNutrientSourceDescription")
    public void setFoodNutrientSourceDescription(FoodNutrientSourceDescription value) { this.foodNutrientSourceDescription = value; }

    @JsonProperty("rank")
    public long getRank() { return rank; }
    @JsonProperty("rank")
    public void setRank(long value) { this.rank = value; }

    @JsonProperty("indentLevel")
    public long getIndentLevel() { return indentLevel; }
    @JsonProperty("indentLevel")
    public void setIndentLevel(long value) { this.indentLevel = value; }

    @JsonProperty("foodNutrientId")
    public long getFoodNutrientID() { return foodNutrientID; }
    @JsonProperty("foodNutrientId")
    public void setFoodNutrientID(long value) { this.foodNutrientID = value; }

    @JsonProperty("percentDailyValue")
    public Long getPercentDailyValue() { return percentDailyValue; }
    @JsonProperty("percentDailyValue")
    public void setPercentDailyValue(Long value) { this.percentDailyValue = value; }
}

