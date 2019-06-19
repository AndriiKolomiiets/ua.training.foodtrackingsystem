package ua.training.finalproject.foodtrackingsystem.model.dao;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDao.*;
import ua.training.finalproject.foodtrackingsystem.model.dao.connection.ConnectionPoolHolder;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class JdbcDaoFactory extends DaoFactory {
    private static final Logger log = Logger.getLogger(JdbcDaoFactory.class);
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("db",
            new Locale("en", "GB"));

    @Override
    public ClientDao createClientDao() {
        return new JdbcClientDao(getConnection());
    }

    @Override
    public UserDao createUserDao() {
        return new JdbcUserDao(getConnection());
    }

    @Override
    public ClientTrackDao createClientTrackDao() {
        return new JdbcClientTrackDao(getConnection());
    }

    @Override
    public DayMealDao createDayMealDao() {
        return new JdbcDayMealDao(getConnection());
    }

    @Override
    public FoodDao createFoodDao() {
        return new JdbcFoodDao(getConnection());
    }

    @Override
    public TrackStatisticDao createTrackStatisticDao() {
        return new JdbcTrackStatisticDao(getConnection());
    }

    @Override
    public RoleDao createRoleDao() {
        return new JdbcRoleDao(getConnection());
    }

    private Connection getConnection() {
        DataSource dataSource;
        dataSource = ConnectionPoolHolder.getDataSource();

        try {
            return Objects.requireNonNull(dataSource).getConnection();
        } catch (SQLException e) {
            log.error(LogMessages.LOG_CONNECTION_EXCEPTION + "[" + e.getMessage() + "]");
        }
        return null;
    }
}

