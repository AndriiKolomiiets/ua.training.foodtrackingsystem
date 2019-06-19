package ua.training.finalproject.foodtrackingsystem.model.service.client;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.ClientDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;
import ua.training.finalproject.foodtrackingsystem.model.service.util.CalcCaloriesNormService;
import ua.training.finalproject.foodtrackingsystem.model.service.daymeal.GetDayMealService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class AddClientService {
    private static final Logger log = Logger.getLogger(AddClientService.class);

    public void addClient(Client client){
        DaoFactory daoFactory;
        daoFactory = JdbcDaoFactory.getInstance();
        try(ClientDao clientDao = daoFactory.createClientDao()) {
            clientDao.create(client);
        } catch (SQLException e) {
            log.debug(LogMessages.LOG_DATABASE_EXCEPTION + "[Login: "+ client.getUser().getUsername() +"]");
        }
    }

    public void addOrUpdate(Client client) {
        DaoFactory daoFactory;
        int normCalories;
        DayMeal dayMeal;
        List<DayMeal> dayMealList;
        daoFactory = JdbcDaoFactory.getInstance();
        try(ClientDao clientDao = daoFactory.createClientDao()){
            clientDao.update(client);
        } catch (SQLException e) {
            log.debug(LogMessages.LOG_DATABASE_EXCEPTION + "[Login: "+ client.getUser().getUsername() +"]");
        }
        GetDayMealService getDayMealService = new GetDayMealService();
        CalcCaloriesNormService calcCaloriesNormService = new CalcCaloriesNormService();

        if(client.getCaloriesNorm()==null){
            dayMealList = new ArrayList<>();
            dayMeal = getDayMealService.createAndGet(client);
            dayMealList.add(dayMeal);
            client.setDayMealList(dayMealList);
            log.debug(LogMessages.LOG_DAY_MEAL_LIST_CREATED + "[Login: "+ client.getUser().getUsername() +"]");

            normCalories = calcCaloriesNormService.calcNorm(client);
            client.setCaloriesNorm(normCalories);
        }
        normCalories = calcCaloriesNormService.calcNorm(client);
        client.setCaloriesNorm(normCalories);
    }

    public void update(Client client) {
        DaoFactory daoFactory;
        daoFactory = JdbcDaoFactory.getInstance();
        try (ClientDao clientDao = daoFactory.createClientDao()) {
            clientDao.update(client);
        } catch (SQLException e) {
            //NOP
        }
    }
}
