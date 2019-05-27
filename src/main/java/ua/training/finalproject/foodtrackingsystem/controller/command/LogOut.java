package ua.training.finalproject.foodtrackingsystem.controller.command;

import javax.servlet.http.HttpServletRequest;

import static ua.training.finalproject.foodtrackingsystem.constants.PagePath.INDEX_REDIRECT;

public class LogOut implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        return INDEX_REDIRECT;
    }
}
