package ua.training.finalproject.foodtrackingsystem.model.dao.dao;

import ua.training.finalproject.foodtrackingsystem.model.entity.User;

import java.util.Optional;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public interface UserDao extends GenericDao<User> {
    boolean checkUserByLogin(String login, String pass);

    User getOrCheckUser(String login);

    Optional<User> findByUsername(String name);

    void createWithoutClient(User entity);

    void setClient(User user);

    Long getClientId(String username);
}
