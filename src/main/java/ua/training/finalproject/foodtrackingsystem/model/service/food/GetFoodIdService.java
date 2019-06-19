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
public class GetFoodIdService {
    public Long getIdByName(String foodName) {
        DaoFactory daoFactory = JdbcDaoFactory.getInstance();
        Long foodId = null;
        Optional<Food> optionalFood;
        try(FoodDao foodDao = daoFactory.createFoodDao()) {
           optionalFood = foodDao.findByName(foodName);
        }
        if (optionalFood.isPresent()) {
            foodId = optionalFood.get().getId();
        }
        return foodId;
    }
}
