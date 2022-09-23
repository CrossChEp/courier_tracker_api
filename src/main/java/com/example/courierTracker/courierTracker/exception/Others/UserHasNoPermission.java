package com.example.courierTracker.courierTracker.exception.Others;

public class UserHasNoPermission extends RuntimeException {
    public UserHasNoPermission(String message) {
        super(message);
    }
}
