package ua.training.finalproject.foodtrackingsystem.model.service;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.controller.command.exception.DataSqlException;
import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.UserDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;

public class RegisterService {
    private static final Logger log = Logger.getLogger(RegisterService.class);

    public void registerNewUser(User user) throws DataSqlException {
        DaoFactory daoFactory = new JdbcDaoFactory();
        UserDao userDao = daoFactory.createUserDao();
        if (userDao.checkUserByLogin(user.getUsername(), user.getPassword())) {
            log.warn(LogMessages.LOG_USER_LOGIN_REGISTER_ERROR + "[Login: " + user.getUsername() + "]");
        }
        userDao.createWithoutClient(user);
        userDao.close();
    }
}
