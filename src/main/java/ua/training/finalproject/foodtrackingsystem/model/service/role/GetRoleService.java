package ua.training.finalproject.foodtrackingsystem.model.service.role;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.RoleDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.Role;

import java.util.Optional;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class GetRoleService {
    private static final Logger log = Logger.getLogger(GetRoleService.class);

    public Role getRole(Long roleId) {
        DaoFactory daoFactory;
        daoFactory = JdbcDaoFactory.getInstance();
        Optional<Role> role;
        try(RoleDao roleDao = daoFactory.createRoleDao()) {
            role = roleDao.findById(roleId);
        }
        if (!role.isPresent()){
            log.error(LogMessages.LOG_USER_ROLE_MISSED);
        }
        return role.get();
    }
}
