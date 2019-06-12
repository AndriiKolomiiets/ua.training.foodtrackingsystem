package ua.training.finalproject.foodtrackingsystem.model.dao.mapper;

import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.model.entity.Food;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodMapper implements ObjectMapper {
    @Override
    public Food extractFromResultSet(ResultSet rs) throws SQLException {
        Food food = new Food();
        food.setId(rs.getLong(Attributes.REQUEST_FOOD_ID));
        food.setFoodName(rs.getString(Attributes.REQUEST_FOOD_NAME));
        food.setCalories(rs.getInt(Attributes.REQUEST_CALORIES));
        food.setProteins(rs.getInt(Attributes.REQUEST_PROTEINS));
        food.setCarbs(rs.getInt(Attributes.REQUEST_CARBOHYDRATES));
        food.setLipids(rs.getInt(Attributes.REQUEST_LIPIDS));

        return food;
    }
}
