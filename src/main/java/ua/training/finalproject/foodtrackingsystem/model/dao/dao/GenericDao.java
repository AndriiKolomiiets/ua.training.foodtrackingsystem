package ua.training.finalproject.foodtrackingsystem.model.dao.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public interface GenericDao<T> extends AutoCloseable {
    void create (T entity) throws SQLException;
    Optional<T> findById(long id);
    List<T> findAll();
    void update(T entity) throws SQLException;
    void delete(long id);
    void close();
}