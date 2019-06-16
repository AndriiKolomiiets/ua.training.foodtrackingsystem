package ua.training.finalproject.foodtrackingsystem.model.dao.dao;

import ua.training.finalproject.foodtrackingsystem.model.entity.Food;

import java.util.Optional;

public interface FoodDao extends GenericDao <Food> {
    Optional<Food> findByName(String foodName);
}
