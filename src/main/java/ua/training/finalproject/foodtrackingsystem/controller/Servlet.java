package ua.training.finalproject.foodtrackingsystem.controller;

import ua.training.finalproject.foodtrackingsystem.constants.PagePath;
import ua.training.finalproject.foodtrackingsystem.controller.command.Command;
import ua.training.finalproject.foodtrackingsystem.controller.command.CommandUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class Servlet extends HttpServlet {
    private Map<String, Command> commands;
    private static final String PAGE_PATH = "/fts/";
    private static final String REGEX_REDIRECT_PAGE = ".*/fts/";

    @Override
    public void init() {
        commands = CommandUtil.initializeCommands();

    }

    @Override
    public void destroy() {
        commands = null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String path = req.getRequestURI();

        path = path.replaceAll(REGEX_REDIRECT_PAGE, "");

        Command command = commands.getOrDefault(path, (r) -> PagePath.INDEX);

        String page = command.execute(req);

        if (page.contains(PagePath.REDIRECT)) {
            resp.sendRedirect(page.replace(PagePath.REDIRECT, PAGE_PATH));
        } else {

            if (page.equals("200")) {

//                resp.sendRedirect("http://localhost:8080/fts/mealStatistic");
                resp.setContentType("text/html;charset=UTF-8");
                resp.getWriter().write("200");
            } else {
                req.getRequestDispatcher(page).forward(req, resp);
            }


        }
    }
}
