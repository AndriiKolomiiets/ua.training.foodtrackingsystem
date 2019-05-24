package ua.training.finalproject.foodtrackingsystem.controller;

import ua.training.finalproject.foodtrackingsystem.model.service.LoginService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    LoginService service = null;

    public void init(ServletConfig config)throws ServletException {
        service = new LoginService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        response.setContentType("text");
        response.getWriter().write(service.doLogin(login, password));
    }
}
