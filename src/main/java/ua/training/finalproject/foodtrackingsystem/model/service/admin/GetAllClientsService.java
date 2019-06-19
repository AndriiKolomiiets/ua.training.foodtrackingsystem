package ua.training.finalproject.foodtrackingsystem.model.service.admin;

import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.ClientDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;

import java.util.List;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class GetAllClientsService {

    public List<Client> getClientList() {
        DaoFactory daoFactory = JdbcDaoFactory.getInstance();
        List<Client> clients;
        try (ClientDao clientDao = daoFactory.createClientDao()) {
            clients = clientDao.findAll();
        }
        return clients;
    }
}
