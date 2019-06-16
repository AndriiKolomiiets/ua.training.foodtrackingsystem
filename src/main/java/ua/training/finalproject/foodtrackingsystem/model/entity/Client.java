package ua.training.finalproject.foodtrackingsystem.model.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Client implements Entity{
    private Long id;
    private LocalDate birthDate;
    private Integer weight;
    private Integer height;
    private Integer lifeStyleCoefficient;
    private Integer caloriesNorm;
    private User user;

    private List<DayMeal> dayMealList = new ArrayList<>();
    private List<ClientTrack> clientTrackList = new ArrayList<>();

    public Client(User user, Long id, LocalDate birthDate, Integer weight, Integer height, Integer lifeStyleCoefficient, Integer caloriesNorm) {
        this.user = user;
        this.id = id;
        this.birthDate = birthDate;
        this.weight = weight;
        this.height = height;
        this.lifeStyleCoefficient = lifeStyleCoefficient;
        this.caloriesNorm = caloriesNorm;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Client(User user, Long id, LocalDate birthDate, Integer weight, Integer height, Integer lifeStyleCoefficient,
                  Integer caloriesNorm, List<DayMeal> dayMealList, List<ClientTrack> clientTrackList) {
        this.user = user;

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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
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

    @Override
    public String toString(){
        return "ID: " + getId()+
                "\nBirthDate" + getBirthDate() +
                "\nWeight" + getWeight() +
                "\nHeight" + getHeight() +
                "\nLifeStyleCoefficient" + getLifeStyleCoefficient() +
                "\nCaloriesNorm" + getCaloriesNorm() +
                "\nUser" + getUser() +
                "\nDayMealList" + getDayMealList() +
                "\nClientTrackList" + getClientTrackList();
    }
}
