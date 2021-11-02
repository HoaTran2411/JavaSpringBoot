package com.example.employee_management.exception;

public class StorageException extends RuntimeException {
    
    private static final long serialVersionUID = 123365963802085889L;

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}