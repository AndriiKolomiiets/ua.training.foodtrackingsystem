package ua.training.finalproject.foodtrackingsystem.controller.command.direction;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.controller.command.Command;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;
import ua.training.finalproject.foodtrackingsystem.model.service.GetClientService;
import ua.training.finalproject.foodtrackingsystem.model.service.GetDayMealListService;
import ua.training.finalproject.foodtrackingsystem.model.service.GetDayMealService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class ClientStatistic implements Command {
    private static final Logger log = Logger.getLogger(ClientStatistic.class);
    @Override
    public String execute(HttpServletRequest request) {
        Client client;

        return null;
    }
}
