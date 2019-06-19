package ua.training.finalproject.foodtrackingsystem.model.service.user;

import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.UserDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;

import java.util.Optional;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class GetUserService {

    public Optional<User> getUserByName(String name) {
        DaoFactory daoFactory = JdbcDaoFactory.getInstance();
        Optional<User> optionalUser;
        try(UserDao userDao = daoFactory.createUserDao()) {
            optionalUser = userDao.findByUsername(name);
        }
        return optionalUser;
    }

    public Optional<User> getUserById(Long id) {
        DaoFactory daoFactory = JdbcDaoFactory.getInstance();
        Optional<User> optionalUser;
        try(UserDao userDao = daoFactory.createUserDao()) {
            optionalUser = userDao.findById(id);
        }
        return optionalUser;
    }
}
