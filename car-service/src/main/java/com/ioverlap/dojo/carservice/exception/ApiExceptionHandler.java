package com.ioverlap.dojo.carservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({
            NotFoundException.class
    })
    @ResponseBody
    public ResponseEntity notFound(HttpServletRequest http, Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex, http.getRequestURI());
        return new ResponseEntity(errorMessage, HttpStatus.NOT_FOUND);
    }

}
