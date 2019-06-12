package ua.training.finalproject.foodtrackingsystem.controller.command.direction;

import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.PagePath;
import ua.training.finalproject.foodtrackingsystem.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class FoodTracking implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute(Attributes.PAGE_NAME, Attributes.PAGE_FOOD_TRACKING);
        return PagePath.USER_FOOD_TRACKING;
    }
}
