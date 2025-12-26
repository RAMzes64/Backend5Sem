package com.example.courseWork5REST.Controllers;

import com.example.courseWork5REST.DTO.CreateOrderRequest;
import com.example.courseWork5REST.DTO.OrderResponse;
import com.example.courseWork5REST.Models.OrderStatus;
import com.example.courseWork5REST.Services.BookingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final BookingService bookingService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody CreateOrderRequest request) {
        try {
            bookingService.add(request);
            return ResponseEntity.ok("Order is created");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity delete(@NonNull @PathVariable Integer id) {
        try {
            bookingService.delete(id);
            return ResponseEntity.ok("Order is deleted");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/updateStatus/{status}")
    public ResponseEntity updateStatus(
            @NonNull @PathVariable Integer id,
            @NonNull @PathVariable OrderStatus status) {
        try {
            bookingService.changeStatus(id, status);
            return ResponseEntity.ok("Orders status updated to\t" + status.name());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public List<OrderResponse> getAll() {
        return bookingService.getAll();
    }

    @GetMapping("/{id}/get")
    public ResponseEntity getById(@NonNull @PathVariable Integer id){
        try {
            return ResponseEntity.ok(bookingService.getById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/client/{id}")
    public ResponseEntity getByClientId(@NonNull @PathVariable Integer id) {
        try {
            return ResponseEntity.ok(bookingService.getByClientId(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
