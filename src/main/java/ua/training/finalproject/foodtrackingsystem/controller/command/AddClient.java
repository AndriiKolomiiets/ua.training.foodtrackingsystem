package ua.training.finalproject.foodtrackingsystem.controller.command;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;
import ua.training.finalproject.foodtrackingsystem.model.service.AddClientService;
import ua.training.finalproject.foodtrackingsystem.model.service.CaloriesNormCalc;
import ua.training.finalproject.foodtrackingsystem.model.service.GetClientService;
import ua.training.finalproject.foodtrackingsystem.model.service.GetDayMealService;

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
        String login = user.getUsername();
//        String pass = request.getParameter(Attributes.REQUEST_PASSWORD);
        String returnStatement;
        Integer caloriesNorm;
        String caloriesStatus = null;
        Integer caloriesToNorm = 0;
        AddClientService addClientService = new AddClientService();
//        Long clientId = user.getClient().getId();
//        GetClientService getClientService = new GetClientService();
        Client client = user.getClient();

        String httpDate = request.getParameter(Attributes.REQUEST_BIRTH_DATE);
        client.setBirthDate(LocalDate.parse(httpDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        client.setHeight(Integer.parseInt(request.getParameter(Attributes.REQUEST_HEIGHT)));
        client.setWeight(Integer.parseInt(request.getParameter(Attributes.REQUEST_WEIGHT)));
//        client.setCaloriesNorm(Integer.parseInt(request.getParameter(Attributes.REQUEST_CALORIES_NORM)));
        client.setLifeStyleCoefficient(Integer.parseInt(request.getParameter(Attributes.REQUEST_LIFE_STYLE)));
        addClientService.addOrUpdate(client);
        CaloriesNormCalc caloriesNormCalc = new CaloriesNormCalc();
        caloriesNorm = caloriesNormCalc.calcNorm(client);
        GetDayMealService getDayMealService = new GetDayMealService();
        Optional<DayMeal> dayMeal= getDayMealService.getDayMealByClient(client);
        if (dayMeal.isPresent()){
            caloriesToNorm = dayMeal.get().getCaloriesToNorm();
            caloriesStatus = dayMeal.get().getCaloriesStatus();
        }


        request.getSession().setAttribute(Attributes.REQUEST_CLIENT_ID, client.getId());
        request.getSession().setAttribute(Attributes.REQUEST_CALORIES_NORM, caloriesNorm);
        request.getSession().setAttribute(Attributes.REQUEST_CALORIES_TO_NORM, caloriesToNorm);
        request.getSession().setAttribute(Attributes.REQUEST_CALORIES_STATUS, caloriesStatus);
        returnStatement = "success";
        return returnStatement;
    }
}
