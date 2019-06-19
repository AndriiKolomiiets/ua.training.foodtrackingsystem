package ua.training.finalproject.foodtrackingsystem.model.dao.mapper;

import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;
import ua.training.finalproject.foodtrackingsystem.model.entity.Food;
import ua.training.finalproject.foodtrackingsystem.model.service.food.GetFoodService;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class DayMealMapper implements ObjectMapper {
    @Override
    public DayMeal extractFromResultSet(ResultSet rs) throws SQLException {
        Food food;
        GetFoodService getFoodService = new GetFoodService();
        DayMeal dayMeal = new DayMeal();
        dayMeal.setId(rs.getLong(Attributes.REQUEST_MEAL_ID));
        dayMeal.setClient(new Client());
        dayMeal.getClient().setId(rs.getLong(Attributes.REQUEST_CLIENT_ID));
        dayMeal.setDateTime(rs.getTimestamp(Attributes.REQUEST_DATE_TIME)
                .toLocalDateTime());
        food =getFoodService.getFoodById(rs.getLong(Attributes.REQUEST_FOOD_ID)).get();
        dayMeal.setFood(food);
        dayMeal.setNumber(rs.getInt(Attributes.REQUEST_NUMBER));
        dayMeal.setCaloriesStatus(rs.getString(Attributes.REQUEST_CALORIES_STATUS));
        dayMeal.setCaloriesToNorm(rs.getInt(Attributes.REQUEST_CALORIES_TO_NORM));

        return dayMeal;
    }
}
