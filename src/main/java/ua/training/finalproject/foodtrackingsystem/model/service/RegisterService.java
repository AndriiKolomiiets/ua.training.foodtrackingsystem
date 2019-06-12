package ua.training.finalproject.foodtrackingsystem.model.service;

import ua.training.finalproject.foodtrackingsystem.controller.command.exception.DataSqlException;
import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.UserDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;

public class RegisterService {
    public void registerNewUser(User user) throws DataSqlException{
        DaoFactory daoFactory = new JdbcDaoFactory();
        UserDao userDao = daoFactory.createUserDao();
        if (userDao.checkUserByLogin(user.getUsername(), user.getPassword())){
            throw new DataSqlException("User exists!");
        }
        userDao.create(user);
    }

//    public void registerNewUser(String login, String pass) throws DataSqlException{
//        DaoFactory daoFactory = new JdbcDaoFactory();
//        UserDao userDao = daoFactory.createUserDao();
//        if (userDao.checkUserByLogin(user.getUsername(), user.getPassword())){
//            throw new DataSqlException("User exists!");
//        }
//        userDao.create(user);
//    }
}
