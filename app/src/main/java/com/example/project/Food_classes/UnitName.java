package com.example.project.Food_classes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.IOException;

public enum UnitName {
    G, IU, KCAL, MG;

    @JsonValue
    public String toValue() {
        switch (this) {
            case G:
                return "G";
            case IU:
                return "IU";
            case KCAL:
                return "KCAL";
            case MG:
                return "MG";
        }
        return null;
    }
    @JsonCreator
    public static UnitName forValue(String value) throws IOException {
        if (value.equals("G")) return G;
        if (value.equals("IU")) return IU;
        if (value.equals("KCAL")) return KCAL;
        if (value.equals("MG")) return MG;
        else return KCAL;
       // throw new IOException("Cannot deserialize UnitName");
    }
}