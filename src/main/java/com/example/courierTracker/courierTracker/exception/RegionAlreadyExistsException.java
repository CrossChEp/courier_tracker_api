package com.example.courierTracker.courierTracker.exception;

public class RegionAlreadyExistsException extends RuntimeException {
    public RegionAlreadyExistsException(String message) {
        super(message);
    }
}
