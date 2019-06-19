package ua.training.finalproject.foodtrackingsystem.model.service.client;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.UserDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class SetClientToUserService {
    private static final Logger log = Logger.getLogger(SetClientToUserService.class);

    public void set(User user){
        DaoFactory daoFactory = new JdbcDaoFactory();
        UserDao userDao = daoFactory.createUserDao();
        userDao.setClient(user);

        log.debug(LogMessages.LOG_USER_UPDATE_PARAMETERS + "[" + user + "]");
        userDao.close();
    }
}
