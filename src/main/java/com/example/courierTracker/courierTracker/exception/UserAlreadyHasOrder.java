package com.example.courierTracker.courierTracker.exception;

public class UserAlreadyHasOrder extends RuntimeException {
    public UserAlreadyHasOrder(String message) {
        super(message);
    }
}
