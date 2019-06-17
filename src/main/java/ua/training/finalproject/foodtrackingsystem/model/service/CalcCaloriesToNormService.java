package ua.training.finalproject.foodtrackingsystem.model.service;

import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;

import java.util.List;

public class CalcCaloriesToNormService {
    public Integer calculate(Integer caloriesNorm, List<DayMeal> dayMealList) {
        int calories = 0;
        if (dayMealList==null){
            return caloriesNorm;
        }
        for (DayMeal dayMeal : dayMealList) {
            calories += (dayMeal.getFood().getCalories()*dayMeal.getNumber());
        }
       return caloriesNorm - calories;
    }
}
