package ua.training.finalproject.foodtrackingsystem.model.service.daymeal;

import com.sun.org.apache.bcel.internal.generic.DMUL;
import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.DayMealDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;

import java.util.List;

public class GetDayMealListService {
    public List<DayMeal> getDayMealList(Client client){
        DaoFactory daoFactory;
        daoFactory = JdbcDaoFactory.getInstance();
        DayMealDao dayMealDao = daoFactory.createDayMealDao();
       List<DayMeal> dayMealList = dayMealDao.findDayMealListByClient(client);
        dayMealDao.close();
        return dayMealList;
    }

  /*  public List<DayMeal> getDayMealList(long clientId){
        DaoFactory daoFactory;
        daoFactory = JdbcDaoFactory.getInstance();
        DayMealDao dayMealDao = daoFactory.createDayMealDao();
        return dayMealDao.findDayMealListByClient(clientId);
    }*/
}
