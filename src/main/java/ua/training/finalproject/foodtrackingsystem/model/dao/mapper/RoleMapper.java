package ua.training.finalproject.foodtrackingsystem.model.dao.mapper;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.model.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements ObjectMapper {
    private static final Logger log = Logger.getLogger(RoleMapper.class);
    @Override
    public Role extractFromResultSet(ResultSet rs)  {

        String role = null;
        try {
            role = rs.getString(Attributes.REQUEST_ROLE);
        } catch (SQLException e) {
            log.error(LogMessages.LOG_DATABASE_EXCEPTION + "[" + e.getMessage() + "]");
        }
     return Role.valueOf(role);
    }
}
