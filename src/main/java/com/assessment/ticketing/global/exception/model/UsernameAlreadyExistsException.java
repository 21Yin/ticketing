package com.assessment.ticketing.global.exception.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsernameAlreadyExistsException  extends RuntimeException {

    private String errorCode;

    public UsernameAlreadyExistsException(String errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }
}
