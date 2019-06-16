package ua.training.finalproject.foodtrackingsystem.model.dao.mapper;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.controller.command.exception.DataHttpException;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDao.JdbcClientDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;
import ua.training.finalproject.foodtrackingsystem.model.entity.Role;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;
import ua.training.finalproject.foodtrackingsystem.model.entity.UserBuilder;
import ua.training.finalproject.foodtrackingsystem.model.service.GetClientService;
import ua.training.finalproject.foodtrackingsystem.model.service.GetRoleService;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserMapper implements ObjectMapper {
    private static final Logger log = Logger.getLogger(UserMapper.class);

    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
//        log.warn(LogMessages.LOG_DATABASE_EXCEPTION + "[>>>" + rs + "<<<]");
        String userName = null;
        Role role = null;
        Client client = null;
        User user=null;

        //todo: add additional method for Login with client
        long id  ;
        String pass  ;
        String email  ;
        UserBuilder userBuilder = new UserBuilder();
        GetClientService clientService = new GetClientService();
        GetRoleService roleService = new GetRoleService();
        if (rs.next()) {
//            String s = "sdf";
//            log.warn(LogMessages.LOG_DATABASE_EXCEPTION + "[>>>" + rs + "<<<]");
            System.out.println(rs);
            userName = rs.getString(Attributes.REQUEST_LOGIN);
            id = rs.getLong(Attributes.REQUEST_USER_ID);
            pass = rs.getString(Attributes.REQUEST_PASSWORD);
            email = rs.getString(Attributes.REQUEST_EMAIL);

//            client = clientService.getSimpleClientByUserName(userName);
            role = roleService.getRole(rs.getLong(Attributes.REQUEST_ROLE_ID));
            user = userBuilder.
                    buildId(id).
                    buildUsername(userName).
                    buildPassword(pass).
                    buildEmail(email).
                    buildRole(role).
//                buildClient(client).
        build();
        }
        return user;
    }

    public static User extractFromHttpServletRequest(HttpServletRequest req) throws DataHttpException {
        User user = new User();
        user.setUsername(req.getParameter(Attributes.REQUEST_LOGIN));
        user.setPassword(req.getParameter(Attributes.REQUEST_PASSWORD));
        user.setEmail(req.getParameter(Attributes.REQUEST_EMAIL));

        return user;
    }

    public static User extractRegisterFromHttpServletRequest(HttpServletRequest req) throws DataHttpException {
        User user = new User();
        user.setUsername(req.getParameter(Attributes.REQUEST_LOGIN));
        user.setPassword(req.getParameter(Attributes.REQUEST_PASSWORD));
        user.setEmail(req.getParameter(Attributes.REQUEST_EMAIL));
        user.setRole(Role.USER);

        return user;
    }
}
