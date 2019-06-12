package ua.training.finalproject.foodtrackingsystem.model.dao.mapper;

import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientMapper implements ObjectMapper {
    @Override
    public Client extractFromResultSet(ResultSet rs) throws SQLException {
        Client client = new Client();
        client.setId(rs.getLong(Attributes.REQUEST_CLIENT_ID));
        client.setBirthDate(rs.getDate(Attributes.REQUEST_BIRTH_DATE));
        client.setCaloriesNorm(rs.getInt(Attributes.REQUEST_CALORIES_NORM));
        client.setHeight(rs.getInt(Attributes.REQUEST_HEIGHT));
        client.setWeight(rs.getInt(Attributes.REQUEST_WEIGHT));
        client.setLifeStyleCoefficient(rs.getInt(Attributes.REQUEST_LIFE_STYLE));

        return client;
    }
}
