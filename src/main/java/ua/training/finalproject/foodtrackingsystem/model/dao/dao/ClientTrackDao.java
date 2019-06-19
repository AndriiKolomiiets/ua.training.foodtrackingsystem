package ua.training.finalproject.foodtrackingsystem.model.dao.dao;

import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.ClientTrack;

import java.util.List;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public interface ClientTrackDao extends GenericDao<ClientTrack> {
    List<ClientTrack> findClientTrackListByClient(Client client);
}
