package com.example.modeljson.error.notfound;

public class EnumeratedValueNotFound extends RuntimeException {

    public EnumeratedValueNotFound() {
    }

    public EnumeratedValueNotFound(String message) {
        super(message);
    }

    public EnumeratedValueNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public EnumeratedValueNotFound(Throwable cause) {
        super(cause);
    }

    public EnumeratedValueNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
