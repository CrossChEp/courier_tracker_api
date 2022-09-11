package com.example.courierTracker.courierTracker.exception;

public class UserHasNoPermission extends RuntimeException {
    public UserHasNoPermission(String message) {
        super(message);
    }
}
