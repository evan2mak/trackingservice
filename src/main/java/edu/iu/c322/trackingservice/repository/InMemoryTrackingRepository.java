package edu.iu.c322.trackingservice.repository;
import edu.iu.c322.trackingservice.model.ItemOrd;
import edu.iu.c322.trackingservice.model.Orders;
import edu.iu.c322.trackingservice.model.TrackingStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryTrackingRepository {
    private List<Orders> orders = new ArrayList<>();

    public Orders findByOrderId(int orderId) {
        return orders.stream().filter(order -> order.getOrderId() == orderId).findFirst().orElse(null);
    }

    public TrackingStatus findByOrderIdAndItemId(int orderId, int itemId) {
        Orders order = findByOrderId(orderId);
        if (order == null) {
            throw new IllegalStateException("Order with this ID does not exist in the system.");
        }
        for (ItemOrd item : order.getItems()) {
            if (item.getItemId() == itemId) {
                return new TrackingStatus(item.getTrackingStatus().getTrackingStatus(),item.getTrackingStatus().getDate());
            }
        }
        throw new IllegalStateException("Item with this ID does not exist in the system.");
    }

//    public void update(int orderId, int itemId, String status) {
//        Orders order = findByOrderId(orderId);
//        if (order != null) {
//            ItemOrd item = order.getItems().stream().filter(i -> i.getItemId() == itemId).findFirst().orElse(null);
//            if (item != null) {
//                item.setTrackingStatus(status);
//            }
//            else {
//                throw new IllegalStateException("Item ID is not valid.");
//            }
//        }
//        else {
//            throw new IllegalStateException("Order ID is not valid.");
//        }
//    }

    public int create(Orders order) {
        int orderId = orders.size() + 1;
        order.setOrderId(orderId);

        int itemId = 1;
        for (ItemOrd item : order.getItems()) {
            item.setItemId(itemId);
            itemId++;
        }

        orders.add(order);
        return orderId;
    }
}

