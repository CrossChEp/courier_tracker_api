package com.example.courierTracker.courierTracker.controller;

import com.example.courierTracker.courierTracker.model.courier.CourierUpdateModel;
import com.example.courierTracker.courierTracker.model.courier.DefineCourierModel;
import com.example.courierTracker.courierTracker.model.courier.AddCourierTypeModel;
import com.example.courierTracker.courierTracker.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courier")
public class CourierController {
    @Autowired
    private CourierService courierService;

    @PostMapping
    public ResponseEntity<Object> addCourier(@RequestBody DefineCourierModel courierData) {
        courierService.addCourier(courierData);
        return ResponseEntity.ok("user was defined as a courier");
    }

    @PostMapping("/type")
    public ResponseEntity<Object> addCourierType(@RequestBody AddCourierTypeModel courierTypeData) {
        courierService.addCourierType(courierTypeData);
        return ResponseEntity.ok("courier type was added");
    }

    @PutMapping
    public ResponseEntity<Object> updateCourierData(@RequestBody CourierUpdateModel courierUpdateData) {
        courierService.updateCourier(courierUpdateData);
        return ResponseEntity.ok("courier was updated");
    }
    @GetMapping
    public ResponseEntity<Object> getCouriers() {
        return ResponseEntity.ok(courierService.getCouriers());
    }
    @DeleteMapping("/{courierId}")
    public ResponseEntity<Object> getCourier(@PathVariable long courierId) {
        courierService.deleteCourier(courierId);
        return ResponseEntity.ok("courier was deleted");
    }
}
