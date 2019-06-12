package ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDao;

import ua.training.finalproject.foodtrackingsystem.model.dao.dao.FoodDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.Food;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JdbcFoodDao implements FoodDao {
    private Connection connection;

    public JdbcFoodDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Food entity) {

    }

    @Override
    public Optional<Food> findById(int id) {
        Optional<Food> optionalUser = Optional.empty();


        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public void update(Food entity) {

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
