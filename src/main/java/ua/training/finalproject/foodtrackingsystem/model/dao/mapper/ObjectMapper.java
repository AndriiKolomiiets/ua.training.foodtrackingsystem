package ua.training.finalproject.foodtrackingsystem.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public interface ObjectMapper<T> {

    T extractFromResultSet(ResultSet rs) throws SQLException;
}
