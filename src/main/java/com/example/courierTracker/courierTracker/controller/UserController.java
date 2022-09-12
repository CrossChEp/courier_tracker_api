package com.example.courierTracker.courierTracker.controller;

import com.example.courierTracker.courierTracker.exception.alreadyExistsException.UserAlreadyExistsException;
import com.example.courierTracker.courierTracker.model.user.UserRegisterModel;
import com.example.courierTracker.courierTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
	@PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserRegisterModel userData) {
        try {
            userService.addUserToDatabase(userData);
            return ResponseEntity.ok("user was registered");
        } catch (UserAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }
}
