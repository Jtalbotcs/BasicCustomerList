package com.talbot.customerList.core.services.exceptions;

public class CustomerTelephoneExistsException extends RuntimeException {
    public CustomerTelephoneExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerTelephoneExistsException(String message) {
        super(message);
    }

    public CustomerTelephoneExistsException() {
    }
}
