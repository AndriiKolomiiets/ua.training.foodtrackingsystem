package ua.training.finalproject.foodtrackingsystem.model.entity;

public enum Role {
        USER, ADMIN, GUEST;

        public String getRole() {
            return name();
        }
}
