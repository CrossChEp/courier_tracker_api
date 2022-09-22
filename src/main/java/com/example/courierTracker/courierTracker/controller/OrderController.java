package com.example.courierTracker.courierTracker.controller;

import com.example.courierTracker.courierTracker.exception.UserAlreadyHasOrder;
import com.example.courierTracker.courierTracker.exception.UserHasNoPermission;
import com.example.courierTracker.courierTracker.model.order.OrderAddModel;
import com.example.courierTracker.courierTracker.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Object> addOrder(@RequestBody OrderAddModel orderData) {
        orderService.addOrder(orderData);
        return ResponseEntity.ok("order was added");
    }
    @GetMapping
    public ResponseEntity<Object> getOrders() {
        try {
            return ResponseEntity.ok(orderService.getOrders());
        } catch (ParseException e) {
            return ResponseEntity.badRequest().body("something happened");
        }
    }

    @PostMapping("/{orderId}")
    public ResponseEntity<Object> acceptOrder(@PathVariable long orderId) {
        try {
            orderService.acceptOrder(orderId);
            return new ResponseEntity<>("order was accepted", HttpStatus.OK);
        } catch (UserAlreadyHasOrder e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/finish")
    public ResponseEntity<Object> finishOrder() {
        try {
            orderService.finishOrder();
            return ResponseEntity.ok("user finished order");
        } catch (UserHasNoPermission e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
}
