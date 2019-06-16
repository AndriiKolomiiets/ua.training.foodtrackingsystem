package ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDao;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.ClientTrackDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.ClientTrack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class JdbcClientTrackDao implements ClientTrackDao {
    private Connection connection;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("db",
            new Locale("en", "GB"));
    private static final Logger log = Logger.getLogger(JdbcClientTrackDao.class);
    private ResultSet rs;
    private ClientTrack clientTrack;

    public JdbcClientTrackDao(Connection connection) {
        this.connection = connection;
    }

    public JdbcClientTrackDao() {
    }

    @Override
    public void create(ClientTrack entity) {
//todo: collect data for the day from DayMeal and save here
    }

    public List<ClientTrack> findClientTrackListByClient(Client client) {
        List<ClientTrack> clientTrackList = new ArrayList<>();
//        GetFoodService getFoodService = new GetFoodService();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("select.clientTrackByClient"))) {
            preparedStatement.setLong(1, client.getId());
            rs = preparedStatement.executeQuery();
            clientTrack = new ClientTrack();
            while (rs.next()) {
                clientTrack.setId(rs.getLong(Attributes.REQUEST_CLIENT_TRACK_ID));
                clientTrack.setDate(rs.getDate(Attributes.REQUEST_DATE)
                        .toLocalDate());
                clientTrack.setCaloriesStatus(rs.getString(Attributes.REQUEST_CALORIES_STATUS));
                clientTrack.setCaloriesToNorm(rs.getInt(Attributes.REQUEST_CALORIES_TO_NORM));
                clientTrackList.add(clientTrack);
            }
        } catch (Exception e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        return clientTrackList;
    }

    @Override
    public Optional<ClientTrack> findById(long id) {
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
