package ua.training.finalproject.foodtrackingsystem.controller.listener;

import ua.training.finalproject.foodtrackingsystem.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        User user;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getRequestURI();
        if(path.contains("add-meal")) {//TODO: rewrite add user roles
            if ((user = (User) ((HttpServletRequest) request).getSession().getAttribute("user")) != null) {
                chain.doFilter(request,response);
            }else{
                response.getWriter().append("AccessDenied");
                return;
            }
        }else{
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}