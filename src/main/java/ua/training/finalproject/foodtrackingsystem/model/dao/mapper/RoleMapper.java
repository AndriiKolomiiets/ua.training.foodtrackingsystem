package ua.training.finalproject.foodtrackingsystem.model.dao.mapper;

import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.model.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements ObjectMapper {
    @Override
    public Role extractFromResultSet(ResultSet rs) throws SQLException {
     return Role.valueOf(rs.getString(Attributes.REQUEST_ROLE));
    }
}
