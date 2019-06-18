package ua.training.finalproject.foodtrackingsystem.controller.command.direction;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.controller.command.Command;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;

import javax.servlet.http.HttpServletRequest;

public class ClientStatistic implements Command {
    private static final Logger log = Logger.getLogger(ClientStatistic.class);
    @Override
    public String execute(HttpServletRequest request) {
        Client client;

        return null;
    }
}
