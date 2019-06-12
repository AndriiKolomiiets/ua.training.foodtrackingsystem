package ua.training.finalproject.foodtrackingsystem.controller.command;

import ua.training.finalproject.foodtrackingsystem.constants.PagePath;

import javax.servlet.http.HttpServletRequest;

public class MealStatistic implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.USER_MEAL_STATISTIC;
    }
}
