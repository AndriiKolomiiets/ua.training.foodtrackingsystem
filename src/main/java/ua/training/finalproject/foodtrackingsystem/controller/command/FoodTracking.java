package ua.training.finalproject.foodtrackingsystem.controller.command;

import javax.servlet.http.HttpServletRequest;

import static ua.training.finalproject.foodtrackingsystem.constants.PagePath.FOOD_TRACKING;

public class FoodTracking implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return FOOD_TRACKING;
    }
}
