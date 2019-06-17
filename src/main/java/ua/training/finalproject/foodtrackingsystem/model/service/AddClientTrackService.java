package ua.training.finalproject.foodtrackingsystem.model.service;

import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.ClientTrackDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.ClientTrack;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class AddClientTrackService {
    public void add(Set<ClientTrack> clientTrackSet){
        DaoFactory daoFactory = JdbcDaoFactory.getInstance();
        ClientTrackDao clientTrackDao = daoFactory.createClientTrackDao();

        for (ClientTrack clientTrack : clientTrackSet) {
        clientTrackDao.create(clientTrack);
        }
        clientTrackDao.close();
    }
}
