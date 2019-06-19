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
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class JdbcDayMealDao implements DayMealDao {
    private Connection connection;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("db",
            new Locale("en", "GB"));
    private static final Logger log = Logger.getLogger(JdbcDayMealDao.class);
    DayMealMapper dayMealMapper = new DayMealMapper();
    private ResultSet rs;
    private DayMeal dayMeal;

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
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("select.dayMealListByClientId"))) {
            preparedStatement.setLong(1, client.getId());
            rs = preparedStatement.executeQuery();
//            dayMeal = new DayMeal();
            while (rs.next()) {
                dayMeal = dayMealMapper.extractFromResultSet(rs);
                dayMealList.add(dayMeal);
            }
        } catch (Exception e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        return dayMealList;
    }

    @Override
    public Optional<DayMeal> findById(long id) {
        Optional<DayMeal> optionalDayMeal = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("select.dayMealById"))) {
            preparedStatement.setLong(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                dayMeal = dayMealMapper.extractFromResultSet(rs);
            }
        } catch (Exception e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        optionalDayMeal=Optional.ofNullable(dayMeal);
        return optionalDayMeal;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public void update(DayMeal entity) {
    }

    @Override
    public void updateAll(DayMeal entity) {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("update.dayMealList"))) {
            preparedStatement.setString(1, entity.getCaloriesStatus());
            preparedStatement.setInt(2, entity.getCaloriesToNorm());
            preparedStatement.setLong(3, entity.getClient().getId());
            preparedStatement.executeUpdate();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        log.debug(LogMessages.LOG_DAY_MEAL_UPPDATED_IN_DB );
    }

    public List<DayMeal> findAllByDate(DayMeal dayMeal, LocalDateTime yesterday){
        List<DayMeal> dayMealList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("select.dayMealListByClientIdAndDateTime"))) {
            preparedStatement.setLong(1, dayMeal.getClient().getId());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(yesterday));
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                dayMeal = dayMealMapper.extractFromResultSet(rs);
                dayMealList.add(dayMeal);
            }
        } catch (Exception e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        return dayMealList;
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("delete.dayMealById"))) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
            dayMeal = null;
        }
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
