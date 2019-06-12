package ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDao;

import ua.training.finalproject.foodtrackingsystem.model.dao.dao.DayMealDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JdbcDayMealDao implements DayMealDao {
    private Connection connection;

    public JdbcDayMealDao(Connection connection) {
        this.connection = connection;
    }

    public JdbcDayMealDao() {
    }

    @Override
    public void create(DayMeal entity) {

    }

    @Override
    public Optional<DayMeal>findById(int id) {
        Optional<DayMeal> optionalUser = Optional.empty();


        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public void update(DayMeal entity) {

    }

    @Override
    public void delete(int id) {

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
