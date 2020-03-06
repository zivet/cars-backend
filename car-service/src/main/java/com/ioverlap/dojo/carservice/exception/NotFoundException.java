package com.ioverlap.dojo.carservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private static final String DETAILS = "Not Found (404).";

    public NotFoundException(String message) {
        super(DETAILS + " " + message);
    }
}
