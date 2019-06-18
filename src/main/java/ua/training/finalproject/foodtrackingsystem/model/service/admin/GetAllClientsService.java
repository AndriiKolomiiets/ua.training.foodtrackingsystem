package ua.training.finalproject.foodtrackingsystem.model.service.admin;

import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.ClientDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;

import java.util.List;

public class GetAllClientsService {

    public List<Client> getClientList() {
        DaoFactory daoFactory = JdbcDaoFactory.getInstance();
        ClientDao clientDao = daoFactory.createClientDao();
        List<Client> clients ;
        clients = clientDao.findAll();
        clientDao.close();
        return clients;
    }
}
