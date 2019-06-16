package ua.training.finalproject.foodtrackingsystem.model.dao.dao;

import ua.training.finalproject.foodtrackingsystem.model.entity.Client;

import java.util.Optional;

public interface ClientDao extends GenericDao <Client> {
    Optional<Client> findByUserId(long id);
}
