package ua.training.finalproject.foodtrackingsystem.controller.command;

import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.PagePath;
import ua.training.finalproject.foodtrackingsystem.controller.command.auth.LogOut;
import ua.training.finalproject.foodtrackingsystem.controller.command.auth.Login;
import ua.training.finalproject.foodtrackingsystem.controller.command.auth.RegisterNewUser;
import ua.training.finalproject.foodtrackingsystem.controller.command.direction.*;
import ua.training.finalproject.foodtrackingsystem.controller.command.exception.DataHttpException;
import ua.training.finalproject.foodtrackingsystem.model.dao.mapper.UserMapper;
import ua.training.finalproject.foodtrackingsystem.model.entity.Role;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public abstract class CommandUtil {

    public static Map<String, Command> initializeCommands() {
        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("login", new Login());
        commandMap.put("loginOrRegister", new LoginOrRegister());
        commandMap.put("register", new RegisterNewUser());
        commandMap.put("logOut", new LogOut());
        commandMap.put("dayMeal", new DayMeal());
        commandMap.put("mainPage", new MainPage());
        commandMap.put("changeLanguage", new ChangeLanguage());

        commandMap.put("foodTracking", new FoodTracking());
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

    public static void setUserRole(HttpServletRequest request,
                                   Role role, String name) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        context.setAttribute("userName", name);
        session.setAttribute("role", role);
    }

    public static String openUsersSession(HttpServletRequest request, User user) {

        HashSet<String> allUsers = (HashSet<String>) request.getServletContext().getAttribute(Attributes.REQUEST_USERS_ALL);

        if (allUsers == null) {
            //todo: logger
            allUsers = new HashSet<>();
        }
        if (allUsers.contains(user.getUsername())) {
//            log.warn(Mess.LOG_USER_DOUBLE_AUTH + " [" + user.getEmail() + "]");
//           todo: logger
            //todo: change attributes
            request.getSession().setAttribute(Attributes.PAGE_USER_ERROR_LOGIN, Attributes.PAGE_USER_LOGGED);
            return PagePath.LOGIN_OR_REGISTER;
        }

        request.getSession().setAttribute(Attributes.REQUEST_USER, user);
        request.getSession().setAttribute(Attributes.PAGE_NAME, Attributes.PAGE_FOOD_TRACKING);

        allUsers.add(user.getUsername());
        request.getServletContext().setAttribute(Attributes.REQUEST_USERS_ALL, allUsers);
//        log.info(Mess.LOG_USER_LOGGED + "[" + user.getEmail() + "]");
//todo: logger
        return PagePath.USER_MAIN_PAGE;
    }

    public static boolean checkUserIsLogged(HttpServletRequest request, String userName) {
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers");

//        if (){}
        if ((!loggedUsers.isEmpty())&(loggedUsers.stream().anyMatch(userName::equals))) {
            return true;
        }
        loggedUsers.add(userName);
        request.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
        return false;
    }

    public static Optional<User> extractUserFromHTTP(HttpServletRequest request) {
        Optional<User> userHttp;
        try {
            userHttp = Optional.ofNullable(UserMapper.extractFromHttpServletRequest(request));
        } catch (DataHttpException e) {
            //todo: logger
//            log.error(e.getMessage());
            userHttp = Optional.empty();
        }
        return userHttp;
    }

    public static Optional<User> extractRegisterUserFromHTTP(HttpServletRequest request) {
        Optional<User> userHttp;
        try {
            userHttp = Optional.ofNullable(UserMapper.extractRegisterFromHttpServletRequest(request));
        } catch (DataHttpException e) {
            //todo: logger
//            log.error(e.getMessage());
            userHttp = Optional.empty();
        }
        return userHttp;
    }
}
