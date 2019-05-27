package ua.training.finalproject.foodtrackingsystem.constants;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

public interface PagePath {
    String INDEX = "/index.jsp";
    String REDIRECT = "redirect:/";
    String INDEX_REDIRECT = "redirect:/index.jsp";
    String USER_MAIN_PAGE = "/WEB-INF/user/main.jsp";
    String USER_SETTINGS = "/WEB-INF/user/settings.jsp";
    String FOOD_TRACKING = "/WEB-INF/user/food_tracking.jsp";
    String USER_STATISTIC = "/WEB-INF/user/statistic.jsp";
    String MANAGE_ALL_USERS = "/WEB-INF/admin/manage_all_users.jsp";
    String ALL_USERS_STATISTIC = "/WEB-INF/admin/all_users_statistic.jsp";

}
