package ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDao;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.RoleDao;
import ua.training.finalproject.foodtrackingsystem.model.dao.mapper.RoleMapper;
import ua.training.finalproject.foodtrackingsystem.model.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class JdbcRoleDao implements RoleDao {
    private Connection connection;
    private ResultSet rs;
    private static final Logger log = Logger.getLogger(JdbcUserDao.class);
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("db",
            new Locale("en", "GB"));

    public JdbcRoleDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Role entity) {
    }

    @Override
    public Optional<Role> findById(long id) {
        Optional<Role> optionalRole = Optional.empty();
        RoleMapper roleMapper = new RoleMapper();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("select.roleById"))) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            System.out.println(preparedStatement);
            rs = preparedStatement.getResultSet();
            while (rs.next()) {

                optionalRole = Optional.of(roleMapper.extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
        return optionalRole;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public void update(Role entity) {

    }

    @Override
    public void delete(long id) {

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
