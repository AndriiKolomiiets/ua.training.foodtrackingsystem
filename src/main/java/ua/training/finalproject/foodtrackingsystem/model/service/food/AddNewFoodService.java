package ua.training.finalproject.foodtrackingsystem.model.service.food;

import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.FoodDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.Food;

import java.sql.SQLException;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class AddNewFoodService {
    public void add(Food food){
        DaoFactory daoFactory = JdbcDaoFactory.getInstance();
        try (FoodDao foodDao = daoFactory.createFoodDao()) {
            foodDao.create(food);
        } catch (SQLException e) {
           //NOP
        }

    }
}
