package ua.training.finalproject.foodtrackingsystem.model.entity;

import java.time.LocalDateTime;
import java.util.List;

public class DayMeal implements Entity{
    private Long id;
//    private Client client;
    private LocalDateTime dateTime;
    private List<Food> foodList;
    private Integer number;
    private String caloriesStatus;
    private Integer caloriesToNorm;

    public DayMeal(Long id, LocalDateTime dateTime, List<Food> foodList, Integer number, String caloriesStatus) {
        this.id = id;
        this.dateTime = dateTime;
        this.foodList = foodList;
        this.number = number;
        this.caloriesStatus = caloriesStatus;
    }

    public DayMeal(Long id, LocalDateTime dateTime, List<Food> foodList, Integer number, String caloriesStatus, Integer caloriesToNorm) {
        this.id = id;
        this.dateTime = dateTime;
        this.foodList = foodList;
        this.number = number;
        this.caloriesStatus = caloriesStatus;
        this.caloriesToNorm = caloriesToNorm;
    }

    public DayMeal() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCaloriesStatus() {
        return caloriesStatus;
    }

    public void setCaloriesStatus(String caloriesStatus) {
        this.caloriesStatus = caloriesStatus;
    }

    public Integer getCaloriesToNorm() {
        return caloriesToNorm;
    }

    public void setCaloriesToNorm(Integer caloriesToNorm) {
        this.caloriesToNorm = caloriesToNorm;
    }
}
