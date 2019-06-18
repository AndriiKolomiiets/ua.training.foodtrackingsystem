package ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDao;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.UserDao;
import ua.training.finalproject.foodtrackingsystem.model.dao.mapper.ClientMapper;
import ua.training.finalproject.foodtrackingsystem.model.dao.mapper.UserMapper;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;

import javax.naming.directory.AttributeInUseException;
import java.sql.*;
import java.util.*;

public class JdbcUserDao implements UserDao {
    private Connection connection;
    private static final Logger log = Logger.getLogger(JdbcUserDao.class);
    private UserMapper userMapper = new UserMapper();
    private ClientMapper clientMapper;
    private ResultSet rs;
    private User user;
    private List<User> userList;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("db",
            new Locale("en", "GB"));

    public JdbcUserDao(Connection connection) {
        this.connection = connection;
    }

    public boolean checkUserByLogin(String login, String pass) {
        boolean message = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("select.passwordByLogin"))) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, pass);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                message = true;
            }
        } catch (Exception e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        return message;
    }

    public User getOrCheckUser(String login) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("select.userByLogin"))) {
            preparedStatement.setString(1, login);
            rs = preparedStatement.executeQuery();
            rs=preparedStatement.getResultSet();
//            System.out.println(rs);
//            sdfsdfsdfsdf
            //todo:  RS==null
//            while (rs.next()) {
                user = userMapper.extractFromResultSet(rs);
//            }
        } catch (Exception e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        } finally {
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return user;
    }

    @Override
    public void create(User entity) {
        Integer role = 2;
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("insert.user"))) {
            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getEmail());
            preparedStatement.setLong(4, entity.getClient().getId());
            preparedStatement.setInt(5, role);
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        log.debug(LogMessages.LOG_USER_CREATED_IN_DB + "[Login: " + entity.getUsername() + "]");
    }

    @Override
    public void createWithoutClient(User entity) {
        Integer role = 2;
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("insert.userWithoutClient"))) {
            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getEmail());
            preparedStatement.setInt(4, role);
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        log.debug(LogMessages.LOG_USER_CREATED_IN_DB + "[Login: " + entity.getUsername() + "]");
    }

    @Override
    public Optional<User> findById(long id) {
        Optional<User> optionalUser = Optional.empty();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("select.userById"))) {
            preparedStatement.setLong(1, id);
            rs = preparedStatement.executeQuery();
            optionalUser = Optional.of(userMapper.extractFromResultSet(rs));
        } catch (SQLException e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        return optionalUser;
    }

    public Long getClientId(String username){
        Long clientId = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("select.userClientById"))) {
            preparedStatement.setString(1, username);
            rs = preparedStatement.executeQuery();
            rs = preparedStatement.getResultSet();
           while (rs.next()){
             clientId = rs.getLong(Attributes.REQUEST_CLIENT_ID);
           }
        } catch (SQLException e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        return clientId;
    }

//    public void setClientId()

    @Override
    public Optional<User> findByUsername(String name) {
        Optional<User> optionalUser = Optional.empty();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("select.userByName"))) {
            preparedStatement.setString(1, name);
            rs = preparedStatement.executeQuery();
            rs = preparedStatement.getResultSet();
            optionalUser = Optional.of(userMapper.extractFromResultSet(rs));
        } catch (SQLException e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        return optionalUser;
    }

    public void setClient(User user){
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("update.userClientId"))) {
            preparedStatement.setLong(1, user.getClient().getId());
            preparedStatement.setLong(2, user.getId());
            preparedStatement.executeUpdate();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }



    }

    //<editor-fold desc="UsersWithClient">
/*    public List<User> getUsersWithClient() {
        userList = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            rs = st.executeQuery(resourceBundle.getString("select.userWithClint"));
            while (rs.next()) {
                user = userMapper.extractFromResultSet(rs);
                Client client = clientMapper.extractFromResultSet(rs);
                user.setClient(client);
                userList.add(user);
            }
        } catch (SQLException e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        return userList;
    }*/
    //</editor-fold>

    @Override
    public List<User> findAll() {
        userList = new ArrayList<>();

        try (Statement st = connection.createStatement()) {
            rs = st.executeQuery(resourceBundle.getString("select.allUsers"));
            while (rs.next()) {
                user = userMapper.extractFromResultSet(rs);
                userList.add(user);
            }
        } catch (SQLException e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        return userList;
    }

    @Override
    public void update(User entity) {
    }

    //todo EXECUTE UPDATE

    @Override
    public void delete(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("delete.userById"))) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
    }
}
