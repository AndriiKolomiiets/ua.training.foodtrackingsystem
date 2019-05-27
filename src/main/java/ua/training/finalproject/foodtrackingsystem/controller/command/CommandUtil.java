package ua.training.finalproject.foodtrackingsystem.controller.command;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandUtil{

    public static Map<String, Command> initializeCommands() {
        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("loginOrRegister", new LoginOrRegister());
        commandMap.put("logOut", new LogOut());
        commandMap.put("foodTracking", new FoodTracking());
        commandMap.put("allUsersStatistic", new AllUsersStatistic());
        commandMap.put("manageAllUsers", new ManageAllUsers());
        commandMap.put("userSettings", new UserSettings());
        commandMap.put("userStatistic", new UserStatistic());
        commandMap.put("exception", new PageException());
        return commandMap;
    }
}
