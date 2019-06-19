package ua.training.finalproject.foodtrackingsystem.model.dao.dao;

import ua.training.finalproject.foodtrackingsystem.model.entity.Food;

import java.util.Optional;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public interface FoodDao extends GenericDao <Food> {
    Optional<Food> findByName(String foodName);
}
