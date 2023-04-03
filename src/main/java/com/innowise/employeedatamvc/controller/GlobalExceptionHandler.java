package com.innowise.employeedatamvc.controller;

import com.innowise.employeedatamvc.exception.EmployeeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<String> handleEmployeeException(EmployeeException employeeException) {
        log.error("EmployeeException: {}", employeeException.getMessage(), employeeException);
        return ResponseEntity.badRequest().body(employeeException.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        log.error("Unexpected exception: {}", exception.getMessage(), exception);
        return ResponseEntity.internalServerError().body("Unexpected exception");
    }

}
