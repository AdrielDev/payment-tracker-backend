package com.api.paymenttracke.exception.model;

public class ResponseMessageError {
    private final int status;
    private final String message;
    private final long timestamp;

    public ResponseMessageError(int status, String message, long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
