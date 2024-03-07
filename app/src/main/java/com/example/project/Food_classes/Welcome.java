package com.example.project.Food_classes;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.List;

public class Welcome implements Serializable {
    private List<Food> foods;

    /**
     *
     * @return list of foods
     */
    @JsonProperty("foods")
    public List<Food> getFoods() { return foods; }

    /**
     *
     * @param value list of foods
     */
    @JsonProperty("foods")
    public void setFoods(List<Food> value) { this.foods = value; }

}
