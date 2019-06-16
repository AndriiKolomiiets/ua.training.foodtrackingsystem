package ua.training.finalproject.foodtrackingsystem.model.service;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.ClientDao;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.UserDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;

import javax.jws.soap.SOAPBinding;
import java.util.Optional;

public class GetClientService {
    private static final Logger log = Logger.getLogger(GetClientService.class);

    public Client getClient(User user){
        Optional<Client> client = Optional.empty();
        Long userId = user.getId();
        DaoFactory daoFactory;
        daoFactory = JdbcDaoFactory.getInstance();
        ClientDao clientDao = daoFactory.createClientDao();
        if (userId!=null) {
            client = clientDao.findByUserId(userId);
        }
        return client.orElse(null);
    }

    public Client getSimpleClientByUserName(String username){
        DaoFactory daoFactory;
        Optional<Client> optionalClient;
        Long clientId;
        daoFactory = JdbcDaoFactory.getInstance();
        ClientDao clientDao = daoFactory.createClientDao();
        UserDao userDao = daoFactory.createUserDao();
        Optional<User> optionalUser = userDao.findByUsername(username);
        if (optionalUser.isPresent()){
            clientId =  userDao.getClientId(username);
           optionalClient = clientDao.findById(clientId);
            optionalUser.get().setClient(optionalClient.get());
            //todo: GetClientFromUser ->> UserDao
            //todo: AddClientToUserService
//            soptionalClient=clientDao.findById(optionalUser.get().getClient().getId());
            userDao.close();
            clientDao.close();
            return optionalClient.get();
        }else {
            log.error(LogMessages.LOG_USER_NOT_FOUND + "[" + username + "]");
            return null;
        }

    }
}