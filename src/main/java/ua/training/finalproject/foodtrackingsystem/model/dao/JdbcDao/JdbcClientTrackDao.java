package ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDao;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.ClientTrackDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.ClientTrack;

import java.sql.*;
import java.sql.Date;
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

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("insert.clientTrack"))) {
            preparedStatement.setLong(1, entity.getClient().getId());
            preparedStatement.setDate(2, Date.valueOf(entity.getDate()));
            preparedStatement.setString(3, entity.getCaloriesStatus());
            preparedStatement.setInt(4, entity.getCaloriesToNorm());
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        log.debug(LogMessages.LOG_CLIENT_TRACK_CREATED_IN_DB + "[Login: " + entity.getClient() + "]");
    }

    public List<ClientTrack> findClientTrackListByClient(Client client) {
        List<ClientTrack> clientTrackList = new ArrayList<>();
//        GetFoodService getFoodService = new GetFoodService();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("select.clientTrackByClient"))) {
            preparedStatement.setLong(1, client.getId());
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                clientTrack = new ClientTrack();
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
