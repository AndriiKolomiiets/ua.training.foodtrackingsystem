package ua.training.finalproject.foodtrackingsystem.model.service;

import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.DayMealDao;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.FoodDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;
import ua.training.finalproject.foodtrackingsystem.model.entity.TrackStatistic;

import java.time.LocalDateTime;
import java.util.List;

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
