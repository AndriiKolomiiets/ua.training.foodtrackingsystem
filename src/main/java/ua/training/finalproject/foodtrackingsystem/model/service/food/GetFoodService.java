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
public class GetFoodService {
    public Optional<Food> getFoodById(Long id) {
        DaoFactory daoFactory;
        daoFactory = JdbcDaoFactory.getInstance();
        FoodDao foodDao = daoFactory.createFoodDao();
        Optional<Food> optionalFood = foodDao.findById(id);
        foodDao.close();
        return optionalFood;
    }
}
