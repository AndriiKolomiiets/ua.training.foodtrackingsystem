package ua.training.finalproject.foodtrackingsystem.controller.filter;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.PagePath;
import ua.training.finalproject.foodtrackingsystem.controller.command.auth.Login;
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
 *
 * @author
 */
//todo: change pages
@WebFilter(urlPatterns = {"/fts/menuGeneralEdit", "/fts/menuGeneralEditWithError", "/fts/deleteGeneralMenuItem",
        "/fts/updateGeneralDish", "/fts/showUsers", "/fts/deleteUsers", "/fts/listUsersPage", "/fts/updateUsers",
        "/fts/showUsersAfterUpdateOrSearch", "/fts/searchUsersByEmail"})
public class PageFilterAdmin extends AbstractFilter {
    private static final Logger log = Logger.getLogger(PageFilterAdmin.class);

    @Override
    protected void filter(HttpServletRequest request,
                          HttpServletResponse response,
                          FilterChain filterChain, Optional<User> user) throws IOException, ServletException {

        if (user.isPresent()) {
            if (user.get().getRole().equals(Role.ADMIN)) {
                filterChain.doFilter(request, response);
            } else {
//                todo: logger
//                log.warn(Mess.LOG_USER_GO_ADMIN_URL + " - [" + user.get().getEmail() + "]");
                request.getSession().setAttribute(Attributes.PAGE_NAME, Attributes.PAGE_MAIN);
                request.getRequestDispatcher(PagePath.USER_MAIN_PAGE).forward(request, response);
            }
        } else {
//            log.warn(Mess.LOG_USER_GO_ADMIN_URL + " - [" + Role.UNKNOWN + "]");
            request.getRequestDispatcher(PagePath.INDEX).forward(request, response);
        }
    }
}