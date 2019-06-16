package ua.training.finalproject.foodtrackingsystem.controller.command;

import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;
import ua.training.finalproject.foodtrackingsystem.model.entity.Food;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;
import ua.training.finalproject.foodtrackingsystem.model.service.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

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
        CaloriesNormCalc caloriesNormCalc = new CaloriesNormCalc();
        CheckCaloriesStatusService checkCaloriesStatusService = new CheckCaloriesStatusService();
        DayMeal dayMeal = new DayMeal();
        AddMealService addMealService = new AddMealService();
        User user = (User) request.getSession().getAttribute(Attributes.REQUEST_USER);
        Client client = getClientService.getClient(user);
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

        foodId = getFoodIdService.getIdByName(foodName);
        //</editor-fold>

        //<editor-fold desc="Meal Processing">
        dayMeal.setClient(client);
        dayMeal.setDateTime(LocalDateTime.now());
        dayMeal.setFood(getFoodService.getFoodById(foodId).get());
        dayMeal.setNumber(number);
        caloriesNorm = caloriesNormCalc.calcNorm(client);
        client.setCaloriesNorm(caloriesNorm);
        dayMealList = getDayMealListService.getDayMealList(client);
        caloriesToNorm = calcCaloriesToNormService.calculate(caloriesNorm, dayMealList);
        caloriesStatus = checkCaloriesStatusService.checkStatus(caloriesToNorm);
        dayMeal.setCaloriesStatus(caloriesStatus);
        dayMeal.setCaloriesToNorm(caloriesToNorm);
        addMealService.addMeal(dayMeal);
        //</editor-fold>

        return Attributes.RETURN_STATEMENT_SUCCESS;
    }
}
