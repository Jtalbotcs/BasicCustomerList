package com.talbot.customerList.core.services.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerNotFoundException(String message) {
        super(message);
    }

    public CustomerNotFoundException() {
    }
}
