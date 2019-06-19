package ua.training.finalproject.foodtrackingsystem.model.service.util;

import org.junit.Before;
import org.junit.Test;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

public class CalcCaloriesNormServiceTest {
    private Client client;

    @Before
    public void setUp() {
        client = new Client();
        client.setBirthDate(LocalDate.of(2002, Month.JANUARY, 1));
        client.setWeight(70);
        client.setHeight(180);
        client.setLifeStyleCoefficient(1);

    }

    @Test
    public void calcNorm() {
        CalcCaloriesNormService calcService = new CalcCaloriesNormService();
        int testCaloriesNorm = calcService.calcNorm(client);
        assertEquals(1876, testCaloriesNorm);
    }
}