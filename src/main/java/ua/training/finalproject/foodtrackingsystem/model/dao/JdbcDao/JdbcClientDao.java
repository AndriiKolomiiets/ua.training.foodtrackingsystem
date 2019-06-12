package ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDao;

import ua.training.finalproject.foodtrackingsystem.model.dao.dao.ClientDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JdbcClientDao implements ClientDao {
    private Connection connection;

    public JdbcClientDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Client entity) {

    }

    @Override
    public Optional<Client> findById(int id) {
        Optional<Client> optionalClient = Optional.empty();

        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public void update(Client entity) {

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
