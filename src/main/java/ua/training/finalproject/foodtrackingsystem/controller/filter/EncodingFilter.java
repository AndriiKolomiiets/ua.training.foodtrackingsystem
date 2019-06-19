package ua.training.finalproject.foodtrackingsystem.controller.filter;

import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
@WebFilter(urlPatterns = "/*")
public class EncodingFilter extends AbstractFilter {

    @Override
    protected void filter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Optional<User> user) throws IOException, ServletException {
        response.setContentType(Attributes.HTML_TEXT);
        response.setCharacterEncoding(Attributes.HTML_ENCODE);
        request.setCharacterEncoding(Attributes.HTML_ENCODE);

        if (!user.isPresent()) {
            request.getSession().setAttribute(Attributes.PAGE_NAME, Attributes.PAGE_DEMONSTRATION);
        }

        filterChain.doFilter(request, response);
    }
}