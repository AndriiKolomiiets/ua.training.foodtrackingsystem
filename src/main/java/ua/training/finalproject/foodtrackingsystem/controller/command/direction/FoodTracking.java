package ua.training.finalproject.foodtrackingsystem.controller.command.direction;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.PagePath;
import ua.training.finalproject.foodtrackingsystem.controller.command.Command;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;
import ua.training.finalproject.foodtrackingsystem.model.service.client.GetClientService;
import ua.training.finalproject.foodtrackingsystem.model.service.daymeal.GetDayMealListService;
import ua.training.finalproject.foodtrackingsystem.model.service.daymeal.GetDayMealService;
import ua.training.finalproject.foodtrackingsystem.model.service.user.GetUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * Command which check and save food into database.
 * Works with next services:
 *
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 * @see GetUserService
 * @see GetClientService
 * @see GetDayMealListService
 */
public class FoodTracking implements Command {
    private static final Logger log = Logger.getLogger(ToDayMeal.class);

    @Override
    public String execute(HttpServletRequest request) {
        GetUserService getUserService = new GetUserService();
        GetClientService getClientService = new GetClientService();
        GetDayMealService getDayMealService = new GetDayMealService();
        Optional<DayMeal> optionalDayMeal;
        Integer caloriesToNorm = null;
        String caloriesStatus = null;
        List<DayMeal> dayMealList = null;
        Integer caloriesNorm = null;
        User user = (User) request.getSession().getAttribute(Attributes.REQUEST_USER);
        Optional<User> userFromDb = getUserService.getUserByName(user.getUsername());
        Client client = getClientService.getClient(userFromDb.get());
        if (client != null) {
            GetDayMealListService getDayMealListService = new GetDayMealListService();

            dayMealList = getDayMealListService.getDayMealList(client);
            request.getSession().setAttribute(Attributes.REQUEST_MEAL_LIST, dayMealList);
            optionalDayMeal = getDayMealService.getDayMealByClient(client);
            if (optionalDayMeal.isPresent()) {
                caloriesToNorm = optionalDayMeal.get().getCaloriesToNorm();
                caloriesStatus = optionalDayMeal.get().getCaloriesStatus();
            }
            caloriesNorm = client.getCaloriesNorm();
        }
        request.getSession().setAttribute(Attributes.REQUEST_USER_ROLE, user.getRole());
        request.getSession().setAttribute(Attributes.REQUEST_MEAL_LIST, dayMealList);
        request.getSession().setAttribute(Attributes.REQUEST_CALORIES_NORM, caloriesNorm);
        request.getSession().setAttribute(Attributes.REQUEST_CALORIES_TO_NORM, caloriesToNorm);
        request.getSession().setAttribute(Attributes.REQUEST_CALORIES_STATUS, caloriesStatus);
        return PagePath.USER_FOOD_TRACKING;
    }
}
