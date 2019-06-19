package ua.training.finalproject.foodtrackingsystem.model.service.clienttrack;

import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.ClientTrackDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.ClientTrack;

import java.util.List;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class GetClientTrackByClientService {
    public List<ClientTrack> getDayMealList(Client client){
        DaoFactory daoFactory;
        daoFactory = JdbcDaoFactory.getInstance();
        ClientTrackDao clientTrackDao = daoFactory.createClientTrackDao();
        List<ClientTrack> clientTrackList = clientTrackDao.findClientTrackListByClient(client);
        clientTrackDao.close();
        return clientTrackList;
    }
}
