package ua.training.finalproject.foodtrackingsystem.controller;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.constants.PagePath;
import ua.training.finalproject.foodtrackingsystem.controller.command.Command;
import ua.training.finalproject.foodtrackingsystem.controller.command.CommandUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;

public class Servlet extends HttpServlet {
    private Map<String, Command> commands;
    private static final Logger log = Logger.getLogger(Servlet.class);

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
        Command command;
        String pageOrResponse;
        HashSet<String> returnStatements = new HashSet<>();
        returnStatements.add(Attributes.RETURN_STATEMENT_SUCCESS);
        returnStatements.add(Attributes.RETURN_STATEMENT_USER_EXISTS_IN_DB);
        returnStatements.add(Attributes.RETURN_STATEMENT_USER_IS_EMPTY);
        returnStatements.add(Attributes.RETURN_STATEMENT_USER_LOGGED);
        returnStatements.add(Attributes.RETURN_STATEMENT_USER_LOGGED_OUT);
        returnStatements.add(Attributes.USER_ERROR_LOGIN);
        returnStatements.add(Attributes.USER_NOT_EXISTS);
        returnStatements.add(Attributes.USER_ERROR_PASSWORD);
        returnStatements.add(Attributes.RETURN_STATEMENT_FOOD_EXISTS);
        returnStatements.add(Attributes.RETURN_STATEMENT_WRONG_ID);
        String path = req.getRequestURI();

        path = path.replaceAll(Attributes.REGEX_REDIRECT_PAGE, "");

        command = commands.getOrDefault(path, (r) -> PagePath.INDEX);
        pageOrResponse = command.execute(req);

        if (pageOrResponse.contains(PagePath.REDIRECT)) {
            resp.sendRedirect(pageOrResponse.replace(PagePath.REDIRECT, Attributes.PAGE_PATH));
            log.debug(LogMessages.LOG_PAGE_REDIRECTED + "[Path: " + pageOrResponse + "]");
        } else {
            for (String s : returnStatements) {
                if (pageOrResponse.equals(s)) {
                    resp.setContentType(Attributes.HTML_CONTENT_TYPE);
                    resp.getWriter().write(pageOrResponse);
                    log.debug(LogMessages.LOG_SEND_RESPONSE + "[" + pageOrResponse + "] - to request: " + req.getRequestURI());
                    return;
                }
            }
            req.getRequestDispatcher(pageOrResponse).forward(req, resp);
        }
    }
}
