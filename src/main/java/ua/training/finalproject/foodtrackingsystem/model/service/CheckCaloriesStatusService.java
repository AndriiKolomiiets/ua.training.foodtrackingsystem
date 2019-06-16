package ua.training.finalproject.foodtrackingsystem.model.service;

import ua.training.finalproject.foodtrackingsystem.constants.Attributes;

public class CheckCaloriesStatusService {
    public String checkStatus(Integer caloriesToNorm){
        if (caloriesToNorm<0){
            return Attributes.CALORIES_STATUS_EXCEEDED;
        }
        return Attributes.CALORIES_STATUS_OK;
    }
}
