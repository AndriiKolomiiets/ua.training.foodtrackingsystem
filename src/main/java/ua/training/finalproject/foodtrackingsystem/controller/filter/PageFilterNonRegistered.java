package ua.training.finalproject.foodtrackingsystem.controller.filter;

import com.sun.org.apache.bcel.internal.generic.LMUL;
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
 * Description: Direct non registered users to users pages
 *
 * @author
 */
//todo: add pages
@WebFilter(urlPatterns = {"/fts/homePage", "/fts/foodTracking", "/fts/dayMeal",
        "/fts/mealStatistic", "/fts/logOut", "/fts/userSettings", /*"wwwwwwww",
        "wwwwwwww", "wwwwwwww", "wwwwwwww"*/})
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