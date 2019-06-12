package ua.training.finalproject.foodtrackingsystem.model.entity;

import java.time.LocalDate;
import java.util.List;

public class ClientTrack implements Entity{
    private Long id;
    private LocalDate date;
    private String caloriesStatus;
    private Integer caloriesToNorm;
    private List<TrackStatistic> trackStatisticList;

    public ClientTrack(Long id, LocalDate date, String caloriesStatus, Integer caloriesToNorm, List<TrackStatistic> trackStatisticList) {
        this.id = id;
        this.date = date;
        this.caloriesStatus = caloriesStatus;
        this.caloriesToNorm = caloriesToNorm;
        this.trackStatisticList = trackStatisticList;
    }

    public ClientTrack() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCaloriesStatus() {
        return caloriesStatus;
    }

    public void setCaloriesStatus(String caloriesStatus) {
        this.caloriesStatus = caloriesStatus;
    }

    public Integer getCaloriesToNorm() {
        return caloriesToNorm;
    }

    public void setCaloriesToNorm(Integer caloriesToNorm) {
        this.caloriesToNorm = caloriesToNorm;
    }

    public List<TrackStatistic> getTrackStatisticList() {
        return trackStatisticList;
    }

    public void setTrackStatisticList(List<TrackStatistic> trackStatisticList) {
        this.trackStatisticList = trackStatisticList;
    }
}
