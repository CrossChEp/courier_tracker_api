package com.example.courierTracker.courierTracker.controller;

import com.example.courierTracker.courierTracker.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @PostMapping
    public ResponseEntity<Object> addRegion(@RequestBody String name) {
        regionService.addRegion(name);
        return ResponseEntity.ok("region was added");
    }
}
