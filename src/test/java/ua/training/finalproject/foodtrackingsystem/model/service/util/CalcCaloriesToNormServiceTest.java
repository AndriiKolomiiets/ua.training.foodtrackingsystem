package ua.training.finalproject.foodtrackingsystem.model.service.util;

import org.junit.Before;
import org.junit.Test;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;
import ua.training.finalproject.foodtrackingsystem.model.entity.Food;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CalcCaloriesToNormServiceTest {
    private Integer caloriesNorm;
    private List<DayMeal> dayMealList;

    @Before
    public void setUp() {
        Food food= new Food();
        DayMeal dayMeal= new DayMeal();
        DayMeal dayMeal1 = new DayMeal();
        DayMeal dayMeal2 = new DayMeal();
        dayMealList = new ArrayList<>();
        food.setCalories(100);
        dayMeal.setNumber(3);
        dayMeal.setFood(food);
        dayMeal1.setFood(food);
        dayMeal1.setNumber(2);
        dayMeal2.setNumber(1);
        dayMeal2.setFood(food);
        caloriesNorm = 1876;
        dayMealList.add(dayMeal);
        dayMealList.add(dayMeal1);
        dayMealList.add(dayMeal2);
    }

    @Test
    public void calculate() {
        CalcCaloriesToNormService calcService = new CalcCaloriesToNormService();
        int testCaloriesToNorm = calcService.calculate(caloriesNorm,dayMealList);
        assertEquals(1276, testCaloriesToNorm);
    }
}