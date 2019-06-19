package ua.training.finalproject.foodtrackingsystem.model.service.util;

import java.time.LocalDateTime;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class GetDateTimeService {
    public static LocalDateTime getTime(){

        return LocalDateTime.now();
    }
}
