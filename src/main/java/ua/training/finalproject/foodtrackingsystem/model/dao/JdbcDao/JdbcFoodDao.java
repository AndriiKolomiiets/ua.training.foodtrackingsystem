package ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDao;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.FoodDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.Food;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class JdbcFoodDao implements FoodDao {
    private Connection connection;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("db",
            new Locale("en", "GB"));
    private static final Logger log = Logger.getLogger(JdbcFoodDao.class);
    private ResultSet rs;
    private Food food;

    public JdbcFoodDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Food entity) {

    }

    @Override
    public Optional<Food> findById(long id) {
        Optional<Food> optionalFood = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("select.foodById"))) {
            preparedStatement.setLong(1, id);
            rs = preparedStatement.executeQuery();
            food = new Food();
            if (rs.next()) {
                food.setId(rs.getLong(Attributes.REQUEST_FOOD_ID));
                food.setFoodName(rs.getString(Attributes.REQUEST_FOOD_NAME));
                food.setCalories(rs.getInt(Attributes.REQUEST_CALORIES));
                food.setLipids(rs.getInt(Attributes.REQUEST_LIPIDS));
                food.setCarbs(rs.getInt(Attributes.REQUEST_CARBOHYDRATES));
                food.setProteins(rs.getInt(Attributes.REQUEST_PROTEINS));
                optionalFood = Optional.of(food);
            }
        } catch (Exception e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        return optionalFood;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public void update(Food entity) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
