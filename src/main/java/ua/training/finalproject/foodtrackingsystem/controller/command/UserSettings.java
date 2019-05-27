package ua.training.finalproject.foodtrackingsystem.controller.command;

import javax.servlet.http.HttpServletRequest;

import static ua.training.finalproject.foodtrackingsystem.constants.PagePath.USER_SETTINGS;

public class UserSettings implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return USER_SETTINGS;
    }
}
