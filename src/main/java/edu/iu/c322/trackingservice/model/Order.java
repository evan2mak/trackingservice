package edu.iu.c322.trackingservice.model;

import java.util.List;
import java.util.Objects;

public class Order {
    private int orderId;
    private List<Item> items;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return getOrderId() == order.getOrderId() && Objects.equals(getItems(), order.getItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getItems());
    }
}
