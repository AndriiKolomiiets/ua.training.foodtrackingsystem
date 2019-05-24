package ua.training.finalproject.foodtrackingsystem.model.service;

import ua.training.finalproject.foodtrackingsystem.model.JdbcDao;

public class LoginService {
        public String doLogin(String login, String password){
            JdbcDao jdbcDao = new JdbcDao();
           return jdbcDao.checkLoginInDB(login,password);
        }
}
