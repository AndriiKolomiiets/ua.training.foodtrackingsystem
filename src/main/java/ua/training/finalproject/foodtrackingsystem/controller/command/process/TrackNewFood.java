package ua.training.finalproject.foodtrackingsystem.controller.command.process;

import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.controller.command.Command;
import ua.training.finalproject.foodtrackingsystem.controller.command.CommandUtil;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;
import ua.training.finalproject.foodtrackingsystem.model.entity.Food;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;
import ua.training.finalproject.foodtrackingsystem.model.service.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class TrackNewFood implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String foodName = request.getParameter(Attributes.REQUEST_FOOD_NAME);
        Integer number = Integer.parseInt(request.getParameter(Attributes.REQUEST_NUMBER));
        Integer calories = Integer.parseInt(request.getParameter(Attributes.REQUEST_CALORIES));
        Integer proteins = Integer.parseInt(request.getParameter(Attributes.REQUEST_PROTEINS));
        Integer lipids = Integer.parseInt(request.getParameter(Attributes.REQUEST_LIPIDS));
        Integer carbs = Integer.parseInt(request.getParameter(Attributes.REQUEST_CARBOHYDRATES));
        CheckFoodService checkFoodService = new CheckFoodService();
        AddNewFoodService addNewFoodService = new AddNewFoodService();
        GetFoodIdService getFoodIdService = new GetFoodIdService();
        GetClientService getClientService = new GetClientService();
        GetFoodService getFoodService = new GetFoodService();
        CalcCaloriesToNormService calcCaloriesToNormService = new CalcCaloriesToNormService();
        GetDayMealListService getDayMealListService = new GetDayMealListService();
        CalcCaloriesNormService calcCaloriesNormService = new CalcCaloriesNormService();
        CheckCaloriesStatusService checkCaloriesStatusService = new CheckCaloriesStatusService();
        DayMeal dayMeal = new DayMeal();
        AddMealService addMealService = new AddMealService();
        User user = (User) request.getSession().getAttribute(Attributes.REQUEST_USER);
        GetUserService getUserService = new GetUserService();
        Optional<User> userFromDb = getUserService.getUserByName(user.getUsername());
        Client client = getClientService.getClient(userFromDb.get());
        userFromDb.get().setClient(client);
        CommandUtil.openUsersSession(request, userFromDb.get());
        Integer caloriesNorm;
        Integer caloriesToNorm;
        List<DayMeal> dayMealList;
        String caloriesStatus;
        Food food = new Food();
        Long foodId;
        if (checkFoodService.isUnique(foodName)) {
            return Attributes.RETURN_STATEMENT_FOOD_EXISTS;
        }

        //<editor-fold desc="Food Processing">
        food.setFoodName(foodName);
        food.setCalories(calories);
        food.setProteins(proteins);
        food.setLipids(lipids);
        food.setCarbs(carbs);
        addNewFoodService.add(food);
        //</editor-fold>

        //<editor-fold desc="Meal Processing">
        foodId = getFoodIdService.getIdByName(foodName);
        dayMeal.setClient(client);
        dayMeal.setDateTime(LocalDateTime.now());
        dayMeal.setFood(getFoodService.getFoodById(foodId).get());
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
        //</editor-fold>

        return Attributes.RETURN_STATEMENT_SUCCESS;
    }
}
