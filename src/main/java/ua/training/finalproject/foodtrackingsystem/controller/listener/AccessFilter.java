package ua.training.finalproject.foodtrackingsystem.controller.listener;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AccessFilter implements Filter {
    private static final Logger log = Logger.getLogger(AccessFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getRequestURI();
        if(path.contains("add-meal")) {//TODO: rewrite add user roles
            if (((HttpServletRequest) request).getSession().getAttribute("user") != null) {
                chain.doFilter(request,response);
            }else{
                response.getWriter().append("AccessDenied");
                log.warn(LogMessages.LOG_USER_ACCESS_DENIED);
            }
        }else{
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {
    }
}