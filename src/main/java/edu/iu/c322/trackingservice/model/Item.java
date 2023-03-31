package edu.iu.c322.trackingservice.model;

import java.util.Objects;

public class Item {
    private int itemId;
    private String name;
    private String status;
    private String date;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(o instanceof Item item)) return false;
        return getItemId() == item.getItemId() && Objects.equals(getName(), item.getName()) && Objects.equals(getStatus(), item.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemId(), getName(), getStatus());
    }
}
