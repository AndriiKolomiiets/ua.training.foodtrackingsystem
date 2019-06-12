package ua.training.finalproject.foodtrackingsystem.controller.command.direction;

import ua.training.finalproject.foodtrackingsystem.constants.PagePath;
import ua.training.finalproject.foodtrackingsystem.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class LoginOrRegister implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.LOGIN_OR_REGISTER;
    }
}
