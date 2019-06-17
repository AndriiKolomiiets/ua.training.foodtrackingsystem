package ua.training.finalproject.foodtrackingsystem.model.service;

import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.DayMealDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.ClientTrack;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;

import java.util.List;
import java.util.Set;

public class AddMealService {
    public void addMeal(DayMeal dayMeal) {
        DaoFactory daoFactory = JdbcDaoFactory.getInstance();
        DayMealDao dayMealDao = daoFactory.createDayMealDao();
        CheckTrackDateService checkTrackDate = new CheckTrackDateService();
        DayMealToTrackStatisticConverter dayMealToTrackStatisticConverter = new DayMealToTrackStatisticConverter();
        AddClientTrackService addClientTrackService = new AddClientTrackService();
        Set<ClientTrack> clientTrackSet;

        //<editor-fold desc="Client Track Block">
        List<DayMeal> dayMealList = checkTrackDate.checkAndGetExpiredDayMealTrack(dayMeal);
        if (dayMealList!=null){
            clientTrackSet = dayMealToTrackStatisticConverter.convert(dayMealList);
            addClientTrackService.add(clientTrackSet);
            DeleteDayMealService deleteDayMealService = new DeleteDayMealService();
            for (DayMeal meal:dayMealList) {
                deleteDayMealService.deleteById(meal.getId());
            }
        }
        //</editor-fold>

        dayMealDao.create(dayMeal);
        dayMealDao.close();
    }
}
