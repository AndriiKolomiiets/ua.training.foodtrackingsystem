package ua.training.finalproject.foodtrackingsystem.model.service.daymeal;

import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.DayMealDao;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class DeleteDayMealService {
    public void deleteById(Long id) {
        DaoFactory daoFactory = JdbcDaoFactory.getInstance();
        try(DayMealDao dayMealDao = daoFactory.createDayMealDao()) {
            dayMealDao.delete(id);
        }
    }
}
