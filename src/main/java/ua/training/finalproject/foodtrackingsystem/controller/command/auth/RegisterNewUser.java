package ua.training.finalproject.foodtrackingsystem.controller.command.auth;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.controller.command.Command;
import ua.training.finalproject.foodtrackingsystem.controller.command.CommandUtil;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;
import ua.training.finalproject.foodtrackingsystem.model.service.util.LoginService;
import ua.training.finalproject.foodtrackingsystem.model.service.util.RegisterService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class RegisterNewUser implements Command {
    private static final Logger log = Logger.getLogger(RegisterNewUser.class);
    private LoginService service = new LoginService();

    @Override
    public String execute(HttpServletRequest request) {
        RegisterService registerService = new RegisterService();
        String login = request.getParameter(Attributes.REQUEST_LOGIN);
        Optional<User> httpUser = CommandUtil.extractRegisterUserFromHTTP(request);
        Optional<User> optionalUserFromDb = service.checkLoginAndGetUser(login);
        if (optionalUserFromDb.isPresent()) {
            log.warn(LogMessages.LOG_LOGIN_OCCUPIED + "[Login: " + optionalUserFromDb.get().getUsername() + "]");
            return Attributes.RETURN_STATEMENT_USER_EXISTS_IN_DB;
        }
        httpUser.ifPresent(registerService::registerNewUser);

        log.debug(LogMessages.LOG_USER_REGISTERED + "[Login: " + httpUser.get().getUsername() + "]");
        return CommandUtil.openUsersSession(request, httpUser.get());
    }
}
