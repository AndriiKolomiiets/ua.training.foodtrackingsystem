package ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDao;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.TrackStatisticDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.TrackStatistic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class JdbcTrackStatisticDao implements TrackStatisticDao {
    private Connection connection;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("db",
            new Locale("en", "GB"));
    private static final Logger log = Logger.getLogger(JdbcTrackStatisticDao.class);
    private ResultSet rs;

    public JdbcTrackStatisticDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(TrackStatistic entity) {
    }

    @Override
    public Optional<TrackStatistic> findById(long id) {
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
