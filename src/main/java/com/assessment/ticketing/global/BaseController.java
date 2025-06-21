package com.assessment.ticketing.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;

public class BaseController {

    protected <T> ResponseEntity<GlobalResponse<T>> createResponse(
            HttpStatus httpStatus, T data, String message
    ) {
        GlobalResponse<T> response = GlobalResponse.<T>builder()
                .data(data)
                .timestamp(OffsetDateTime.now())
                .message(message)
                .build();

        return new ResponseEntity<>(response, httpStatus);
    }
}
