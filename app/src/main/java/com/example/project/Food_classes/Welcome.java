package com.example.project.Food_classes;

import com.fasterxml.jackson.annotation.*;
import java.util.List;

public class Welcome {
    private long totalHits;
    private long currentPage;
    private long totalPages;
    private List<Long> pageList;
    private FoodSearchCriteria foodSearchCriteria;
    private List<Food> foods;
    private Aggregations aggregations;

    @JsonProperty("totalHits")
    public long getTotalHits() { return totalHits; }
    @JsonProperty("totalHits")
    public void setTotalHits(long value) { this.totalHits = value; }

    @JsonProperty("currentPage")
    public long getCurrentPage() { return currentPage; }
    @JsonProperty("currentPage")
    public void setCurrentPage(long value) { this.currentPage = value; }

    @JsonProperty("totalPages")
    public long getTotalPages() { return totalPages; }
    @JsonProperty("totalPages")
    public void setTotalPages(long value) { this.totalPages = value; }

    @JsonProperty("pageList")
    public List<Long> getPageList() { return pageList; }
    @JsonProperty("pageList")
    public void setPageList(List<Long> value) { this.pageList = value; }

    @JsonProperty("foodSearchCriteria")
    public FoodSearchCriteria getFoodSearchCriteria() { return foodSearchCriteria; }
    @JsonProperty("foodSearchCriteria")
    public void setFoodSearchCriteria(FoodSearchCriteria value) { this.foodSearchCriteria = value; }

    @JsonProperty("foods")
    public List<Food> getFoods() { return foods; }
    @JsonProperty("foods")
    public void setFoods(List<Food> value) { this.foods = value; }

    @JsonProperty("aggregations")
    public Aggregations getAggregations() { return aggregations; }
    @JsonProperty("aggregations")
    public void setAggregations(Aggregations value) { this.aggregations = value; }
}
