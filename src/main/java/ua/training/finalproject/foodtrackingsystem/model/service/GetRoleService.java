package ua.training.finalproject.foodtrackingsystem.model.service;

import org.apache.log4j.Logger;
import ua.training.finalproject.foodtrackingsystem.constants.LogMessages;
import ua.training.finalproject.foodtrackingsystem.model.dao.DaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.JdbcDaoFactory;
import ua.training.finalproject.foodtrackingsystem.model.dao.dao.RoleDao;
import ua.training.finalproject.foodtrackingsystem.model.entity.Role;

import java.util.Optional;

public class GetRoleService {
    private static final Logger log = Logger.getLogger(GetRoleService.class);

    public Role getRole(Long roleId) {
        DaoFactory daoFactory;
        daoFactory = JdbcDaoFactory.getInstance();
        RoleDao roleDao = daoFactory.createRoleDao();
        Optional<Role> role = roleDao.findById(roleId);
        if (!role.isPresent()){
            log.error(LogMessages.LOG_USER_ROLE_MISSED);
        }
        roleDao.close();
        return role.get();
    }
}
