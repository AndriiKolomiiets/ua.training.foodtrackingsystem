package ua.training.finalproject.foodtrackingsystem.model.service;

import ua.training.finalproject.foodtrackingsystem.controller.command.exception.DataSqlException;
import ua.training.finalproject.foodtrackingsystem.model.JdbcDao;
import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDao.JdbcUserDao;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.UserDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;

import java.sql.SQLException;
import java.util.Optional;

public class LoginService {
    public boolean doLogin(String login, String password) {
//        DaoFactory daoFactory = JdbcDaoFactory.getInstance();
//        UserDao userDao = daoFactory.createUserDao();
        DaoFactory daoFactory;

        daoFactory = JdbcDaoFactory.getInstance();

        UserDao userDao = daoFactory.createUserDao();
        return userDao.checkUserByLogin(login, password);
    }

    public Optional<User> checkLoginAndGetUser (String login, String password) {
//        DaoFactory daoFactory = JdbcDaoFactory.getInstance();
//        UserDao userDao = daoFactory.createUserDao();
        DaoFactory daoFactory;

        daoFactory = JdbcDaoFactory.getInstance();

        UserDao userDao = daoFactory.createUserDao();
        return Optional.ofNullable(userDao.getOrCheckUser(login, password));
    }
}
