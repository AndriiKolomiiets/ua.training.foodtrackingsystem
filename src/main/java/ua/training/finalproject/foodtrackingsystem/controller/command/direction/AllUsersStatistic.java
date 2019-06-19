package ua.training.finalproject.foodtrackingsystem.controller.command.direction;

import ua.training.finalproject.foodtrackingsystem.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

import static ua.training.finalproject.foodtrackingsystem.constants.PagePath.ALL_USERS_STATISTIC;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class AllUsersStatistic implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return ALL_USERS_STATISTIC;
    }
}
