package ua.training.finalproject.foodtrackingsystem.model.service.food;

import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.FoodDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.Food;

import java.util.Optional;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
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
