package ua.training.finalproject.foodtrackingsystem.model.service;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDao.JdbcClientDao;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.UserDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;

import java.util.Optional;

public class LoginService {
    private static final Logger log = Logger.getLogger(LoginService.class);
    public boolean doLogin(String login, String password) {
        DaoFactory daoFactory;
        daoFactory = JdbcDaoFactory.getInstance();
        UserDao userDao = daoFactory.createUserDao();
        return userDao.checkUserByLogin(login, password);
    }

    public Optional<User> checkLoginAndGetUser (String login) {
        DaoFactory daoFactory;
        daoFactory = JdbcDaoFactory.getInstance();
        UserDao userDao = daoFactory.createUserDao();
//        userDao.getOrCheckUser(login);
        User user = userDao.getOrCheckUser(login);
        if (user==null){
            log.debug(LogMessages.LOG_USER_LOGIN_ERROR );
        }
        userDao.close();
        return Optional.ofNullable(user);
    }
}
