package com.innowise.employeedatamvc.exception;

import java.io.Serial;

public class EmployeeException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 2672724725973194488L;

    public EmployeeException(String message) {
        super(message);
    }
}
