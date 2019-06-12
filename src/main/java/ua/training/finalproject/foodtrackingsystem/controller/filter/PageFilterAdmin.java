package ua.training.finalproject.foodtrackingsystem.controller.filter;

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
 *
 * @author
 */
@WebFilter(urlPatterns = {"/swft/menuGeneralEdit", "/swft/menuGeneralEditWithError", "/swft/deleteGeneralMenuItem",
        "/swft/updateGeneralDish", "/swft/showUsers", "/swft/deleteUsers", "/swft/listUsersPage", "/swft/updateUsers",
        "/swft/showUsersAfterUpdateOrSearch", "/swft/searchUsersByEmail"})
//@Log4j2
public class PageFilterAdmin extends AbstractFilter {

    @Override
    protected void filter(HttpServletRequest request,
                          HttpServletResponse response,
                          FilterChain filterChain, Optional<User> user) throws IOException, ServletException {

        if (user.isPresent()) {
            if (user.get().getRole().equals(Role.ADMIN)) {
                filterChain.doFilter(request, response);
            } else {
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