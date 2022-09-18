package com.example.courierTracker.courierTracker.controller;

import com.example.courierTracker.courierTracker.model.order.OrderAddModel;
import com.example.courierTracker.courierTracker.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
