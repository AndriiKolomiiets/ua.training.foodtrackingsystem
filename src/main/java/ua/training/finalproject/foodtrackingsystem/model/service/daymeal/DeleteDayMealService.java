package ua.training.finalproject.foodtrackingsystem.model.service.daymeal;

import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.DayMealDao;

public class DeleteDayMealService {
    public void deleteById(Long id) {
        DaoFactory daoFactory = JdbcDaoFactory.getInstance();
        DayMealDao dayMealDao = daoFactory.createDayMealDao();
        dayMealDao.delete(id);
        dayMealDao.close();
    }
}
