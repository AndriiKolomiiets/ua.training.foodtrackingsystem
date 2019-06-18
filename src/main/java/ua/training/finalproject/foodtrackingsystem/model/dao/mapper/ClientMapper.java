package ua.training.finalproject.foodtrackingsystem.model.dao.mapper;

import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.model.entity.Client;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientMapper implements ObjectMapper {
    @Override
    public Client extractFromResultSet(ResultSet rs) throws SQLException {
        while (rs.next()) {
            Client client = new Client();
            client.setId(rs.getLong(Attributes.REQUEST_CLIENT_ID));
            Date date = rs.getDate(Attributes.REQUEST_BIRTH_DATE);
            if (date != null) {
                client.setBirthDate(date.toLocalDate());
            }
            client.setCaloriesNorm(rs.getInt(Attributes.REQUEST_CALORIES_NORM));
            client.setHeight(rs.getInt(Attributes.REQUEST_HEIGHT));
            client.setWeight(rs.getInt(Attributes.REQUEST_WEIGHT));
            client.setLifeStyleCoefficient(rs.getInt(Attributes.REQUEST_LIFE_STYLE));
            return client;
        }
        return null;
    }
}
