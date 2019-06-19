package ua.training.finalproject.foodtrackingsystem.model.dao.dao;

import ua.training.finalproject.foodtrackingsystem.model.entity.Client;

import java.util.Optional;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public interface ClientDao extends GenericDao <Client> {
    Optional<Client> findByUserId(long id);

}
