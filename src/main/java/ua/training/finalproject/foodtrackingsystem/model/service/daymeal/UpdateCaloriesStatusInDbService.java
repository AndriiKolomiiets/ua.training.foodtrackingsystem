package ua.training.finalproject.foodtrackingsystem.model.service.daymeal;

import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.DayMealDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class UpdateCaloriesStatusInDbService {
    public void updateAll(DayMeal dayMeal){
        DaoFactory daoFactory = JdbcDaoFactory.getInstance();
        DayMealDao dayMealDao = daoFactory.createDayMealDao();
        dayMealDao.updateAll(dayMeal);
        dayMealDao.close();
    }
}
