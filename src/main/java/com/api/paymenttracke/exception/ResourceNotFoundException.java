package com.api.paymenttracke.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Class<?> resourceClass, Long resourceId) {
        super(buildMessage(resourceClass.getSimpleName(), resourceId));
    }

    private static String buildMessage(String resourceName, Long resourceId) {
        return String.format("%s not found with id: %d", resourceName, resourceId);
    }
}
