package ua.training.finalproject.foodtrackingsystem.model.service.daymeal;

import ua.training.finalproject.foodtrackingsystem.constants.Attributes;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class CheckCaloriesStatusService {
    public String checkStatus(Integer caloriesToNorm){
        if (caloriesToNorm<0){
            return Attributes.CALORIES_STATUS_EXCEEDED;
        }
        return Attributes.CALORIES_STATUS_OK;
    }
}
