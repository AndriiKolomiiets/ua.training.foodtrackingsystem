package ua.training.finalproject.foodtrackingsystem.model.service;

import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.DayMealDao;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.FoodDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;
import ua.training.finalproject.foodtrackingsystem.model.entity.Food;

import java.util.Optional;

public class AddMealService {
    public void addMeal(DayMeal dayMeal){
        DaoFactory daoFactory = JdbcDaoFactory.getInstance();
        DayMealDao dayMealDao = daoFactory.createDayMealDao();
        dayMealDao.create(dayMeal);
        dayMealDao.close();
    }
}
