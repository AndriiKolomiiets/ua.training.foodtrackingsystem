package ua.training.finalproject.foodtrackingsystem.model.dao.dao;

import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;

import java.util.Optional;

public interface UserDao extends GenericDao <User>{
    boolean checkUserByLogin(String login, String pass);
//    User getOrCheckUser(String login, String pass);
    User getOrCheckUser(String login);
    Optional<User> findByUsername(String name);
    void createWithoutClient(User entity);

    void setClient(User user);
}
