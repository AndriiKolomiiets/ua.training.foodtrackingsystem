package ua.training.finalproject.foodtrackingsystem.controller.command.auth;

import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.PagePath;
import ua.training.finalproject.foodtrackingsystem.controller.command.Command;
import ua.training.finalproject.foodtrackingsystem.controller.command.CommandUtil;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;
import ua.training.finalproject.foodtrackingsystem.model.service.LoginService;
import ua.training.finalproject.foodtrackingsystem.model.service.RegisterService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class RegisterNewUser implements Command {
    private LoginService service = new LoginService();
    @Override
    public String execute(HttpServletRequest request) {
        RegisterService registerService = new RegisterService();
        String login = request.getParameter(Attributes.REQUEST_LOGIN);
        String pass = request.getParameter(Attributes.REQUEST_PASSWORD);
        String returnPage = PagePath.LOGIN_OR_REGISTER;
        Optional<User> httpUser = CommandUtil.extractRegisterUserFromHTTP(request);
        Optional<User> optionalUser = service.checkLoginAndGetUser(login, pass);

        if (optionalUser.isPresent()){
            request.getSession().setAttribute(Attributes.PAGE_USER_ERROR_LOGIN, Attributes.PAGE_USER_WRONG_DATA);
            return PagePath.LOGIN_OR_REGISTER;
        }
//        httpUser.get();
        registerService.registerNewUser(httpUser.get());

       /* LoginService loginService = new LoginService();
        optionalUser = loginService.checkLoginAndGetUser(login, pass);*/
        //todo: problem appears here
        returnPage = CommandUtil.openUsersSession(request,  httpUser.get());
        request.setAttribute("response", "200");
        return "200";
    }
}
