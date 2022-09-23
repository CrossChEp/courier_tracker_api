package com.example.courierTracker.courierTracker.exception.Others;

public class UserAlreadyHasOrder extends RuntimeException {
    public UserAlreadyHasOrder(String message) {
        super(message);
    }
}
