package ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDao;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.ClientDao;
import ua.training.finalproject.foodtrackingsystem.model.dao.mapper.ClientMapper;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.service.GetClientTrackByClientService;
import ua.training.finalproject.foodtrackingsystem.model.service.GetDayMealListService;

import java.sql.*;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class JdbcClientDao implements ClientDao {
    private Connection connection;
    private static final Logger log = Logger.getLogger(JdbcClientDao.class);
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("db",
            new Locale("en", "GB"));
    private ResultSet rs;
    private Client client;

    public JdbcClientDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Client entity) {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("insert.simpleClient"))) {
            preparedStatement.setLong(1, entity.getUser().getId());
            /*preparedStatement.setDate(2, Date.valueOf(entity.getBirthDate()));
            preparedStatement.setInt(3, entity.getCaloriesNorm());
            preparedStatement.setInt(4, entity.getHeight());
            preparedStatement.setInt(5, entity.getWeight());
            preparedStatement.setInt(6, entity.getLifeStyleCoefficient());*/
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        log.debug(LogMessages.LOG_CLIENT_CREATED_IN_DB + "[Login: " + entity.getUser().getUsername() + "]");
    }

    @Override
    public Optional<Client> findById(long id) {
        Optional<Client> optionalClient = Optional.empty();
        GetDayMealListService getDayMealByClientService = new GetDayMealListService();
        GetClientTrackByClientService getClientTrackByClientService = new GetClientTrackByClientService();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("select.ClientByClientId"))) {
            preparedStatement.setLong(1, id);
            rs = preparedStatement.executeQuery();
            ClientMapper clientMapper = new ClientMapper();

            client = clientMapper.extractFromResultSet(rs);
            /*while (rs.next()) {
                client.setId(rs.getLong(Attributes.REQUEST_CLIENT_ID));
                client.setBirthDate(rs.getDate(Attributes.REQUEST_BIRTH_DATE)
                        .toLocalDate());
                client.setHeight(rs.getInt(Attributes.REQUEST_CALORIES_TO_NORM));
                client.setWeight(rs.getInt(Attributes.REQUEST_WEIGHT));
                client.setLifeStyleCoefficient(rs.getInt(Attributes.REQUEST_LIFE_STYLE));
                client.setCaloriesNorm(rs.getInt(Attributes.REQUEST_CALORIES_NORM));
            }*/
        } catch (Exception e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        client.setClientTrackList(getClientTrackByClientService.getDayMealList(client));
        client.setDayMealList(getDayMealByClientService.getDayMealList(client));
        return Optional.ofNullable(client);
    }

    @Override
    public Optional<Client> findByUserId(long id) {
        Optional<Client> optionalClient = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("select.ClientByUserId"))) {
            preparedStatement.setLong(1, id);
            rs = preparedStatement.executeQuery();
            ClientMapper clientMapper = new ClientMapper();

            client = clientMapper.extractFromResultSet(rs);
        } catch (Exception e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        return Optional.ofNullable(client);
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public void update(Client entity) {
        Long id = entity.getId();

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("insert.client"))) {
            preparedStatement.setLong(1, entity.getUser().getId());
            preparedStatement.setDate(2, Date.valueOf(entity.getBirthDate()));
            preparedStatement.setInt(3, entity.getCaloriesNorm());
            preparedStatement.setInt(4, entity.getHeight());
            preparedStatement.setInt(5, entity.getWeight());
            preparedStatement.setInt(6, entity.getLifeStyleCoefficient());
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        log.debug(LogMessages.LOG_CLIENT_UPDATED_IN_DB + "[Login: " + entity.getUser().getUsername() + "]");


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
