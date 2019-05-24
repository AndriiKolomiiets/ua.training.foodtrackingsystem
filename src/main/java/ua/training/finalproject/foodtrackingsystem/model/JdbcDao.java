package ua.training.finalproject.foodtrackingsystem.model;

import java.sql.*;

public class JdbcDao {

    public String checkLoginInDB(String login, String pass){
        Connection connection = null;
        String message = null;
        PreparedStatement statement;
        ResultSet rs;
        try {
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/food_tracking_system_db?autoReconnect=true&useSSL=false",
                    "root",
                    "root11");
            statement = connection.prepareStatement("SELECT login, password FROM users WHERE login = ? AND password = ?");
            statement.setString(1, login);
            statement.setString(2, pass);
            rs = statement.executeQuery();
            if(rs.next()){
                message = "Success";
            }else{
                message = "Failure";
            }
        } catch (Exception e) {
            message = "Failure";
            e.printStackTrace();
        }
        return message;
    }
}
