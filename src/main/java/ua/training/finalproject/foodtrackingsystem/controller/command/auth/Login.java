package ua.training.finalproject.foodtrackingsystem.controller.command.auth;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.controller.command.Command;
import ua.training.finalproject.foodtrackingsystem.controller.command.CommandUtil;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;
import ua.training.finalproject.foodtrackingsystem.model.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.swing.event.CaretListener;

import java.util.List;
import java.util.Optional;

public class Login implements Command {
    private static final Logger log = Logger.getLogger(Login.class);
    private LoginService service = new LoginService();

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(Attributes.REQUEST_LOGIN);
        String pass = request.getParameter(Attributes.REQUEST_PASSWORD);
        GetClientService getClientService = new GetClientService();
        GetDayMealService getDayMealService = new GetDayMealService();
        Optional<DayMeal> optionalDayMeal;
        String caloriesStatus = null;
        Integer caloriesToNorm = null;
        Client client;
        User user;
        String returnStatement;

        if (login == null || login.equals("") || pass == null || pass.equals("")) {
            log.warn(LogMessages.LOG_HTTP_USER_FIELDS_EMPTY);
            return Attributes.RETURN_STATEMENT_USER_IS_EMPTY;
        }
        Optional<User> optionalUser = service.checkLoginAndGetUser(login);

        if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(pass)) {
            returnStatement = CommandUtil.openUsersSession(request, optionalUser.get());
        } else if (optionalUser.isPresent() && !optionalUser.get().getPassword().equals(pass)) {
            log.warn(LogMessages.LOG_USER_PASSWORD_ERROR);
            return Attributes.USER_ERROR_PASSWORD;
        } else if (!optionalUser.isPresent()) {
            log.warn(LogMessages.LOG_USER_LOGIN_ERROR);
            return Attributes.USER_ERROR_LOGIN;
        } else {
            log.warn(LogMessages.LOG_USER_HTTP_ERROR + "[Login: " + optionalUser.get().getUsername() + "]");
            return Attributes.USER_NOT_EXISTS;
        }
        user = (User) request.getSession().getAttribute(Attributes.REQUEST_USER);
        GetUserService getUserService = new GetUserService();
        Optional<User> userFromDb = getUserService.getUserByName(user.getUsername());
        /*Client*/
        client = getClientService.getClient(userFromDb.get());
        client = getClientService.getClient(userFromDb.get());
        optionalDayMeal = getDayMealService.getDayMealByClient(client);
        if (optionalDayMeal.isPresent()) {
            caloriesToNorm = optionalDayMeal.get().getCaloriesToNorm();
            caloriesStatus = optionalDayMeal.get().getCaloriesStatus();
        }
        GetDayMealListService getDayMealListService = new GetDayMealListService();
        List<DayMeal> dayMealList = getDayMealListService.getDayMealList(client);
        request.getSession().setAttribute(Attributes.REQUEST_MEAL_LIST, dayMealList);
        request.getSession().setAttribute(Attributes.REQUEST_CALORIES_NORM, client.getCaloriesNorm());
        request.getSession().setAttribute(Attributes.REQUEST_CALORIES_TO_NORM, caloriesToNorm);
        request.getSession().setAttribute(Attributes.REQUEST_CALORIES_STATUS, caloriesStatus);
        return returnStatement;
    }
}
