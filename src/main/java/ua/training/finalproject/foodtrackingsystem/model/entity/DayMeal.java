package ua.training.finalproject.foodtrackingsystem.model.entity;

import java.time.LocalDateTime;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class DayMeal implements Entity {
    private long id;
    private LocalDateTime dateTime;
    private Food food;
    private int number;
    private String caloriesStatus;
    private int caloriesToNorm;
    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public DayMeal(Long id, LocalDateTime dateTime, Food food, Integer number, String caloriesStatus) {
        this.id = id;
        this.dateTime = dateTime;
        this.food = food;
        this.number = number;
        this.caloriesStatus = caloriesStatus;
    }

    public DayMeal(Long id, LocalDateTime dateTime, Food food, Integer number, String caloriesStatus, Integer caloriesToNorm) {
        this.id = id;
        this.dateTime = dateTime;
        this.food = food;
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

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DayMeal temp = (DayMeal) obj;
        return number == temp.getNumber() &&
                (client == temp.getClient() || client != null &&
                        client.equals(temp.getClient())) &&
                caloriesToNorm == temp.getCaloriesToNorm() &&
                (dateTime == temp.getDateTime() || dateTime != null &&
                        dateTime.equals(temp.getDateTime())) &&
                (caloriesStatus == temp.getCaloriesStatus() || caloriesStatus != null &&
                        caloriesStatus.equals(temp.getCaloriesStatus())) &&
                (food == temp.food || food != null && food.equals(temp.getFood()));
    }

    @Override
    public int hashCode() {
        final int prime = 33;
        int result = 1;
        result = prime * result + ((client == null) ? 0 : client.hashCode());
        result = prime * result + number;
        result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
        result = prime * result + caloriesToNorm;
        result = prime * result + ((caloriesStatus == null) ? 0 : caloriesStatus.hashCode());
        result = prime * result + ((food == null) ? 0 : food.hashCode());
        return result;

    }
}
