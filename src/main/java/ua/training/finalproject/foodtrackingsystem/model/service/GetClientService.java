package ua.training.finalproject.foodtrackingsystem.model.service;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.ClientDao;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.UserDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;

import java.util.Optional;

public class GetClientService {
    private static final Logger log = Logger.getLogger(GetClientService.class);

    public Client getSimpleClientByUserName(String username){
        DaoFactory daoFactory;
        Optional<Client> optionalClient;
        daoFactory = JdbcDaoFactory.getInstance();
        ClientDao clientDao = daoFactory.createClientDao();
        UserDao userDao = daoFactory.createUserDao();
        Optional<User> optionalUser = userDao.findByUsername(username);
        if (optionalUser.isPresent()){
            optionalClient=clientDao.findById(optionalUser.get().getId());
            userDao.close();
            return optionalClient.get();
        }else {
            log.error(LogMessages.LOG_USER_NOT_FOUND + "[" + username + "]");
            return null;
        }

    }
}
