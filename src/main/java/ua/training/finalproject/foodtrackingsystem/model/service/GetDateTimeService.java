package ua.training.finalproject.foodtrackingsystem.model.service;

import java.time.LocalDateTime;

public class GetDateTimeService {
    public static LocalDateTime getTime(){

        return LocalDateTime.now();
    }
}
