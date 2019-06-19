package ua.training.finalproject.foodtrackingsystem.model.service.util;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.UserDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;

import java.util.Optional;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class LoginService {
    private static final Logger log = Logger.getLogger(LoginService.class);

    public boolean doLogin(String login, String password) {
        DaoFactory daoFactory;
        daoFactory = JdbcDaoFactory.getInstance();
        boolean isLogged;
        try (UserDao userDao = daoFactory.createUserDao()) {
            isLogged = userDao.checkUserByLogin(login, password);
        }
        return isLogged;
    }

    public Optional<User> checkLoginAndGetUser(String login) {
        DaoFactory daoFactory;
        User user;
        daoFactory = JdbcDaoFactory.getInstance();
        try (UserDao userDao = daoFactory.createUserDao()) {
            user = userDao.getOrCheckUser(login);
        }
        if (user == null) {
            log.debug(LogMessages.LOG_USER_LOGIN_ERROR);
        }
        return Optional.ofNullable(user);
    }
}
