package ua.training.finalproject.foodtrackingsystem.controller.command.direction;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.controller.command.Command;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class ClientStatistic implements Command {
    private static final Logger log = Logger.getLogger(ClientStatistic.class);
    @Override
    public String execute(HttpServletRequest request) {
        return null;
    }
}
