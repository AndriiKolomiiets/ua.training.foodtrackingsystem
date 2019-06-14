package ua.training.finalproject.foodtrackingsystem.model.dao;

import ua.training.finalproject.foodtrackingsystem.model.dao.*;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDao.*;
import ua.training.finalproject.foodtrackingsystem.model.dao.connection.ConnectionPoolHolder;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.*;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class JdbcDaoFactory extends DaoFactory {

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

/*    private Connection getConnection() {
        try {
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
           return DriverManager.getConnection(
                   resourceBundle.getString("db.url"),
                   resourceBundle.getString("db.user"),
                   resourceBundle.getString("db.password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    private Connection getConnection() {
     DataSource dataSource = ConnectionPoolHolder.getDataSource();
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

