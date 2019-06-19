package ua.training.finalproject.foodtrackingsystem.model.service.client;

import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.ClientTrackDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.ClientTrack;

import java.sql.SQLException;
import java.util.Set;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class AddClientTrackService {
    public void add(Set<ClientTrack> clientTrackSet){
        DaoFactory daoFactory = JdbcDaoFactory.getInstance();
        for (ClientTrack clientTrack : clientTrackSet) {
            try (ClientTrackDao clientTrackDao = daoFactory.createClientTrackDao()) {
                clientTrackDao.create(clientTrack);
            } catch (SQLException e) {
                //NOP
            }
        }
    }
}
