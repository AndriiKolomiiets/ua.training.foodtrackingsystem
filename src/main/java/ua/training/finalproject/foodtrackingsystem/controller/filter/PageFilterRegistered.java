package ua.training.finalproject.foodtrackingsystem.controller.filter;

import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
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
 * @author
 */
@WebFilter(urlPatterns = {"/fts/loginOrRegister", "/fts/registerNewUser",
        "/fts/logIn", ""})
//todo: change default path into /ftl/
//@Log4j2
public class PageFilterRegistered extends AbstractFilter {

    @Override
    protected void filter(HttpServletRequest request,
                          HttpServletResponse response,
                          FilterChain filterChain, Optional<User> user) throws IOException, ServletException {

        if (user.isPresent()) {
//            log.warn(Mess.LOG_USER_GO_NON_REGISTERED_URL + " - [" + user.get().getEmail() + "]");
            request.getSession().setAttribute(Attributes.PAGE_NAME, Attributes.PAGE_MAIN);
            request.getRequestDispatcher(PagePath.USER_MAIN_PAGE).forward(request, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}