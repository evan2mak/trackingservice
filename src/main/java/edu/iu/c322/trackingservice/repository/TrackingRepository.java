package edu.iu.c322.trackingservice.repository;

import edu.iu.c322.trackingservice.model.ItemOrd;
import edu.iu.c322.trackingservice.model.Orders;
import edu.iu.c322.trackingservice.model.TrackingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingRepository extends JpaRepository<Orders, Integer> {
    Orders findByOrderIdAndItemsItemId(int orderId, int itemId);
    Orders findByOrderId(int orderId);
}
