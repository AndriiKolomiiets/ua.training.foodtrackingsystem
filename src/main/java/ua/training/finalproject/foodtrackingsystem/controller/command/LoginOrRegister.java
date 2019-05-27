package ua.training.finalproject.foodtrackingsystem.controller.command;

import ua.training.finalproject.foodtrackingsystem.model.service.LoginService;

import javax.servlet.http.HttpServletRequest;

import static ua.training.finalproject.foodtrackingsystem.constants.PagePath.*;

public class LoginOrRegister implements Command {
    private LoginService service = null;

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        System.out.println(login + " " + pass);
        if (service.doLogin(login, pass)) {
            return USER_MAIN_PAGE;
        }
        return INDEX_REDIRECT;
    }
}
