package ua.training.finalproject.foodtrackingsystem.controller.command.auth;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.controller.command.Command;
import ua.training.finalproject.foodtrackingsystem.controller.command.CommandUtil;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;
import ua.training.finalproject.foodtrackingsystem.model.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class RegisterNewUser implements Command {
    private static final Logger log = Logger.getLogger(RegisterNewUser.class);
    private LoginService service = new LoginService();

    @Override
    public String execute(HttpServletRequest request) {
        CreateClientService createClientService = new CreateClientService();
        RegisterService registerService = new RegisterService();
        GetUserService getUserService = new GetUserService();
        String login = request.getParameter(Attributes.REQUEST_LOGIN);
        Optional<User> userFromDb=Optional.empty();
        Client client;
        SetClientToUserService setClientToUserService = new SetClientToUserService();
//        String pass = request.getParameter(Attributes.REQUEST_PASSWORD);
        Optional<User> httpUser = CommandUtil.extractRegisterUserFromHTTP(request);

        Optional<User> optionalUser = service.checkLoginAndGetUser(login);
        if (optionalUser.isPresent()) {
            log.warn(LogMessages.LOG_LOGIN_OCCUPIED + "[Login: " + optionalUser.get().getUsername() + "]");
            return Attributes.RETURN_STATEMENT_USER_EXISTS_IN_DB;
        }
        if (httpUser.isPresent()) {
            registerService.registerNewUser(httpUser.get());
//            userFromDb = getUserService.getUserByName(httpUser.get().getUsername());

//            if(userFromDb.isPresent()){
//                client = createClientService.createEmptyClient(userFromDb.get());
//                userFromDb.get().setClient(client);
//                setClientToUserService.set(userFromDb.get());
//            }


//            userFromDb.setClient(createClientService.createEmptyClient(userFromDb));
        }

        log.debug(LogMessages.LOG_USER_REGISTERED + "[Login: " + httpUser.get().getUsername() + "]");
        return CommandUtil.openUsersSession(request, httpUser.get());
    }
}
