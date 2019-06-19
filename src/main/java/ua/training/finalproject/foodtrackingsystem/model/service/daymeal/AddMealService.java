package ua.training.finalproject.foodtrackingsystem.model.service.daymeal;

import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.DayMealDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.ClientTrack;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;
import ua.training.finalproject.foodtrackingsystem.model.service.util.CheckTrackDateService;
import ua.training.finalproject.foodtrackingsystem.model.service.util.DayMealToTrackStatisticConverter;
import ua.training.finalproject.foodtrackingsystem.model.service.client.AddClientTrackService;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class AddMealService {
    public void addMeal(DayMeal dayMeal) {
        DaoFactory daoFactory = JdbcDaoFactory.getInstance();
        CheckTrackDateService checkTrackDate = new CheckTrackDateService();
        DayMealToTrackStatisticConverter dayMealToTrackStatisticConverter = new DayMealToTrackStatisticConverter();
        AddClientTrackService addClientTrackService = new AddClientTrackService();
        Set<ClientTrack> clientTrackSet;
        DeleteDayMealService deleteDayMealService;

        List<DayMeal> dayMealList = checkTrackDate.checkAndGetExpiredDayMealTrack(dayMeal);
        if (dayMealList!=null){
            clientTrackSet = dayMealToTrackStatisticConverter.convert(dayMealList);
            addClientTrackService.add(clientTrackSet);
            deleteDayMealService = new DeleteDayMealService();
            for (DayMeal meal:dayMealList) {
                deleteDayMealService.deleteById(meal.getId());
            }
        }

        try (DayMealDao dayMealDao = daoFactory.createDayMealDao()) {
            dayMealDao.create(dayMeal);
        } catch (SQLException e) {
            //NOP
        }
    }
}
