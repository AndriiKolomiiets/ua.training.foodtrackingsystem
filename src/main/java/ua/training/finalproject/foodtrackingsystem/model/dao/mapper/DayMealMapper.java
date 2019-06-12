package ua.training.finalproject.foodtrackingsystem.model.dao.mapper;

import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DayMealMapper implements ObjectMapper {
    @Override
    public DayMeal extractFromResultSet(ResultSet rs) throws SQLException {
        DayMeal dayMeal = new DayMeal();
        dayMeal.setId(rs.getLong(Attributes.REQUEST_MEAL_ID));
        dayMeal.setCaloriesStatus(rs.getString(Attributes.REQUEST_CALORIES_STATUS));
        dayMeal.setCaloriesToNorm(rs.getInt(Attributes.REQUEST_CALORIES_TO_NORM));
        dayMeal.setDateTime(rs.getTimestamp(Attributes.REQUEST_DATE)
                .toLocalDateTime());
        dayMeal.setNumber(rs.getInt(Attributes.REQUEST_NUMBER));

        return dayMeal;
    }
}
