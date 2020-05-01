package com.ioverlap.dojo.carservice.exception;

public class NotFoundException extends RuntimeException {

    private static final String DETAILS = "Not Found (404).";

    public NotFoundException(String message) {
        super(DETAILS + " " + message);
    }
}
