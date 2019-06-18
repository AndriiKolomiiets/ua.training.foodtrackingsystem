package ua.training.finalproject.foodtrackingsystem.model.service.client;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.ClientDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;

import java.sql.SQLException;
import java.util.Optional;

public class CreateClientService {
    private static final Logger log = Logger.getLogger(CreateClientService.class);

    public Client createEmptyClient(User user) {
        DaoFactory daoFactory;
        daoFactory = JdbcDaoFactory.getInstance();
        ClientDao clientDao = daoFactory.createClientDao();
        Client client = new Client();

        client.setUser(user);
        try {
            clientDao.create(client);
        } catch (SQLException e) {
           //NOP
        } finally {
            clientDao.close();
        }
        Optional<Client> optionalClient = clientDao.findByUserId(user.getId());

        if (!optionalClient.isPresent()){
            log.error(LogMessages.LOG_CLIENT_ERROR + "[Login: " + user.getUsername() + "]");
        }
        return optionalClient.get();
    }
}
