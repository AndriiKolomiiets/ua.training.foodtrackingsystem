package ua.training.finalproject.foodtrackingsystem.controller.filter;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.model.entity.Role;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Prevent non-registered users to get to the website.
 *
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
@WebFilter(urlPatterns = {"/fts/homePage", "/fts/foodTracking", "/fts/dayMeal",
        "/fts/mealStatistic", "/fts/logOut", "/fts/userSettings"})
public class PageFilterNonRegistered extends AbstractFilter {
    private static final Logger log = Logger.getLogger(PageFilterNonRegistered.class);

    @Override
    protected void filter(HttpServletRequest request,
                          HttpServletResponse response,
                          FilterChain filterChain, Optional<User> user) throws IOException, ServletException {

        if (user.isPresent()) {
            filterChain.doFilter(request, response);
        } else {
            log.warn(LogMessages.LOG_USER_GO_USER_URL + " - [" + Role.GUEST + "]");
            response.sendRedirect("http://localhost:8080/fts/loginOrRegister");
        }
    }
}