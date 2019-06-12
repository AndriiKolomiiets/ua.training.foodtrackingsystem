package ua.training.finalproject.foodtrackingsystem.model.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Client implements Entity{
    private Long id;
    private Date birthDate;
    private Integer weight;
    private Integer height;
    private Integer lifeStyleCoefficient;
    private Integer caloriesNorm;

    private List<DayMeal> dayMealList = new ArrayList<>();
    private List<ClientTrack> clientTrackList = new ArrayList<>();

    public Client(Long id, Date birthDate, Integer weight, Integer height, Integer lifeStyleCoefficient, Integer caloriesNorm) {
        this.id = id;
        this.birthDate = birthDate;
        this.weight = weight;
        this.height = height;
        this.lifeStyleCoefficient = lifeStyleCoefficient;
        this.caloriesNorm = caloriesNorm;
    }

    public Client(Long id, Date birthDate, Integer weight, Integer height, Integer lifeStyleCoefficient,
                  Integer caloriesNorm, List<DayMeal> dayMealList, List<ClientTrack> clientTrackList) {
        this.id = id;
        this.birthDate = birthDate;
        this.weight = weight;
        this.height = height;
        this.lifeStyleCoefficient = lifeStyleCoefficient;
        this.caloriesNorm = caloriesNorm;
        this.dayMealList = dayMealList;
        this.clientTrackList = clientTrackList;
    }

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getLifeStyleCoefficient() {
        return lifeStyleCoefficient;
    }

    public void setLifeStyleCoefficient(Integer lifeStyleCoefficient) {
        this.lifeStyleCoefficient = lifeStyleCoefficient;
    }

    public Integer getCaloriesNorm() {
        return caloriesNorm;
    }

    public void setCaloriesNorm(Integer caloriesNorm) {
        this.caloriesNorm = caloriesNorm;
    }

    public List<DayMeal> getDayMealList() {
        return dayMealList;
    }

    public void setDayMealList(List<DayMeal> dayMealList) {
        this.dayMealList = dayMealList;
    }

    public List<ClientTrack> getClientTrackList() {
        return clientTrackList;
    }

    public void setClientTrackList(List<ClientTrack> clientTrackList) {
        this.clientTrackList = clientTrackList;
    }
}
