package edu.iu.c322.trackingservice.model;

import java.util.Objects;

public class TrackingStatus {
    private String status;
    private String date;

    public TrackingStatus(String status, String date) {
        this.status = status;
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return Objects.equals(getStatus(), that.getStatus()) && Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatus(), getDate());
    }
}
