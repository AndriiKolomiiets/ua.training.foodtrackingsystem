package ua.training.finalproject.foodtrackingsystem.model.dao;

import ua.training.finalproject.foodtrackingsystem.model.dao.dao.*;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract ClientDao createClientDao();

    public abstract UserDao createUserDao();

    public abstract ClientTrackDao createClientTrackDao();

    public abstract DayMealDao createDayMealDao();

    public abstract FoodDao createFoodDao();

    public abstract TrackStatisticDao createTrackStatisticDao();

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JdbcDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}
