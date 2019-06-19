package ua.training.finalproject.foodtrackingsystem.model.service.util;

import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.DayMealDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class CheckTrackDateService {
    public List<DayMeal> checkAndGetExpiredDayMealTrack(DayMeal dayMeal) {
        DaoFactory daoFactory = JdbcDaoFactory.getInstance();
        DayMealDao foodDao = daoFactory.createDayMealDao();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime yesterday = now.minusHours(24);
        List<DayMeal> dayMealList = foodDao.findAllByDate(dayMeal, yesterday);
        foodDao.close();
        return dayMealList;

    }

}
