package ua.training.finalproject.foodtrackingsystem.model.service;

import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class CalcCaloriesToNormService {
    public Integer calculate(Integer caloriesNorm, List<DayMeal> dayMealList) {
        int calories = 0;
        if (dayMealList==null){
            return caloriesNorm;
        }
        for (DayMeal dayMeal : dayMealList) {
            calories += dayMeal.getFood().getCalories();
        }
       return caloriesNorm - calories;
    }
}
