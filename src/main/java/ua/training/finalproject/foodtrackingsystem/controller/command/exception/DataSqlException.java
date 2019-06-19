package ua.training.finalproject.foodtrackingsystem.controller.command.exception;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class DataSqlException extends RuntimeException {

    public DataSqlException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}