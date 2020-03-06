package com.ioverlap.dojo.carservice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            NotFoundException.class
    })
    @ResponseBody
    public ResponseEntity notFound(HttpServletRequest http, Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex, http.getRequestURI());
        return new ResponseEntity(errorMessage, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                       HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(err ->
            errors.put(((FieldError)err).getField(), err.getDefaultMessage())
        );
        ErrorMessage errorMessage = new ErrorMessage(ex, ((ServletWebRequest)request).getRequest().getRequestURI());
        errorMessage.setMessage(errorMessage.getMessage() + ". Argument not valid: "  + errors.toString());
        return new ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
    }

}
