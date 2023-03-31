package edu.iu.c322.trackingservice.controller;
import edu.iu.c322.trackingservice.model.Order;
import edu.iu.c322.trackingservice.model.TrackingStatus;
import edu.iu.c322.trackingservice.model.UpdateRequest;
import edu.iu.c322.trackingservice.repository.TrackingRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trackings")
public class TrackingController {
    private TrackingRepository repository;

    public TrackingController(TrackingRepository repository){
        this.repository = repository;
    }

    @GetMapping("/{orderId}/{itemId}")
    public TrackingStatus getTrackingStatus(@PathVariable int orderId, @PathVariable int itemId) {
        return repository.findByOrderIdAndItemId(orderId, itemId);
    }

    @PutMapping("/{orderId}")
    public void update(@Valid @RequestBody UpdateRequest request) {
        repository.update(request.getOrderId(), request.getItemId(), request.getStatus());
    }

    @PostMapping
    public void create(@Valid @RequestBody Order order) {
        repository.create(order);
    }
}


