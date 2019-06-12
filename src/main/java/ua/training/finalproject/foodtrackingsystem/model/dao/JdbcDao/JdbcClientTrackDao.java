package ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDao;

import ua.training.finalproject.foodtrackingsystem.model.dao.dao.ClientTrackDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.ClientTrack;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JdbcClientTrackDao implements ClientTrackDao {
    private Connection connection;

    public JdbcClientTrackDao(Connection connection) {
        this.connection = connection;
    }

    public JdbcClientTrackDao() {
    }

    @Override
    public void create(ClientTrack entity) {

    }

    @Override
    public Optional<ClientTrack> findById(int id) {
        Optional<ClientTrack> optionalUser = Optional.empty();

        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public void update(ClientTrack entity) {

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
