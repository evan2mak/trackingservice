package edu.iu.c322.trackingservice.controller;

import edu.iu.c322.trackingservice.model.ItemOrd;
import edu.iu.c322.trackingservice.model.Orders;
import edu.iu.c322.trackingservice.model.TrackingStatus;
import edu.iu.c322.trackingservice.model.UpdateRequest;
import edu.iu.c322.trackingservice.repository.TrackingRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trackings")
public class TrackingController {

    private final TrackingRepository repository;

    public TrackingController(TrackingRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{orderId}/{itemId}")
    public TrackingStatus getTrackingStatus(@PathVariable int orderId, @PathVariable int itemId) {
        Orders order = repository.findByOrderIdAndItemsItemId(orderId, itemId);
        if (order != null) {
            for (ItemOrd item : order.getItems()) {
                if (item.getItemId() == itemId) {
                    return item.getTrackingStatus();
                }
            }
            throw new IllegalStateException("Item ID is not valid.");
        }
        throw new IllegalStateException("Order ID is not valid.");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{orderId}")
    public void update(@Valid @RequestBody UpdateRequest request) {
        Orders order = repository.findByOrderId(request.getOrderId());
        if (order != null) {
            boolean found = false;
            for (ItemOrd item : order.getItems()) {
                if (item.getItemId() == request.getItemId()) {
                    found = true;
                    item.setTrackingStatus(request.getTrackingStatus());
                    break;
                }
            }
            if (!found) {
                throw new IllegalArgumentException("Item ID is not valid.");
            }
            repository.save(order);
        }
        else {
            throw new IllegalStateException("Order ID is not valid.");
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public int create(@Valid @RequestBody Orders order) {
        Orders newOrder = repository.save(order);
        return newOrder.getOrderId();
    }
}



