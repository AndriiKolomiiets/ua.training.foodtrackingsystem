package ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDao;

import ua.training.finalproject.foodtrackingsystem.model.dao.dao.TrackStatisticDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.TrackStatistic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JdbcTrackStatisticDao implements TrackStatisticDao {
    private Connection connection;

    public JdbcTrackStatisticDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(TrackStatistic entity) {
    }

    @Override
    public Optional<TrackStatistic> findById(int id) {
        Optional<TrackStatistic> optionalTrackStatistic = Optional.empty();


        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public void update(TrackStatistic entity) {

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
