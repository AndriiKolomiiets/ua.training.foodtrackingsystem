package ua.training.finalproject.foodtrackingsystem.controller.filter;

import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

/**
 * Abstract class for all filters.
 *
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 * @see EncodingFilter
 * @see PageFilterRegistered
 * @see PageFilterNonRegistered
 * @see PageFilterAdmin
 */
public abstract class AbstractFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        Optional<User> user = Optional.ofNullable((User) session.getAttribute(Attributes.REQUEST_USER));

        filter(request, response, filterChain, user);
    }

    @Override
    public void destroy() {
    }

    protected abstract void filter(HttpServletRequest request,
                                   HttpServletResponse response,
                                   FilterChain filterChain,
                                   Optional<User> user) throws IOException, ServletException;
}
