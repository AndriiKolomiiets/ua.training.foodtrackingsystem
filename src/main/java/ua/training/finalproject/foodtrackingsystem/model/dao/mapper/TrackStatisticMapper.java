package ua.training.finalproject.foodtrackingsystem.model.dao.mapper;

import ua.training.finalproject.foodtrackingsystem.constants.Attributes;
import ua.training.finalproject.foodtrackingsystem.model.entity.TrackStatistic;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class TrackStatisticMapper implements ObjectMapper {
    @Override
    public Object extractFromResultSet(ResultSet rs) throws SQLException {
        TrackStatistic trackStatistic = new TrackStatistic();
        trackStatistic.setId(rs.getLong(Attributes.REQUEST_TRACK_STATISTIC_ID));
        trackStatistic.setDateTime(rs.getTimestamp(Attributes.REQUEST_DATE_TIME)
                .toLocalDateTime());
        trackStatistic.setNumber(rs.getInt(Attributes.REQUEST_NUMBER));

        return trackStatistic;
    }
}
