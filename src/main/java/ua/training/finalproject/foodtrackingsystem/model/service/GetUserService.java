package ua.training.finalproject.foodtrackingsystem.model.service;

import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.UserDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;

import java.util.Optional;

public class GetUserService {

    public Optional<User> getUserByName(String name) {
        DaoFactory daoFactory = JdbcDaoFactory.getInstance();
        UserDao userDao = daoFactory.createUserDao();
        Optional<User> optionalUser = userDao.findByUsername(name);
        userDao.close();
        return optionalUser;
    }
}
