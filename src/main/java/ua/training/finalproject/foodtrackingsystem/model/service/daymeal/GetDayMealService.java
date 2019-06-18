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

public class GetDayMealService {
    private static final Logger log = Logger.getLogger(GetDayMealService.class);

    public Optional<DayMeal> getDayMealByClient(Client client) {
        DaoFactory daoFactory = JdbcDaoFactory.getInstance();
        DayMealDao dayMealDao = daoFactory.createDayMealDao();
        Optional<DayMeal> dayMealByClient;
        dayMealByClient = dayMealDao.findDayMealByClient(client);
        dayMealDao.close();
        return dayMealByClient;
    }

    public Optional<DayMeal> getDayMealById(Long id) {
        DaoFactory daoFactory = JdbcDaoFactory.getInstance();
        DayMealDao dayMealDao = daoFactory.createDayMealDao();
        Optional<DayMeal> dayMealByClient;
        dayMealByClient = dayMealDao.findById(id);
        dayMealDao.close();
        return dayMealByClient;
    }

    public DayMeal createAndGet(Client client) {
        DaoFactory daoFactory = JdbcDaoFactory.getInstance();
        DayMealDao dayMealDao = daoFactory.createDayMealDao();

        Optional<DayMeal> dayMealByClient;
        DayMeal dayMeal = new DayMeal();
        dayMeal.setClient(client);
        try {
            dayMealDao.create(dayMeal);
        } catch (SQLException e) {
            //NOP
        } finally {
            dayMealDao.close();
        }
        dayMealByClient = dayMealDao.findDayMealByClient(client);
        if (!dayMealByClient.isPresent()) {
            log.error(LogMessages.LOG_DAY_MEAL_ERROR + "[" + client.getUser().getUsername() + "]");
        }

        return dayMealByClient.get();
    }
}
