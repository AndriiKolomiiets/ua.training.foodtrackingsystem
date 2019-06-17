package ua.training.finalproject.foodtrackingsystem.controller.command;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.controller.command.auth.LogOut;
import ua.training.finalproject.foodtrackingsystem.controller.command.auth.Login;
import ua.training.finalproject.foodtrackingsystem.controller.command.auth.RegisterNewUser;
import ua.training.finalproject.foodtrackingsystem.controller.command.direction.*;
import ua.training.finalproject.foodtrackingsystem.controller.command.exception.DataHttpException;
import ua.training.finalproject.foodtrackingsystem.controller.command.process.*;
import ua.training.finalproject.foodtrackingsystem.model.dao.mapper.UserMapper;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public abstract class CommandUtil {
    private static final Logger log = Logger.getLogger(CommandUtil.class);

    public static Map<String, Command> initializeCommands() {
        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("login", new Login());
        commandMap.put("loginOrRegister", new LoginOrRegister());
        commandMap.put("register", new RegisterNewUser());
        commandMap.put("logOut", new LogOut());
        commandMap.put("dayMeal", new ToDayMeal());
        commandMap.put("mainPage", new MainPage());
        commandMap.put("changeLanguage", new ChangeLanguage());
        commandMap.put("addClient", new AddClient());
        commandMap.put("trackNewFood", new TrackNewFood());
        commandMap.put("deleteMeal", new DeleteMeal());

        commandMap.put("foodTracking", new FoodTracking());
        commandMap.put("trackFood", new TrackFood());
        commandMap.put("allUsersStatistic", new AllUsersStatistic());
        commandMap.put("manageAllUsers", new ManageAllUsers());
        commandMap.put("userSettings", new ClientSettings());
        commandMap.put("userStatistic", new ClientStatistic());
        commandMap.put("mealStatistic", new MealStatistic());
        commandMap.put("extendedStatistic", new ExtendedStatistic());
        commandMap.put("exception", new PageException());
        return commandMap;
    }

    public static void deleteUsersFromContext(HttpServletRequest request, String... logins) {
        HashSet<String> allUsers = (HashSet<String>) request.getServletContext().getAttribute(Attributes.REQUEST_USERS_ALL);
        Arrays.asList(logins).forEach(allUsers::remove);
        request.getServletContext().setAttribute(Attributes.REQUEST_USERS_ALL, allUsers);
    }

    public static String openUsersSession(HttpServletRequest request, User user) {

        HashSet<String> allUsers = (HashSet<String>) request.getServletContext().getAttribute(Attributes.REQUEST_USERS_ALL);

        if (allUsers == null) {
            log.debug(LogMessages.LOG_ALL_USERS_NULL);
            allUsers = new HashSet<>();
        }
        if (allUsers.contains(user.getUsername())) {
            log.warn(LogMessages.LOG_USER_DOUBLE_AUTH + " [" + user.getUsername() + "]");
            return Attributes.RETURN_STATEMENT_USER_LOGGED;
        }
        request.getSession().setAttribute(Attributes.REQUEST_USER, user);
        request.getSession().setAttribute(Attributes.PAGE_NAME, Attributes.PAGE_FOOD_TRACKING);

        allUsers.add(user.getUsername());
        request.getServletContext().setAttribute(Attributes.REQUEST_USERS_ALL, allUsers);
        log.debug(LogMessages.LOG_USER_LOGGED + "[" + user.getUsername() + "]");
        return Attributes.RETURN_STATEMENT_SUCCESS;
    }

    public static String checkUserIsLogged(HttpServletRequest request, String userName) {
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession()
                .getServletContext()
                .getAttribute("loggedUsers");

        if ((!loggedUsers.isEmpty())
                && (loggedUsers.stream().anyMatch(userName::equals))) {
            log.debug(LogMessages.LOG_USER_DOUBLE_AUTH + "[" + userName + "]");
            return Attributes.RETURN_STATEMENT_USER_LOGGED;
        }
        loggedUsers.add(userName);
        request.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
        log.debug(LogMessages.LOG_USER_LOGGED + "[" + userName + "]");
        return Attributes.RETURN_STATEMENT_USER_LOGGED_OUT;
    }

    public static Optional<User> extractUserFromHTTP(HttpServletRequest request) {
        Optional<User> userHttp;
        try {
            userHttp = Optional.ofNullable(UserMapper.extractFromHttpServletRequest(request));
        } catch (DataHttpException e) {
            log.error(LogMessages.LOG_USER_HTTP_NOT_EXTRACT + "[" + e.getMessage() + "]");
            userHttp = Optional.empty();
        }
        return userHttp;
    }

    public static Optional<User> extractRegisterUserFromHTTP(HttpServletRequest request) {
        Optional<User> userHttp;
        try {
            userHttp = Optional.ofNullable(UserMapper.extractRegisterFromHttpServletRequest(request));
        } catch (DataHttpException e) {
            log.error(LogMessages.LOG_USER_HTTP_NOT_EXTRACT + "[" + e.getMessage() + "]");
            userHttp = Optional.empty();
        }
        return userHttp;
    }
}
