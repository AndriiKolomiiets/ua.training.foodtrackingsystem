package ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDao;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.DayMealDao;
import ua.training.finalproject.foodtrackingsystem.model.dao.mapper.DayMealMapper;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;
import ua.training.finalproject.foodtrackingsystem.model.entity.Food;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class JdbcDayMealDao implements DayMealDao {
    private Connection connection;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("db",
            new Locale("en", "GB"));
    private static final Logger log = Logger.getLogger(JdbcDayMealDao.class);
    private ResultSet rs;
    private DayMeal dayMeal;
    private Food food;


    public JdbcDayMealDao(Connection connection) {
        this.connection = connection;
    }

    public JdbcDayMealDao() {
    }

    @Override
    public void create(DayMeal entity) {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("insert.dayMeal"))) {
            preparedStatement.setLong(1, entity.getClient().getId());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(entity.getDateTime()));
            preparedStatement.setLong(3, entity.getFood().getId());
            preparedStatement.setInt(4, entity.getNumber());
            preparedStatement.setString(5, entity.getCaloriesStatus());
            preparedStatement.setInt(6, entity.getCaloriesToNorm());
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        log.debug(LogMessages.LOG_DAY_MEAL_CREATED_IN_DB + "[Login: " + entity.getClient() + "]");
    }

    public Optional<DayMeal> findDayMealByClient(Client client) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("select.dayMealByClientId"))) {
            preparedStatement.setLong(1, client.getId());
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                dayMeal = new DayMeal();
                dayMeal.setId(rs.getLong(Attributes.REQUEST_MEAL_ID));
                dayMeal.setCaloriesStatus(rs.getString(Attributes.REQUEST_CALORIES_STATUS));
                dayMeal.setCaloriesToNorm(rs.getInt(Attributes.REQUEST_CALORIES_TO_NORM));
            }
        } catch (Exception e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
            dayMeal = null;
        }
        return Optional.ofNullable(dayMeal);
    }

    public List<DayMeal> findDayMealListByClient(Client client) {
        List<DayMeal> dayMealList = new ArrayList<>();
//        GetFoodService getFoodService = new GetFoodService();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("select.dayMealByClientId"))) {
            preparedStatement.setLong(1, client.getId());
            rs = preparedStatement.executeQuery();
            dayMeal = new DayMeal();
            while (rs.next()) {
                DayMealMapper dayMealMapper = new DayMealMapper();
                dayMeal = dayMealMapper.extractFromResultSet(rs);
                /*dayMeal.setId(rs.getLong(Attributes.REQUEST_MEAL_ID));
                dayMeal.setDateTime(rs.getTimestamp(Attributes.REQUEST_DATE_TIME)
                        .toLocalDateTime());
                food =getFoodService.getFoodById(rs.getLong(Attributes.REQUEST_FOOD_ID)).get();
                dayMeal.setFood(food);
                dayMeal.setNumber(rs.getInt(Attributes.REQUEST_NUMBER));
                dayMeal.setCaloriesStatus(rs.getString(Attributes.REQUEST_CALORIES_STATUS));
                dayMeal.setCaloriesToNorm(rs.getInt(Attributes.REQUEST_CALORIES_TO_NORM));*/
                dayMealList.add(dayMeal);
            }
        } catch (Exception e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        return dayMealList;
    }

    @Override
    public Optional<DayMeal> findById(long id) {
        Optional<DayMeal> optionalUser = Optional.empty();


        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public void update(DayMeal entity) {
        //<editor-fold desc="create method - need to be rewrote">
       /* try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("insert.dayMeal"))) {
            preparedStatement.setLong(1, entity.getClient().getId());
            preparedStatement.setTimestamp(2,
                    Timestamp.valueOf(entity.getDateTime()));
            preparedStatement.setLong(3, entity.getFood().getId());
            preparedStatement.setInt(4, entity.getNumber());
            preparedStatement.setString(5, entity.getCaloriesStatus());
            preparedStatement.setInt(6, entity.getCaloriesToNorm());
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        log.debug(LogMessages.LOG_DAY_MEAL_ADDED_TO_DB + "[Login: " + entity.getClient().getUser().getUsername() + "]");*/
        //</editor-fold>
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
