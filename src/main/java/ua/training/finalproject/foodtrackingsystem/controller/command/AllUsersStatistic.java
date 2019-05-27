package ua.training.finalproject.foodtrackingsystem.controller.command;

import javax.servlet.http.HttpServletRequest;

import static ua.training.finalproject.foodtrackingsystem.constants.PagePath.ALL_USERS_STATISTIC;

public class AllUsersStatistic implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return ALL_USERS_STATISTIC;
    }
}
