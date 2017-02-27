package com.talbot.customerList.core.services.exceptions;

public class CustomerEmailExistsException extends RuntimeException {
    public CustomerEmailExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerEmailExistsException(String message) {
        super(message);
    }

    public CustomerEmailExistsException() {
    }
}
