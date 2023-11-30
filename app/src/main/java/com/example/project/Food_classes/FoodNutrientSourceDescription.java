package com.example.project.Food_classes;

import java.io.IOException;
import com.fasterxml.jackson.annotation.*;

public enum FoodNutrientSourceDescription {
    MANUFACTURER_S_ANALYTICAL_PARTIAL_DOCUMENTATION;

    @JsonValue
    public String toValue() {
        switch (this) {
            case MANUFACTURER_S_ANALYTICAL_PARTIAL_DOCUMENTATION: return "Manufacturer's analytical; partial documentation";
        }
        return null;
    }

    @JsonCreator
    public static FoodNutrientSourceDescription forValue(String value) throws IOException {
        if (value.equals("Manufacturer's analytical; partial documentation")) return MANUFACTURER_S_ANALYTICAL_PARTIAL_DOCUMENTATION;
        throw new IOException("Cannot deserialize FoodNutrientSourceDescription");
    }
}

