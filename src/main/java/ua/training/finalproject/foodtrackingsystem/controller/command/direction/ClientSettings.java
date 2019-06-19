package ua.training.finalproject.foodtrackingsystem.controller.command.direction;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

import static ua.training.finalproject.foodtrackingsystem.constants.PagePath.USER_SETTINGS;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class ClientSettings implements Command {
    private static final Logger log = Logger.getLogger(ClientSettings.class);
    @Override
    public String execute(HttpServletRequest request) {

        return USER_SETTINGS;
    }
}
