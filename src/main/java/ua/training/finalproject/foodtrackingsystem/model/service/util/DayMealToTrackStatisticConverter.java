package ua.training.finalproject.foodtrackingsystem.model.service.util;

import ua.training.finalproject.foodtrackingsystem.model.entity.ClientTrack;
import ua.training.finalproject.foodtrackingsystem.model.entity.DayMeal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class DayMealToTrackStatisticConverter {
    public Set<ClientTrack> convert(List<DayMeal> dayMealList) {
        ClientTrack clientTrack = new ClientTrack();
        Set<ClientTrack> clientTrackSet = new HashSet<>();
        for (DayMeal dayMeal : dayMealList) {
            clientTrack.setClient(dayMeal.getClient());
            clientTrack.setCaloriesStatus(dayMeal.getCaloriesStatus());
            clientTrack.setCaloriesToNorm(dayMeal.getCaloriesToNorm());
            LocalDateTime localDateTime = dayMeal.getDateTime();
            LocalDate localDate = localDateTime.toLocalDate();
            clientTrack.setDate(localDate);
            clientTrackSet.add(clientTrack);
        }
        return clientTrackSet;
    }
}
