package ua.training.finalproject.foodtrackingsystem.model.dao.dao;

import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;

import java.util.List;
import java.util.Optional;

public interface DayMealDao extends GenericDao<DayMeal>{
    Optional<DayMeal> findDayMealByClient(Client client);
    List<DayMeal> findDayMealListByClient(Client client);
//    List<DayMeal> findDayMealListByClient(Long clientId);
}
