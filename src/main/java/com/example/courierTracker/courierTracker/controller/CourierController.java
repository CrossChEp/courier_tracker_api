package com.example.courierTracker.courierTracker.controller;

import com.example.courierTracker.courierTracker.model.UserModels.DefineCourierModel;
import com.example.courierTracker.courierTracker.model.courierTypeModels.AddCourierTypeModel;
import com.example.courierTracker.courierTracker.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
