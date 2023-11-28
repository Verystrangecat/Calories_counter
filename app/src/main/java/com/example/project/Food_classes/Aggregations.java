package com.example.project.Food_classes;


import com.fasterxml.jackson.annotation.*;

public class Aggregations {
    private DataTypeClass dataType;
    private Nutrients nutrients;

    @JsonProperty("dataType")
    public DataTypeClass getDataType() { return dataType; }
    @JsonProperty("dataType")
    public void setDataType(DataTypeClass value) { this.dataType = value; }

    @JsonProperty("nutrients")
    public Nutrients getNutrients() { return nutrients; }
    @JsonProperty("nutrients")
    public void setNutrients(Nutrients value) { this.nutrients = value; }
}




