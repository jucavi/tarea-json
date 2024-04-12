package com.example.modeljson.error;

public class AttributeTypeValueNotFoundException extends RuntimeException {

    public AttributeTypeValueNotFoundException() {
    }

    public AttributeTypeValueNotFoundException(String message) {
        super(message);
    }

    public AttributeTypeValueNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AttributeTypeValueNotFoundException(Throwable cause) {
        super(cause);
    }

    public AttributeTypeValueNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
