package ua.training.finalproject.foodtrackingsystem.model.service.client;

import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.ClientDao;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.UserDao;

import java.sql.SQLException;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
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
