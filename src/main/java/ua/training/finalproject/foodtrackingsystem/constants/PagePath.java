package ua.training.finalproject.foodtrackingsystem.constants;

/**
 * Initializing interface.
 * Here are stored page path constants.
 *
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public interface PagePath {
    String INDEX = "/index.jsp";
    String REDIRECT = "redirect:/";
    String INDEX_REDIRECT = "redirect:/index.jsp";
    String LOGIN_OR_REGISTER_REDIRECT = "redirect:/loginOrRegister";
    String USER_MAIN_PAGE = "/WEB-INF/user/main.jsp";
    String USER_SETTINGS = "/WEB-INF/user/settings.jsp";
    String ADMIN = "/WEB-INF/admin/user_management.jsp";
    String USER_FOOD_TRACKING = "/WEB-INF/user/food_tracking.jsp";
    String USER_DAY_MEAL = "/WEB-INF/user/day_meal.jsp";
    String USER_EXTENDED_STATISTIC = "/WEB-INF/user/extended_statistic.jsp";
    String USER_MEAL_STATISTIC = "/WEB-INF/user/meal_statistic.jsp";
    String MANAGE_ALL_USERS = "/WEB-INF/admin/manage_all_users.jsp";
    String ALL_USERS_STATISTIC = "/WEB-INF/admin/all_users_statistic.jsp";
    String LOGIN_OR_REGISTER = "/WEB-INF/loginOrRegister.jsp";
    String ERROR_PAGE = "/WEB-INF/error.jsp";
}
