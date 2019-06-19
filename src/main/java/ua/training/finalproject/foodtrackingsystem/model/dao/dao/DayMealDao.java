package ua.training.finalproject.foodtrackingsystem.model.dao.dao;

import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public interface DayMealDao extends GenericDao<DayMeal>{
    Optional<DayMeal> findDayMealByClient(Client client);
    List<DayMeal> findDayMealListByClient(Client client);
    void updateAll(DayMeal entity);
    List<DayMeal> findAllByDate(DayMeal dayMeal, LocalDateTime yesterday);
}
