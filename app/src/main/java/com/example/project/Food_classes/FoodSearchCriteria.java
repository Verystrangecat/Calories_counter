package com.example.project.Food_classes;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.List;

public class FoodSearchCriteria implements Serializable {
    private List<Object> dataType;
    private String query;
    private String generalSearchInput;
    private long pageNumber;
    private long numberOfResultsPerPage;
    private long pageSize;
    private boolean requireAllWords;

    @JsonProperty("dataType")
    public List<Object> getDataType() { return dataType; }
    @JsonProperty("dataType")
    public void setDataType(List<Object> value) { this.dataType = value; }

    @JsonProperty("query")
    public String getQuery() { return query; }
    @JsonProperty("query")
    public void setQuery(String value) { this.query = value; }

    @JsonProperty("generalSearchInput")
    public String getGeneralSearchInput() { return generalSearchInput; }
    @JsonProperty("generalSearchInput")
    public void setGeneralSearchInput(String value) { this.generalSearchInput = value; }

    @JsonProperty("pageNumber")
    public long getPageNumber() { return pageNumber; }
    @JsonProperty("pageNumber")
    public void setPageNumber(long value) { this.pageNumber = value; }

    @JsonProperty("numberOfResultsPerPage")
    public long getNumberOfResultsPerPage() { return numberOfResultsPerPage; }
    @JsonProperty("numberOfResultsPerPage")
    public void setNumberOfResultsPerPage(long value) { this.numberOfResultsPerPage = value; }

    @JsonProperty("pageSize")
    public long getPageSize() { return pageSize; }
    @JsonProperty("pageSize")
    public void setPageSize(long value) { this.pageSize = value; }

    @JsonProperty("requireAllWords")
    public boolean getRequireAllWords() { return requireAllWords; }
    @JsonProperty("requireAllWords")
    public void setRequireAllWords(boolean value) { this.requireAllWords = value; }
}
