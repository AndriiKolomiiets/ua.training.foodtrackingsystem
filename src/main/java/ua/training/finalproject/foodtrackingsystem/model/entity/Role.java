package ua.training.finalproject.foodtrackingsystem.model.entity;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public enum Role {
        USER, ADMIN, GUEST;

        public String getRole() {
            return name();
        }
}
