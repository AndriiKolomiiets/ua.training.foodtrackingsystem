package ua.training.finalproject.foodtrackingsystem.controller.command.direction;

import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.PagePath;
import ua.training.finalproject.foodtrackingsystem.controller.command.Command;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.service.admin.GetAllClientsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class Admin implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        List<Client> clients;
        GetAllClientsService getAllClientsService = new GetAllClientsService();
        clients = getAllClientsService.getClientList();

        request.getSession().setAttribute(Attributes.REQUEST_CLIENT_LIST, clients);

        return PagePath.ADMIN;
    }
}
