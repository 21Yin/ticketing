package com.assessment.ticketing.global.exception.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadCredentialsException extends RuntimeException {

    private String errorCode;

    public BadCredentialsException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}