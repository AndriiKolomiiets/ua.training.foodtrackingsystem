package ua.training.finalproject.foodtrackingsystem.controller.command.auth;

import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.PagePath;
import ua.training.finalproject.foodtrackingsystem.controller.command.Command;
import ua.training.finalproject.foodtrackingsystem.controller.command.CommandUtil;
import ua.training.finalproject.foodtrackingsystem.model.entity.Role;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;
import ua.training.finalproject.foodtrackingsystem.model.service.LoginService;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

import static ua.training.finalproject.foodtrackingsystem.constants.PagePath.*;

public class Login implements Command {
    private LoginService service = new LoginService();

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(Attributes.REQUEST_LOGIN);
        String pass = request.getParameter(Attributes.REQUEST_PASSWORD);
        String returnPage = PagePath.LOGIN_OR_REGISTER;

        if (login == null || login.equals("") || pass == null || pass.equals("")) {
//todo: logger
            return PagePath.LOGIN_OR_REGISTER_REDIRECT;
        }

        Optional<User> optionalUser = service.checkLoginAndGetUser(login, pass);
       /* try {
            if (!optionalUser.isPresent()) {
                return PagePath.ERROR_PAGE;
            }
        } catch (NullPointerException e) {
            //todo: logger
        }*/

        if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(pass)) {
            returnPage = CommandUtil.openUsersSession(request, optionalUser.get());
        } else if (optionalUser.isPresent() && !optionalUser.get().getPassword().equals(pass)) {
            request.getSession().setAttribute(Attributes.PAGE_USER_ERROR_LOGIN, Attributes.PAGE_USER_WRONG_PASSWORD);
        } else {
            request.getSession().setAttribute(Attributes.PAGE_USER_ERROR_LOGIN, Attributes.PAGE_USER_NOT_EXIST);
        }
        boolean flag = CommandUtil.checkUserIsLogged(request, optionalUser.get().getUsername());
        System.out.println(flag);
        return returnPage;
    }
}
