package ua.training.finalproject.foodtrackingsystem.model.entity;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class Food implements Entity {
    private Long id;
    private String foodName;
    private Integer calories;
    private Integer proteins;
    private Integer lipids;
    private Integer carbs;

    public Food(String foodName, Integer calories, Integer lipids, Integer carbs, Integer proteins) {
        this.foodName = foodName;
        this.calories = calories;
        this.proteins = proteins;
        this.lipids = lipids;
        this.carbs = carbs;
    }

    public Food() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getProteins() {
        return proteins;
    }

    public void setProteins(Integer proteins) {
        this.proteins = proteins;
    }

    public Integer getLipids() {
        return lipids;
    }

    public void setLipids(Integer lipids) {
        this.lipids = lipids;
    }

    public Integer getCarbs() {
        return carbs;
    }

    public void setCarbs(Integer carbs) {
        this.carbs = carbs;
    }

    @Override
    public String toString() {
        return "Id: " + getId() +
                "\nFood name: " + getFoodName() +
                "\nCalories: " + getCalories() +
                "\nProteins: " + getProteins() +
                "\nLipids: " + getLipids() +
                "\nCarbs: " + getCarbs();
    }
}
