package com.planner.TaskPlanner.Payload;

import lombok.Getter;

import java.util.Date;

@Getter
public class ErrorResponse {

    private Date timestamp;
    private String message;
    private String details;

    public ErrorResponse(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
