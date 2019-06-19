package ua.training.finalproject.foodtrackingsystem.model.dao.mapper;

import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.model.entity.ClientTrack;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class ClientTrackMapper implements ObjectMapper {
    @Override
    public ClientTrack extractFromResultSet(ResultSet rs) throws SQLException {
        ClientTrack clientTrack = new ClientTrack();
        clientTrack.setId(rs.getLong(Attributes.REQUEST_CLIENT_TRACK_ID));
        clientTrack.setCaloriesStatus(rs.getString(Attributes.REQUEST_CALORIES_STATUS));
        clientTrack.setCaloriesToNorm(rs.getInt(Attributes.REQUEST_CALORIES_TO_NORM));
        clientTrack.setDate(rs.getDate(Attributes.REQUEST_DATE)
                .toLocalDate());

        return clientTrack;
    }
}
