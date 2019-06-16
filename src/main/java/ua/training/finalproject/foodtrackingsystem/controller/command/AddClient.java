package ua.training.finalproject.foodtrackingsystem.controller.command;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.PagePath;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;
import ua.training.finalproject.foodtrackingsystem.model.service.*;

import javax.servlet.http.HttpServletRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

public class AddClient implements Command {
    private static final Logger log = Logger.getLogger(AddClient.class);

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Attributes.REQUEST_USER);
        GetUserService getUserService = new GetUserService();
        Optional<User> userFromBb = getUserService.getUserByName(user.getUsername());
        String login = user.getUsername();
//        String pass = request.getParameter(Attributes.REQUEST_PASSWORD);
        String returnStatement;
        Integer caloriesNorm;
        String caloriesStatus = null;
        Integer caloriesToNorm = 0;
        AddClientService addClientService = new AddClientService();
        GetClientService getClientService = new GetClientService();
//        Long clientId = user.getClient().getId();
//        GetClientService getClientService = new GetClientService();

        Client client = getClientService.getClient(userFromBb.get());
        CommandUtil.openUsersSession(request, userFromBb.get());
        //todo: mistake!!!!!!!!!!!
        if (client==null||client.getId()==null){
            client = new Client();
            client.setUser(userFromBb.get());
            String httpDate = request.getParameter(Attributes.REQUEST_BIRTH_DATE);
            client.setBirthDate(LocalDate.parse(httpDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            client.setHeight(Integer.parseInt(request.getParameter(Attributes.REQUEST_HEIGHT)));
            client.setWeight(Integer.parseInt(request.getParameter(Attributes.REQUEST_WEIGHT)));
//        client.setCaloriesNorm(Integer.parseInt(request.getParameter(Attributes.REQUEST_CALORIES_NORM)));
//            String coef = request.getParameter(Attributes.REQUEST_LIFE_STYLE);
            client.setLifeStyleCoefficient(Integer.parseInt(request.getParameter(Attributes.REQUEST_LIFE_STYLE)));
            CaloriesNormCalc caloriesNormCalc = new CaloriesNormCalc();
            caloriesNorm = caloriesNormCalc.calcNorm(client);
            client.setCaloriesNorm(caloriesNorm);
            addClientService.addClient(client);
        } else {
            String httpDate = request.getParameter(Attributes.REQUEST_BIRTH_DATE);
            client.setBirthDate(LocalDate.parse(httpDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            client.setHeight(Integer.parseInt(request.getParameter(Attributes.REQUEST_HEIGHT)));
            client.setWeight(Integer.parseInt(request.getParameter(Attributes.REQUEST_WEIGHT)));
//        client.setCaloriesNorm(Integer.parseInt(request.getParameter(Attributes.REQUEST_CALORIES_NORM)));
            client.setLifeStyleCoefficient(Integer.parseInt(request.getParameter(Attributes.REQUEST_LIFE_STYLE)));
            CaloriesNormCalc caloriesNormCalc = new CaloriesNormCalc();
            caloriesNorm = caloriesNormCalc.calcNorm(client);
            client.setCaloriesNorm(caloriesNorm);
            addClientService.update(client);
        }


        GetDayMealService getDayMealService = new GetDayMealService();


//todo: is DayMeal rewrite to DayMealList!!!!!!!!!!
        Optional<DayMeal> dayMeal= getDayMealService.getDayMealByClient(client);
        if (dayMeal.isPresent()){
            caloriesToNorm = dayMeal.get().getCaloriesToNorm();
            caloriesStatus = dayMeal.get().getCaloriesStatus();
        }


        request.getSession().setAttribute(Attributes.REQUEST_CLIENT_ID, client.getId());
        request.getSession().setAttribute(Attributes.REQUEST_CALORIES_NORM, caloriesNorm);
        request.getSession().setAttribute(Attributes.REQUEST_CALORIES_TO_NORM, caloriesToNorm);
        request.getSession().setAttribute(Attributes.REQUEST_CALORIES_STATUS, caloriesStatus);
        returnStatement = PagePath.USER_FOOD_TRACKING;
        return returnStatement;
    }
}
