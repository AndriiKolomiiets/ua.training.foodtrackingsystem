package ua.training.finalproject.foodtrackingsystem.model.dao.mapper;

import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.controller.command.exception.DataHttpException;
import ua.training.finalproject.foodtrackingsystem.model.entity.Role;
import ua.training.finalproject.foodtrackingsystem.model.entity.User;
import ua.training.finalproject.foodtrackingsystem.model.entity.UserBuilder;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserMapper implements ObjectMapper {
    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        Role role;
        UserBuilder userBuilder = new UserBuilder();
        RoleMapper roleMapper = new RoleMapper();

        role = roleMapper.extractFromResultSet(rs);

        return userBuilder.
                buildId(rs.getLong(Attributes.REQUEST_USER_ID)).
                buildUsername(rs.getString(Attributes.REQUEST_LOGIN)).
                buildPassword(rs.getString(Attributes.REQUEST_PASSWORD)).
                buildEmail(rs.getString(Attributes.REQUEST_EMAIL)).
                buildRole(role).
                build();
    }

    public static User extractFromHttpServletRequest(HttpServletRequest req) throws DataHttpException {
        User user = new User();
        user.setUsername(req.getParameter(Attributes.REQUEST_LOGIN));
        user.setPassword(req.getParameter(Attributes.REQUEST_PASSWORD));
        user.setEmail(req.getParameter(Attributes.REQUEST_EMAIL));
        user.setRole(Role.valueOf(req.getParameter(Attributes.REQUEST_ROLE)));

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
