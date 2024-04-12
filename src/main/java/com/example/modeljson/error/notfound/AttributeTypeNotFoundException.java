package com.example.modeljson.error.notfound;

public class AttributeTypeNotFoundException extends RuntimeException {

    public AttributeTypeNotFoundException() {
    }

    public AttributeTypeNotFoundException(String message) {
        super(message);
    }

    public AttributeTypeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AttributeTypeNotFoundException(Throwable cause) {
        super(cause);
    }

    public AttributeTypeNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
