package ua.training.finalproject.foodtrackingsystem.model.service.client;

import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.ClientDao;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.UserDao;

import java.sql.SQLException;

public class DeleteClientWithTrackService {
    public void delete(Long id){
        DaoFactory daoFactory = JdbcDaoFactory.getInstance();
        try (ClientDao clientDao = daoFactory.createClientDao()) {
            clientDao.delete(id);
        } catch (Exception e) {
            //NOP
        }

    }
}
