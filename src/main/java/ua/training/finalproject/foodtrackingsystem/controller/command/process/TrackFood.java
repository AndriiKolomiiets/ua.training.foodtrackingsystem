package ua.training.finalproject.foodtrackingsystem.controller.command.process;

import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.controller.command.Command;
import ua.training.finalproject.foodtrackingsystem.controller.command.CommandUtil;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;
import ua.training.finalproject.foodtrackingsystem.model.service.client.GetClientService;
import ua.training.finalproject.foodtrackingsystem.model.service.daymeal.AddMealService;
import ua.training.finalproject.foodtrackingsystem.model.service.daymeal.CheckCaloriesStatusService;
import ua.training.finalproject.foodtrackingsystem.model.service.daymeal.GetDayMealListService;
import ua.training.finalproject.foodtrackingsystem.model.service.daymeal.UpdateCaloriesStatusInDbService;
import ua.training.finalproject.foodtrackingsystem.model.service.food.GetFoodIdService;
import ua.training.finalproject.foodtrackingsystem.model.service.food.GetFoodService;
import ua.training.finalproject.foodtrackingsystem.model.service.user.GetUserService;
import ua.training.finalproject.foodtrackingsystem.model.service.util.CalcCaloriesNormService;
import ua.training.finalproject.foodtrackingsystem.model.service.util.CalcCaloriesToNormService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class TrackFood implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String foodName = request.getParameter(Attributes.REQUEST_FOOD_NAME);
        Integer number = Integer.parseInt(request.getParameter(Attributes.REQUEST_NUMBER));
        GetFoodIdService getFoodIdService = new GetFoodIdService();
        GetClientService getClientService = new GetClientService();
        GetFoodService getFoodService = new GetFoodService();
        CalcCaloriesToNormService calcCaloriesToNormService = new CalcCaloriesToNormService();
        GetDayMealListService getDayMealListService = new GetDayMealListService();
        CalcCaloriesNormService calcCaloriesNormService = new CalcCaloriesNormService();
        CheckCaloriesStatusService checkCaloriesStatusService = new CheckCaloriesStatusService();
        DayMeal dayMeal = new DayMeal();
        Integer caloriesNorm;
        Integer caloriesToNorm;
        List<DayMeal> dayMealList;
        String caloriesStatus;
        Long foodId;
        AddMealService addMealService = new AddMealService();
        User user = (User) request.getSession().getAttribute(Attributes.REQUEST_USER);

        GetUserService getUserService = new GetUserService();
        Optional<User> userFromDb = getUserService.getUserByName(user.getUsername());
        Client client = getClientService.getClient(userFromDb.get());
        userFromDb.get().setClient(client);
        CommandUtil.openUsersSession(request, userFromDb.get());

        //<editor-fold desc="Meal Processing">
        foodId = getFoodIdService.getIdByName(foodName);
        dayMeal.setClient(client);
        dayMeal.setDateTime(LocalDateTime.now());
        if (getFoodService.getFoodById(foodId).isPresent()) {
            dayMeal.setFood(getFoodService.getFoodById(foodId).get());
        }
        dayMeal.setNumber(number);
        caloriesNorm = calcCaloriesNormService.calcNorm(client);
        client.setCaloriesNorm(caloriesNorm);
        dayMealList = getDayMealListService.getDayMealList(client);
        caloriesToNorm = calcCaloriesToNormService.calculate(caloriesNorm, dayMealList);
        caloriesStatus = checkCaloriesStatusService.checkStatus(caloriesToNorm);
        dayMeal.setCaloriesStatus(caloriesStatus);
        dayMeal.setCaloriesToNorm(caloriesToNorm);
        addMealService.addMeal(dayMeal);
        UpdateCaloriesStatusInDbService updateCaloriesStatusInDbService = new UpdateCaloriesStatusInDbService();
        updateCaloriesStatusInDbService.updateAll(dayMeal);
        dayMealList = getDayMealListService.getDayMealList(client);
        //</editor-fold>

        request.getSession().setAttribute(Attributes.REQUEST_MEAL_LIST, dayMealList);

        return Attributes.RETURN_STATEMENT_SUCCESS;
    }
}
