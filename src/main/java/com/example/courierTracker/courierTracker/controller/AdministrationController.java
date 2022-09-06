package com.example.courierTracker.courierTracker.controller;

import com.example.courierTracker.courierTracker.model.AdminDataModel;
import com.example.courierTracker.courierTracker.service.AdministrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdministrationController {
    @Autowired
    private AdministrationService adminService;

    @PostMapping
    public ResponseEntity<Object> promoteUserToAdmin(@RequestBody AdminDataModel adminData) {
        adminService.provideAdmin(adminData);
        return ResponseEntity.ok("user was promoted to admin");
    }
}
