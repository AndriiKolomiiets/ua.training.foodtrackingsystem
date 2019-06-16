package ua.training.finalproject.foodtrackingsystem.model.service;

import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.FoodDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.Food;

import java.util.Optional;

public class AddNewFoodService {
    public void add(Food food){
        DaoFactory daoFactory = JdbcDaoFactory.getInstance();
        FoodDao foodDao = daoFactory.createFoodDao();
        foodDao.create(food);
        foodDao.close();
    }
}
