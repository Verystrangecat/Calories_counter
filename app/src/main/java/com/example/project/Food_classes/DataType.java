package com.example.project.Food_classes;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;

public class DataType implements Serializable {
    private long branded;
    private long surveyFNDDS;
    private long srLegacy;
    private long foundation;

    @JsonProperty("Branded")
    public long getBranded() { return branded; }
    @JsonProperty("Branded")
    public void setBranded(long value) { this.branded = value; }

    @JsonProperty("Survey (FNDDS)")
    public long getSurveyFNDDS() { return surveyFNDDS; }
    @JsonProperty("Survey (FNDDS)")
    public void setSurveyFNDDS(long value) { this.surveyFNDDS = value; }

    @JsonProperty("SR Legacy")
    public long getSrLegacy() { return srLegacy; }
    @JsonProperty("SR Legacy")
    public void setSrLegacy(long value) { this.srLegacy = value; }

    @JsonProperty("Foundation")
    public long getFoundation() { return foundation; }
    @JsonProperty("Foundation")
    public void setFoundation(long value) { this.foundation = value; }
}