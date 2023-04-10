package edu.iu.c322.trackingservice.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class TrackingStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int trackingStatusId;
    private String trackingStatus;
    private String date;

    public TrackingStatus(String trackingStatus, String date) {
        this.trackingStatus = trackingStatus;
        this.date = date;
    }

    public TrackingStatus() {
    }

    public String getTrackingStatus() {
        return trackingStatus;
    }

    public void setTrackingStatus(String trackingStatus) {
        this.trackingStatus = trackingStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrackingStatus that)) return false;
        return Objects.equals(getTrackingStatus(), that.getTrackingStatus()) && Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTrackingStatus(), getDate());
    }
}
