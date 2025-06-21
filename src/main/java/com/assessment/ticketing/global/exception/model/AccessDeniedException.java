package com.assessment.ticketing.global.exception.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessDeniedException extends RuntimeException {

    private String errorCode;

    public AccessDeniedException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
