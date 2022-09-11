package com.example.courierTracker.courierTracker.controller;

import com.example.courierTracker.courierTracker.exception.alreadyExistsException.RoleAlreadyExists;
import com.example.courierTracker.courierTracker.model.role.AddRoleModel;
import com.example.courierTracker.courierTracker.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<Object> addRole(@RequestBody AddRoleModel roleData) {
        try {
            roleService.addRoleToDatabase(roleData);
            return ResponseEntity.ok("role was added to database");
        } catch (RoleAlreadyExists e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }
}
