package com.example.project.Food_classes;

import com.fasterxml.jackson.annotation.*;

public class Aggregations {
    private DataType dataType;
    private Nutrients nutrients;

    @JsonProperty("dataType")
    public DataType getDataType() { return dataType; }
    @JsonProperty("dataType")
    public void setDataType(DataType value) { this.dataType = value; }

    @JsonProperty("nutrients")
    public Nutrients getNutrients() { return nutrients; }
    @JsonProperty("nutrients")
    public void setNutrients(Nutrients value) { this.nutrients = value; }
}