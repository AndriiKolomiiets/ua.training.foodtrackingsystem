package ua.training.finalproject.foodtrackingsystem.model.service;

import ua.training.finalproject.foodtrackingsystem.model.entity.Client;

import java.time.LocalDate;
import java.time.Period;

public class CalcCaloriesNormService {
    public int calcNorm(Client client) {
        LocalDate birthDate = client.getBirthDate();
        LocalDate today = LocalDate.now();
        int age = Period.between(birthDate, today).getYears();
        return (client.getHeight() +
                client.getWeight() +
                (client.getLifeStyleCoefficient() +
                age)) *7;
    }
}
