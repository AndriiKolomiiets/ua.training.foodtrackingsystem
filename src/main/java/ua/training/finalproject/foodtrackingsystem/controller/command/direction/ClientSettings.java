package ua.training.finalproject.foodtrackingsystem.controller.command.direction;

import ua.training.finalproject.foodtrackingsystem.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

import static ua.training.finalproject.foodtrackingsystem.constants.PagePath.USER_SETTINGS;

public class ClientSettings implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return USER_SETTINGS;
    }
}
