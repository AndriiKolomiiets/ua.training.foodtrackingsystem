package ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDao;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.ClientDao;
import ua.training.finalproject.foodtrackingsystem.model.dao.mapper.ClientMapper;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;
import ua.training.finalproject.foodtrackingsystem.model.service.clienttrack.GetClientTrackByClientService;
import ua.training.finalproject.foodtrackingsystem.model.service.daymeal.GetDayMealListService;
import ua.training.finalproject.foodtrackingsystem.model.service.user.GetUserService;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
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
    public void create(Client entity) throws SQLException {
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
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            connection.rollback();
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        log.debug(LogMessages.LOG_CLIENT_CREATED_IN_DB + "[Login: " + entity.getUser().getUsername() + "]");
    }

    @Override
    public Optional<Client> findById(long id) {
        GetDayMealListService getDayMealByClientService = new GetDayMealListService();
        GetClientTrackByClientService getClientTrackByClientService = new GetClientTrackByClientService();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("select.ClientByClientId"))) {
            preparedStatement.setLong(1, id);
            rs = preparedStatement.executeQuery();
            ClientMapper clientMapper = new ClientMapper();

            client = clientMapper.extractFromResultSet(rs);
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
    public List<Client> findAll() {
        GetUserService getUserService = new GetUserService();
        List<Client> clients = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("select.clientListWithUser"))) {
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getLong(Attributes.REQUEST_CLIENT_ID));
                client.setBirthDate(rs.getDate(Attributes.REQUEST_BIRTH_DATE)
                        .toLocalDate());
                client.setWeight(rs.getInt(Attributes.REQUEST_WEIGHT));
                client.setLifeStyleCoefficient(rs.getInt(Attributes.REQUEST_LIFE_STYLE));
                client.setCaloriesNorm(rs.getInt(Attributes.REQUEST_CALORIES_NORM));
                Long userId = rs.getLong(Attributes.REQUEST_USER_ID);
                Optional<User> optionalUser = getUserService.getUserById(userId);
                optionalUser.ifPresent(client::setUser);
                clients.add(client);
            }
        } catch (Exception e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        return clients;
    }

    @Override
    public void update(Client entity) throws SQLException {
        Long id = entity.getId();

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("update.client"))) {
            preparedStatement.setDate(1, Date.valueOf(entity.getBirthDate()));
            preparedStatement.setInt(2, entity.getCaloriesNorm());
            preparedStatement.setInt(3, entity.getHeight());
            preparedStatement.setInt(4, entity.getWeight());
            preparedStatement.setInt(5, entity.getLifeStyleCoefficient());
            preparedStatement.setLong(6, entity.getId());
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
            connection.rollback();
        }
        log.debug(LogMessages.LOG_CLIENT_UPDATED_IN_DB + "[Login: " + entity + "]");
    }

    @Override
    public void delete(long id) {
        PreparedStatement preparedStatement;
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        try {
            preparedStatement = connection.prepareStatement(
                    resourceBundle.getString("delete.mealByClientId"));
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(
                resourceBundle.getString("delete.clientById"));
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
            }
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
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
