package ua.training.finalproject.foodtrackingsystem.controller.command.process;

import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.controller.command.Command;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;
import ua.training.finalproject.foodtrackingsystem.model.service.client.DeleteClientWithTrackService;
import ua.training.finalproject.foodtrackingsystem.model.service.client.GetClientService;
import ua.training.finalproject.foodtrackingsystem.model.service.user.DeleteUserService;
import ua.training.finalproject.foodtrackingsystem.model.service.user.GetUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class DeleteUser implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        DeleteUserService deleteUserService = new DeleteUserService();
        DeleteClientWithTrackService deleteClientWithTrackService = new DeleteClientWithTrackService();
        GetClientService getClientService = new GetClientService();
        GetUserService getUserService = new GetUserService();
        String stringUserId = request.getParameter(Attributes.REQUEST_USER_ID);
        Optional<User> user;
        Client client;
        Long userId;
   /*     if (!stringUserId.matches(".*\\d.*")) {
            return Attributes.RETURN_STATEMENT_WRONG_ID;
        }*/
//        userId = Long.valueOf(stringUserId);
//        user = getUserService.getUserById(userId);
        user = getUserService.getUserByName(stringUserId);

        if (user.isPresent()) {
            client = getClientService.getClient(user.get());
            if (client != null) {
                deleteClientWithTrackService.delete(client.getId());
            }
        }
        deleteUserService.delete(user.get().getId());
      return Attributes.RETURN_STATEMENT_SUCCESS;
    }
}
