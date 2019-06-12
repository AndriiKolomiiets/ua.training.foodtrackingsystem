package ua.training.finalproject.foodtrackingsystem.model.dao.dao;

import ua.training.finalproject.foodtrackingsystem.model.entity.User;

public interface UserDao extends GenericDao <User>{
    boolean checkUserByLogin(String login, String pass);
    User getOrCheckUser(String login, String pass);
}
