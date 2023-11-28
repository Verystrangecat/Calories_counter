package com.example.project.Food_classes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddedSugar {
    private double value;

    @JsonProperty("value")
    public double getValue() { return value; }
    @JsonProperty("value")
    public void setValue(double value) { this.value = value; }
}

