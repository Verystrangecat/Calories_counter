package com.example.project.Food_classes;

import java.io.IOException;
import com.fasterxml.jackson.annotation.*;

public enum DerivationCode {
    LCCD, LCCS;

    @JsonValue
    public String toValue() {
        switch (this) {
            case LCCD: return "LCCD";
            case LCCS: return "LCCS";
        }
        return null;
    }

    @JsonCreator
    public static DerivationCode forValue(String value) throws IOException {
        if (value.equals("LCCD")) return LCCD;
        if (value.equals("LCCS")) return LCCS;
        else return LCCD;
        //throw new IOException("Cannot deserialize DerivationCode");
    }
}
