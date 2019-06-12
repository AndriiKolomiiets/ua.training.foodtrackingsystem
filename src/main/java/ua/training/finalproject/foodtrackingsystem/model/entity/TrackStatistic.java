package ua.training.finalproject.foodtrackingsystem.model.entity;

import java.time.LocalDateTime;
import java.util.List;

public class TrackStatistic implements Entity {
    private Long id;
    private List<Food> foodList;
    private Integer number;
    private LocalDateTime dateTime;

    public TrackStatistic(Long id, List<Food> foodList, Integer number, LocalDateTime dateTime) {
        this.id = id;
        this.foodList = foodList;
        this.number = number;
        this.dateTime = dateTime;
    }

    public TrackStatistic() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
