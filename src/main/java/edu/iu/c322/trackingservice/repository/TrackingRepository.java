package edu.iu.c322.trackingservice.repository;
import edu.iu.c322.trackingservice.model.Item;
import edu.iu.c322.trackingservice.model.Order;
import edu.iu.c322.trackingservice.model.TrackingStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TrackingRepository {
    private List<Order> orders = new ArrayList<>();

    public Order findByOrderId(int orderId) {
        return orders.stream().filter(order -> order.getOrderId() == orderId).findFirst().orElse(null);
    }

    public TrackingStatus findByOrderIdAndItemId(int orderId, int itemId) {
        Order order = findByOrderId(orderId);
        if (order == null) {
            throw new IllegalStateException("Order with this ID does not exist in the system.");
        }
        for (Item item : order.getItems()) {
            if (item.getItemId() == itemId) {
                return new TrackingStatus(item.getStatus(), item.getDate());
            }
        }
        throw new IllegalStateException("Item with this ID does not exist in the system.");
    }

    public void update(int orderId, int itemId, String status) {
        Order order = findByOrderId(orderId);
        if (order != null) {
            Item item = order.getItems().stream().filter(i -> i.getItemId() == itemId).findFirst().orElse(null);
            if (item != null) {
                item.setStatus(status);
            }
            else {
                throw new IllegalStateException("Item ID is not valid.");
            }
        }
        else {
            throw new IllegalStateException("Order ID is not valid.");
        }
    }

    public int create(Order order) {
        int orderId = orders.size() + 1;
        order.setOrderId(orderId);

        int itemId = 1;
        for (Item item : order.getItems()) {
            item.setItemId(itemId);
            itemId++;
        }

        orders.add(order);
        return orderId;
    }
}

