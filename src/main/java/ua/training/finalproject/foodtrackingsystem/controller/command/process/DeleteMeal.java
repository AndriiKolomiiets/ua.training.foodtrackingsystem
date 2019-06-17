package ua.training.finalproject.foodtrackingsystem.controller.command.process;

import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.controller.command.Command;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;
import ua.training.finalproject.foodtrackingsystem.model.service.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class DeleteMeal implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        String stringMealId = request.getParameter(Attributes.REQUEST_MEAL_ID);
        if (!stringMealId.matches(".*\\d.*")){
            return Attributes.RETURN_STATEMENT_WRONG_ID;
        }
        Long mealId = Long.valueOf(stringMealId);
        Long clientId = (Long) request.getSession().getAttribute(Attributes.REQUEST_CLIENT_ID);
        GetDayMealService getDayMealService = new GetDayMealService();
        Integer caloriesToNorm = null;
        String caloriesStatus = null;
        Optional<DayMeal> optionalDayMeal = getDayMealService.getDayMealById(mealId);
        if (!optionalDayMeal.isPresent() || !optionalDayMeal.get().getClient().getId().equals(clientId)) {
            return Attributes.RETURN_STATEMENT_WRONG_ID;
        }
        DeleteDayMealService deleteDayMealService = new DeleteDayMealService();
        GetClientService getClientService = new GetClientService();
        GetUserService getUserService = new GetUserService();
        deleteDayMealService.deleteById(mealId);
        GetDayMealListService getDayMealListService = new GetDayMealListService();
//        List<DayMeal> dayMealList = getDayMealListService.getDayMealList(client);
        User user = (User) request.getSession().getAttribute(Attributes.REQUEST_USER);
        Optional<User> userFromDb = getUserService.getUserByName(user.getUsername());
        Client client = getClientService.getClient(userFromDb.get());
        List<DayMeal> dayMealList = getDayMealListService.getDayMealList(client);
        CalcCaloriesToNormService calcCaloriesToNormService = new CalcCaloriesToNormService();
        CheckCaloriesStatusService checkCaloriesStatusService = new CheckCaloriesStatusService();

//        caloriesToNorm = calcCaloriesToNormService.calculate(caloriesToNorm, dayMealList);
        UpdateCaloriesStatusInDbService updateCaloriesStatusInDbService = new UpdateCaloriesStatusInDbService();
        DayMeal dayMeal = new DayMeal();
        CalcCaloriesNormService calcCaloriesNormService = new CalcCaloriesNormService();

        if (dayMealList != null) {
            caloriesToNorm = calcCaloriesToNormService.calculate(calcCaloriesNormService.calcNorm(client), dayMealList);
            caloriesStatus = checkCaloriesStatusService.checkStatus(caloriesToNorm);
            dayMeal.setCaloriesToNorm(caloriesToNorm);
            dayMeal.setCaloriesStatus(caloriesStatus);
            dayMeal.setClient(client);
            updateCaloriesStatusInDbService.updateAll(dayMeal);
        }

//        GetUserService getUserService = new GetUserService();
//        GetClientService getClientService = new GetClientService();
//        User user = (User) request.getSession().getAttribute(Attributes.REQUEST_USER);
//        Optional<User> userFromDb = getUserService.getUserByName(user.getUsername());
//        Client client = getClientService.getClient(userFromDb.get());

        request.getSession().setAttribute(Attributes.REQUEST_MEAL_LIST, dayMealList);
        request.getSession().setAttribute(Attributes.REQUEST_CALORIES_NORM, client.getCaloriesNorm());
        request.getSession().setAttribute(Attributes.REQUEST_CALORIES_TO_NORM, caloriesToNorm);
        request.getSession().setAttribute(Attributes.REQUEST_CALORIES_STATUS, caloriesStatus);
        //todo change calories after delete
        return Attributes.RETURN_STATEMENT_SUCCESS;
    }
}
