package ua.training.finalproject.foodtrackingsystem.controller.command;

import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.PagePath;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.ClientTrack;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;
import ua.training.finalproject.foodtrackingsystem.model.service.GetClientService;
import ua.training.finalproject.foodtrackingsystem.model.service.GetClientTrackByClientService;
import ua.training.finalproject.foodtrackingsystem.model.service.GetUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class MealStatistic implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        GetUserService getUserService = new GetUserService();
        GetClientService getClientService = new GetClientService();
        GetClientTrackByClientService getClientTrackByClientService = new GetClientTrackByClientService();
        User user = (User) request.getSession().getAttribute(Attributes.REQUEST_USER);
        Optional<User> userFromDb = getUserService.getUserByName(user.getUsername());
        Client client = getClientService.getClient(userFromDb.get());
        List<ClientTrack> clientTrackList = getClientTrackByClientService.getDayMealList(client);
        request.getSession().setAttribute(Attributes.REQUETS_CLIENT_TRACK_LIST, clientTrackList);
//        request.getSession().setAttribute(Attributes.REQUEST_CALORIES_NORM, client.getCaloriesNorm());
        return PagePath.USER_MEAL_STATISTIC;
    }
}
