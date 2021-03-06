package ua.training.finalproject.foodtrackingsystem.controller.command.direction;

import ua.training.finalproject.foodtrackingsystem.constants.PagePath;
import ua.training.finalproject.foodtrackingsystem.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class MainPage implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.USER_MAIN_PAGE;
    }
}
