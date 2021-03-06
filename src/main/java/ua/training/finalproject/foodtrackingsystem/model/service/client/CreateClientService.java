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

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class CreateClientService {
    private static final Logger log = Logger.getLogger(CreateClientService.class);

    public Client createEmptyClient(User user) {
        DaoFactory daoFactory;
        daoFactory = JdbcDaoFactory.getInstance();
        Optional<Client> optionalClient = Optional.empty();
        Client client = new Client();

        client.setUser(user);
        try (ClientDao clientDao = daoFactory.createClientDao()){
            clientDao.create(client);
            optionalClient = clientDao.findByUserId(user.getId());
            if (!optionalClient.isPresent()){
                log.error(LogMessages.LOG_CLIENT_ERROR + "[Login: " + user.getUsername() + "]");
            }
        } catch (SQLException e) {
           //NOP
        }
        return optionalClient.get();
    }
}
