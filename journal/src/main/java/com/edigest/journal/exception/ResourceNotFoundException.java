package com.edigest.journal.exception;


public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String entity, String field, Object fieldValue) {
        super(String.format("%s not found with %s: %s", entity, field, fieldValue));
    }
}
