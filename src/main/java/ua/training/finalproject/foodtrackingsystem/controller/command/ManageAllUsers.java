package ua.training.finalproject.foodtrackingsystem.controller.command;

import javax.servlet.http.HttpServletRequest;

import static ua.training.finalproject.foodtrackingsystem.constants.PagePath.MANAGE_ALL_USERS;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class ManageAllUsers implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return MANAGE_ALL_USERS;
    }
}
