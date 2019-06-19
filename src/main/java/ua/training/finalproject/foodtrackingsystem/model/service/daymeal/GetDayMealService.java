package ua.training.finalproject.foodtrackingsystem.model.service.daymeal;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.DayMealDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;

import java.sql.SQLException;
import java.util.Optional;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class GetDayMealService {
    private static final Logger log = Logger.getLogger(GetDayMealService.class);

    public Optional<DayMeal> getDayMealByClient(Client client) {
        DaoFactory daoFactory = JdbcDaoFactory.getInstance();
        Optional<DayMeal> dayMealByClient;
        try(DayMealDao dayMealDao = daoFactory.createDayMealDao()) {
            dayMealByClient = dayMealDao.findDayMealByClient(client);
        }
        return dayMealByClient;
    }

    public Optional<DayMeal> getDayMealById(Long id) {
        DaoFactory daoFactory = JdbcDaoFactory.getInstance();
        Optional<DayMeal> dayMealByClient;
        try(DayMealDao dayMealDao = daoFactory.createDayMealDao()) {
            dayMealByClient = dayMealDao.findById(id);
        }
        return dayMealByClient;
    }

    public DayMeal createAndGet(Client client) {
        DaoFactory daoFactory = JdbcDaoFactory.getInstance();
        Optional<DayMeal> dayMealByClient = Optional.empty();
        DayMeal dayMeal = new DayMeal();
        dayMeal.setClient(client);
        try(DayMealDao dayMealDao = daoFactory.createDayMealDao()) {
            dayMealDao.create(dayMeal);
            dayMealByClient = dayMealDao.findDayMealByClient(client);
            if (!dayMealByClient.isPresent()) {
                log.error(LogMessages.LOG_DAY_MEAL_ERROR + "[" + client.getUser().getUsername() + "]");
            }
        } catch (SQLException e) {
            //NOP
        }
        return dayMealByClient.get();
    }
}
