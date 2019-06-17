package ua.training.finalproject.foodtrackingsystem.controller.command.process;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.PagePath;
import ua.training.finalproject.foodtrackingsystem.controller.command.Command;
import ua.training.finalproject.foodtrackingsystem.controller.command.CommandUtil;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;
import ua.training.finalproject.foodtrackingsystem.model.service.*;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class AddClient implements Command {
    private static final Logger log = Logger.getLogger(AddClient.class);

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Attributes.REQUEST_USER);
        GetUserService getUserService = new GetUserService();
        Optional<User> userFromDb = getUserService.getUserByName(user.getUsername());
        String returnStatement;
        Integer caloriesNorm;
        String caloriesStatus = null;
        Integer caloriesToNorm = 0;
        AddClientService addClientService = new AddClientService();
        GetClientService getClientService = new GetClientService();
        GetDayMealService getDayMealService = new GetDayMealService();
        Client client = getClientService.getClient(userFromDb.get());

        if (client==null||client.getId()==null){
            client = new Client();
            client.setUser(userFromDb.get());
            String httpDate = request.getParameter(Attributes.REQUEST_BIRTH_DATE);
            client.setBirthDate(LocalDate.parse(httpDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            client.setHeight(Integer.parseInt(request.getParameter(Attributes.REQUEST_HEIGHT)));
            client.setWeight(Integer.parseInt(request.getParameter(Attributes.REQUEST_WEIGHT)));
            client.setLifeStyleCoefficient(Integer.parseInt(request.getParameter(Attributes.REQUEST_LIFE_STYLE)));
            CalcCaloriesNormService calcCaloriesNormService = new CalcCaloriesNormService();
            caloriesNorm = calcCaloriesNormService.calcNorm(client);
            client.setCaloriesNorm(caloriesNorm);
            addClientService.addClient(client);
        } else {
            String httpDate = request.getParameter(Attributes.REQUEST_BIRTH_DATE);
            client.setBirthDate(LocalDate.parse(httpDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            client.setHeight(Integer.parseInt(request.getParameter(Attributes.REQUEST_HEIGHT)));
            client.setWeight(Integer.parseInt(request.getParameter(Attributes.REQUEST_WEIGHT)));
            client.setLifeStyleCoefficient(Integer.parseInt(request.getParameter(Attributes.REQUEST_LIFE_STYLE)));
            CalcCaloriesNormService calcCaloriesNormService = new CalcCaloriesNormService();
            caloriesNorm = calcCaloriesNormService.calcNorm(client);
            client.setCaloriesNorm(caloriesNorm);
            addClientService.update(client);
        }
        userFromDb.get().setClient(client);
        CommandUtil.openUsersSession(request, userFromDb.get());
        Optional<DayMeal> optionalDayMeal= getDayMealService.getDayMealByClient(client);
        if (optionalDayMeal.isPresent()){
            caloriesToNorm = optionalDayMeal.get().getCaloriesToNorm();
            caloriesStatus = optionalDayMeal.get().getCaloriesStatus();
        }
        request.getSession().setAttribute(Attributes.REQUEST_CALORIES_NORM, client.getCaloriesNorm());
        request.getSession().setAttribute(Attributes.REQUEST_CALORIES_TO_NORM, caloriesToNorm);
        request.getSession().setAttribute(Attributes.REQUEST_CALORIES_STATUS, caloriesStatus);
        returnStatement = PagePath.USER_FOOD_TRACKING;
        return returnStatement;
    }
}
