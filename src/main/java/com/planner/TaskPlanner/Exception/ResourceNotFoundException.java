package com.planner.TaskPlanner.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND) // This annotation is used to define the response status of the request.
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String resourceName, Object fieldValue, Long fieldName) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldValue = fieldValue;
        this.fieldName = fieldName;
    }

    private String resourceName;
    private Long fieldName;
    private Object fieldValue;
}
