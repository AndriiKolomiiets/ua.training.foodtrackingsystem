package ua.training.finalproject.foodtrackingsystem.controller.filter;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.PagePath;
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
 * Description: Non permit users with role non 'ADMIN' to admin pages
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
@WebFilter(urlPatterns = {"/fts/userManagement", "/fts/appStatistic",
        "/fts/updateFood", "/fts/showUsers", "/fts/deleteUsers", "/fts/updateUsers"})
public class PageFilterAdmin extends AbstractFilter {
    private static final Logger log = Logger.getLogger(PageFilterAdmin.class);

    @Override
    protected void filter(HttpServletRequest request,
                          HttpServletResponse response,
                          FilterChain filterChain, Optional<User> user) throws IOException, ServletException {

        if (user.isPresent()) {
            if (user.get().getRole().equals(Role.ADMIN)) {
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
                response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
                response.setDateHeader("Expires", 0);
                filterChain.doFilter(request, response);
            } else {
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
                response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
                response.setDateHeader("Expires", 0);
                request.getSession().setAttribute(Attributes.PAGE_NAME, Attributes.PAGE_MAIN);
                request.getRequestDispatcher(PagePath.USER_MAIN_PAGE).forward(request, response);
            }
        } else {
            request.getRequestDispatcher(PagePath.INDEX).forward(request, response);
        }
    }
}