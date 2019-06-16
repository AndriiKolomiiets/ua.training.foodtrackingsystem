package ua.training.finalproject.foodtrackingsystem.controller.command.direction;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.constants.PagePath;
import ua.training.finalproject.foodtrackingsystem.controller.command.Command;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;
import ua.training.finalproject.foodtrackingsystem.model.service.GetClientService;
import ua.training.finalproject.foodtrackingsystem.model.service.GetDayMealListService;
import ua.training.finalproject.foodtrackingsystem.model.service.GetDayMealService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ToDayMeal implements Command {
    private static final Logger log = Logger.getLogger(ToDayMeal.class);

    @Override
    public String execute(HttpServletRequest request) {
        Client client;
        Optional<DayMeal> dayMeal;
        long clientId = 0;
        int caloriesToNorm = 0;
        String caloriesStatus = null;
        int caloriesNorm = 0;
        List<DayMeal> dayMealList =null;
        GetDayMealListService getDayMealListService = new GetDayMealListService();
        GetClientService getClientService = new GetClientService();
        GetDayMealService getDayMealService = new GetDayMealService();
        User user = (User) request.getSession().getAttribute(Attributes.REQUEST_USER);
        if (user == null) {
            log.error(LogMessages.LOG_USER_HTTP_NOT_EXTRACT);
//            client = null;
//            clientId = 0;
//            caloriesNorm = 0;
//            caloriesToNorm = 0;
//            caloriesStatus = null;
        } else {
            //todo: getClient(user) ----check
            client = getClientService.getClient(user);
            if (client != null) {
                clientId = client.getId();
                caloriesNorm = client.getCaloriesNorm();
                dayMeal = getDayMealService.getDayMealByClient(client);
                if (dayMeal.isPresent()) {
                    caloriesToNorm = dayMeal.get().getCaloriesToNorm();
                    caloriesStatus = dayMeal.get().getCaloriesStatus();
                    dayMealList = getDayMealListService.getDayMealList(client);
                }
            }
        }
        request.getSession().setAttribute(Attributes.REQUEST_CLIENT_ID, clientId);
        request.getSession().setAttribute(Attributes.REQUEST_CALORIES_NORM, caloriesNorm);
        request.getSession().setAttribute(Attributes.REQUEST_CALORIES_TO_NORM, caloriesToNorm);
        request.getSession().setAttribute(Attributes.REQUEST_CALORIES_STATUS, caloriesStatus);
        request.getSession().setAttribute(Attributes.REQUEST_MEAL_LIST, dayMealList);
        return PagePath.USER_DAY_MEAL;
    }
}
