package ua.training.finalproject.foodtrackingsystem.controller.filter;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
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
 * Description: Non permit registered users to demonstration pages
 *
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
@WebFilter(urlPatterns = {"/fts/loginOrRegister", "/fts/registerNewUser",
        "/fts/logIn", "/ftl/"})
public class PageFilterRegistered extends AbstractFilter {
    private static final Logger log = Logger.getLogger(PageFilterRegistered.class);

    @Override
    protected void filter(HttpServletRequest request,
                          HttpServletResponse response,
                          FilterChain filterChain, Optional<User> user) throws IOException, ServletException {

        if (user.isPresent()) {
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0);
            log.warn(LogMessages.LOG_USER_GO_NON_REGISTERED_URL + " - [" + user.get().getUsername() + "]");
            request.getSession().setAttribute(Attributes.PAGE_NAME, Attributes.PAGE_MAIN);
            request.getRequestDispatcher(PagePath.USER_MAIN_PAGE).forward(request, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}