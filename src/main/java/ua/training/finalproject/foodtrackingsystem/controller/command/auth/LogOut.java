package ua.training.finalproject.foodtrackingsystem.controller.command.auth;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.constants.PagePath;
import ua.training.finalproject.foodtrackingsystem.controller.command.Command;
import ua.training.finalproject.foodtrackingsystem.controller.command.CommandUtil;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;

import javax.servlet.http.HttpServletRequest;

public class LogOut implements Command {
    private static final Logger log = Logger.getLogger(LogOut.class);
    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Attributes.REQUEST_USER);
        CommandUtil.deleteUsersFromContext(request, user.getUsername());
        request.getSession().setAttribute(Attributes.REQUEST_USER, null);
        request.getSession().setAttribute(Attributes.PAGE_NAME, Attributes.PAGE_DEMONSTRATION);
        log.debug(LogMessages.LOG_USER_LOGGED + "[Login: " + user.getUsername() +"]");
        return PagePath.LOGIN_OR_REGISTER_REDIRECT;
    }
}
