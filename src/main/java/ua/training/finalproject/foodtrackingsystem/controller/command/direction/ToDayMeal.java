package ua.training.finalproject.foodtrackingsystem.controller.command.direction;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.constants.PagePath;
import ua.training.finalproject.foodtrackingsystem.controller.command.Command;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;
import ua.training.finalproject.foodtrackingsystem.model.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ToDayMeal implements Command {
    private static final Logger log = Logger.getLogger(ToDayMeal.class);

    @Override
    public String execute(HttpServletRequest request) {
        GetClientService getClientService = new GetClientService();
        GetDayMealService getDayMealService = new GetDayMealService();
//        Optional<DayMeal> optionalDayMeal = getDayMealService.getDayMealById(mealId);
        User user = (User) request.getSession().getAttribute(Attributes.REQUEST_USER);
        Integer caloriesToNorm =null;
        String caloriesStatus = null;
        GetUserService getUserService = new GetUserService();
        Optional<User> userFromDb = getUserService.getUserByName(user.getUsername());
        Client client = getClientService.getClient(userFromDb.get());
        request.getSession().setAttribute(Attributes.REQUEST_CLIENT_ID, client.getId());

        GetDayMealListService getDayMealListService = new GetDayMealListService();
        List<DayMeal> dayMealList = getDayMealListService.getDayMealList(client);

        if (dayMealList.size()!=0) {
            caloriesToNorm = dayMealList.get(0).getCaloriesToNorm();
            caloriesStatus = dayMealList.get(0).getCaloriesStatus();
        }

        request.getSession().setAttribute(Attributes.REQUEST_CALORIES_TO_NORM, caloriesToNorm);
        request.getSession().setAttribute(Attributes.REQUEST_CALORIES_STATUS, caloriesStatus);
        request.getSession().setAttribute(Attributes.REQUEST_MEAL_LIST, dayMealList);
        return PagePath.USER_DAY_MEAL;
    }
}
