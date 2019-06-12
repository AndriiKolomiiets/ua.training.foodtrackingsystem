package ua.training.finalproject.foodtrackingsystem.controller.command.auth;

import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.PagePath;
import ua.training.finalproject.foodtrackingsystem.controller.command.Command;
import ua.training.finalproject.foodtrackingsystem.controller.command.CommandUtil;
import ua.training.finalproject.foodtrackingsystem.model.entity.Role;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;

import javax.servlet.http.HttpServletRequest;

import static ua.training.finalproject.foodtrackingsystem.constants.PagePath.INDEX_REDIRECT;

public class LogOut implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Attributes.REQUEST_USER);

        CommandUtil.deleteUsersFromContext(request, user.getUsername());
        request.getSession().setAttribute(Attributes.REQUEST_USER, null);
        request.getSession().setAttribute(Attributes.PAGE_NAME, Attributes.PAGE_DEMONSTRATION);
//        CommandUtil.setUserRole(request, Role.GUEST, "Guest");
        //todo: logger
        return PagePath.LOGIN_OR_REGISTER_REDIRECT;
    }
}
