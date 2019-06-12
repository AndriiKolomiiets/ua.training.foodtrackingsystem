package ua.training.finalproject.foodtrackingsystem.controller.command.exception;

public class DataSqlException extends RuntimeException {

    public DataSqlException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}