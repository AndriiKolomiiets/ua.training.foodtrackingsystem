package ua.training.finalproject.foodtrackingsystem.controller.command.auth;

import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.controller.command.Command;
import ua.training.finalproject.foodtrackingsystem.controller.command.CommandUtil;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;
import ua.training.finalproject.foodtrackingsystem.model.service.LoginService;

import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

public class Login implements Command {
    private LoginService service = new LoginService();

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(Attributes.REQUEST_LOGIN);
        String pass = request.getParameter(Attributes.REQUEST_PASSWORD);
        String returnStatement;

        if (login == null || login.equals("") || pass == null || pass.equals("")) {
//todo: logger
            return Attributes.RETURN_STATEMENT_USER_IS_EMPTY;
        }

        Optional<User> optionalUser = service.checkLoginAndGetUser(login, pass);

        if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(pass)) {
            returnStatement = CommandUtil.openUsersSession(request, optionalUser.get());
        } else if (optionalUser.isPresent() && !optionalUser.get().getPassword().equals(pass)) {
            returnStatement = Attributes.USER_ERROR_LOGIN;
        } else {
            returnStatement = Attributes.USER_NOT_EXISTS;
        }

        return returnStatement;
    }
}