package ua.training.finalproject.foodtrackingsystem.model.service.daymeal;

import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.DayMealDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;

import java.util.List;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class GetDayMealListService {
    public List<DayMeal> getDayMealList(Client client) {
        DaoFactory daoFactory;
        daoFactory = JdbcDaoFactory.getInstance();
        List<DayMeal> dayMealList;
        try(DayMealDao dayMealDao = daoFactory.createDayMealDao()) {
            dayMealList = dayMealDao.findDayMealListByClient(client);
        }
        return dayMealList;
    }
}
