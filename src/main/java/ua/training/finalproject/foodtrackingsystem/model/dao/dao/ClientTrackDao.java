package ua.training.finalproject.foodtrackingsystem.model.dao.dao;

import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.ClientTrack;

import java.util.List;

public interface ClientTrackDao extends GenericDao<ClientTrack> {
    List<ClientTrack> findClientTrackListByClient(Client client);
}
