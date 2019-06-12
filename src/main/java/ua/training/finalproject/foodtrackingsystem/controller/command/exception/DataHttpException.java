package ua.training.finalproject.foodtrackingsystem.controller.command.exception;

public class DataHttpException extends Exception {

    public DataHttpException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
