package ua.training.finalproject.foodtrackingsystem.model.service.user;

import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.UserDao;

public class DeleteUserService {
    public void delete(Long id){
        DaoFactory daoFactory = JdbcDaoFactory.getInstance();
        UserDao userDao = daoFactory.createUserDao();
        userDao.delete(id);
        userDao.close();
    }
}
