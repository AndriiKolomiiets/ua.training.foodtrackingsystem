package ua.training.finalproject.foodtrackingsystem.controller.filter;

import ua.training.finalproject.foodtrackingsystem.constants.PagePath;
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
@WebFilter(urlPatterns = {"/fts/homePage", "/fts/foodTracking", "/fts/dayMeal",
        "/fts/mealStatistic", "/fts/logOut", /*"/fts/userSettings",*/ /*"wwwwwwww",
        "wwwwwwww", "wwwwwwww", "wwwwwwww"*/})
//@Log4j2
public class PageFilterNonRegistered extends AbstractFilter {

    @Override
    protected void filter(HttpServletRequest request,
                          HttpServletResponse response,
                          FilterChain filterChain, Optional<User> user) throws IOException, ServletException {

        if (user.isPresent()) {
            filterChain.doFilter(request, response);
        } else {
//            log.warn(Mess.LOG_USER_GO_USER_URL + " - [" + Roles.UNKNOWN + "]");
            response.sendRedirect("http://localhost:8080/fts/loginOrRegister");
//            request.getRequestDispatcher(PagePath.LOGIN_OR_REGISTER).forward(request, response);
        }
    }
}