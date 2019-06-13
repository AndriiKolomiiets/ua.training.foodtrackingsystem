package ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDao;

import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.UserDao;
import ua.training.finalproject.foodtrackingsystem.model.dao.mapper.ClientMapper;
import ua.training.finalproject.foodtrackingsystem.model.dao.mapper.UserMapper;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.Role;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;

import java.sql.*;
import java.util.*;

public class JdbcUserDao implements UserDao {
    private Connection connection;
    private UserMapper userMapper;
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
            e.printStackTrace();
        }
        return message;
    }

    public User getOrCheckUser(String login, String pass) {
//        Optional<User> optionalUser;
        Integer role_id = 2;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("select.userByLogin"))) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, pass);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getLong(Attributes.REQUEST_USER_ID));
                user.setUsername(rs.getString(Attributes.REQUEST_LOGIN));
                user.setPassword(rs.getString(Attributes.REQUEST_PASSWORD));
                user.setEmail(rs.getString(Attributes.REQUEST_EMAIL));
                //todo: get User ROle

                role_id =rs.getInt(Attributes.REQUEST_ROLE_ID);
                user.setClient(new Client());
                user.getClient().setId(rs.getLong(Attributes.REQUEST_CLIENT_ID));
            }
        } catch (Exception e) {
            user = null;
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("select.roleByRoleId"))) {
            preparedStatement.setInt(1, role_id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String roleFromDb = rs.getString(Attributes.REQUEST_ROLE);
                user.setRole(Role.valueOf(roleFromDb));
            }
    } catch (Exception e) {
            e.printStackTrace();
            //todo: logger
        user = null;
    }
           return user;
    }

    @Override
    public void create(User entity) {
        Integer role = 2;
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//todo: check is role set successfully!!!!
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("insert.user"))) {
            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getEmail());
            preparedStatement.setInt(4, role);
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        //todo: logging
        }
    }

    @Override
    public Optional<User> findById(int id) {
        Optional<User> optionalUser = Optional.empty();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("select.userById"))) {
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            connection.setAutoCommit(true);
            optionalUser = Optional.of(userMapper.extractFromResultSet(rs));
        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
        }
        return optionalUser;
    }

    public List<User> getUsersWithClient() {
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
            //todo: logger
            System.out.println(e.getMessage());
        }
        return userList;
    }

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
            //todo: logger
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(int id) {
        try (Statement statement = connection.createStatement()) {
            rs = statement.executeQuery(
                    resourceBundle.getString("select.unique.user.and.tax.info"));

        } catch (SQLException e) {
            //todo: logger
            e.printStackTrace();
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
