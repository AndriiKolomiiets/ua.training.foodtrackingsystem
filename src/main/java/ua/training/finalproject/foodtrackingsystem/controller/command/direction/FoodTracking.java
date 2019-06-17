package ua.training.finalproject.foodtrackingsystem.controller.command.direction;

import com.sun.org.apache.bcel.internal.generic.DMUL;
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
import ua.training.finalproject.foodtrackingsystem.model.service.GetUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class FoodTracking implements Command {
    private static final Logger log = Logger.getLogger(ToDayMeal.class);
    @Override
    public String execute(HttpServletRequest request) {
        GetUserService getUserService = new GetUserService();
        GetClientService getClientService = new GetClientService();
        GetDayMealService getDayMealService = new GetDayMealService();
        Optional<DayMeal> optionalDayMeal;
        Integer caloriesToNorm =null;
        String caloriesStatus = null;
        User user = (User) request.getSession().getAttribute(Attributes.REQUEST_USER);
        Optional<User> userFromDb = getUserService.getUserByName(user.getUsername());
        Client client = getClientService.getClient(userFromDb.get());
        GetDayMealListService getDayMealListService = new GetDayMealListService();
        List<DayMeal> dayMealList = getDayMealListService.getDayMealList(client);
        request.getSession().setAttribute(Attributes.REQUEST_MEAL_LIST, dayMealList);
        optionalDayMeal = getDayMealService.getDayMealByClient(client);
        if (optionalDayMeal.isPresent()) {
            caloriesToNorm = optionalDayMeal.get().getCaloriesToNorm();
            caloriesStatus = optionalDayMeal.get().getCaloriesStatus();
        }

//        GetDayMealListService getDayMealListService = new GetDayMealListService();
//        List<DayMeal> dayMealList = getDayMealListService.getDayMealList(client);
        request.getSession().setAttribute(Attributes.REQUEST_MEAL_LIST, dayMealList);
        request.getSession().setAttribute(Attributes.REQUEST_CALORIES_NORM, client.getCaloriesNorm());
        request.getSession().setAttribute(Attributes.REQUEST_CALORIES_TO_NORM, caloriesToNorm);
        request.getSession().setAttribute(Attributes.REQUEST_CALORIES_STATUS, caloriesStatus);
        return PagePath.USER_FOOD_TRACKING;
    }
}
