package ua.training.finalproject.foodtrackingsystem.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public interface Command {
    String execute(HttpServletRequest request);
}
