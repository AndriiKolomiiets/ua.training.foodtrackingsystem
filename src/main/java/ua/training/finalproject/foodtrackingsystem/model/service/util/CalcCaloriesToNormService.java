package ua.training.finalproject.foodtrackingsystem.model.service.util;

import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;

import java.util.List;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
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
