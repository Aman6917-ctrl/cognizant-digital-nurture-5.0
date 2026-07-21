package com.cognizant.springlearn.web;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

public class ApiErrorResponse {

    private Instant timestamp;
    private int status;
    private String message;
    private Map<String, String> errors = new LinkedHashMap<>();

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
