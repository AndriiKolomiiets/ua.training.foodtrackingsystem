package ua.training.finalproject.foodtrackingsystem.model.service;

import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.FoodDao;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.UserDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.Food;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;

import java.util.Optional;

public class CheckFoodService {

    public boolean isUnique(String foodName){
        DaoFactory daoFactory = JdbcDaoFactory.getInstance();
        FoodDao foodDao = daoFactory.createFoodDao();
        Optional<Food> optionalFood = foodDao.findByName(foodName);
        foodDao.close();
        if (optionalFood.get().getFoodName()==null){
            return false;
        }
        return optionalFood.get().getFoodName().equals(foodName);
    }
}
