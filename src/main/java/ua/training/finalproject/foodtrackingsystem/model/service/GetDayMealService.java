package ua.training.finalproject.foodtrackingsystem.model.service;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.DayMealDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;

import java.util.Optional;

public class GetDayMealService {
    private DaoFactory daoFactory = JdbcDaoFactory.getInstance();
    private DayMealDao dayMealDao = daoFactory.createDayMealDao();
    private static final Logger log = Logger.getLogger(GetDayMealService.class);
    private Optional<DayMeal> dayMealByClient;

    public Optional<DayMeal> getDayMealByClient(Client client) {
        /*DaoFactory daoFactory;
        daoFactory = JdbcDaoFactory.getInstance();
        DayMealDao dayMealDao = daoFactory.createDayMealDao();*/
        dayMealByClient = dayMealDao.findDayMealByClient(client);
        dayMealDao.close();
        return dayMealByClient;
    }

    public DayMeal createAndGet(Client client) {
        DayMeal dayMeal = new DayMeal();
        dayMeal.setClient(client);
        dayMealDao.create(dayMeal);
        dayMealByClient = dayMealDao.findDayMealByClient(client);
        if (!dayMealByClient.isPresent()) {
            log.error(LogMessages.LOG_DAY_MEAL_ERROR + "[" + client.getUser().getUsername() + "]");
        }
        dayMealDao.close();
        return dayMealByClient.get();
    }
}
