package com.assessment.ticketing.global.exception.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketCreationException extends RuntimeException {
    private final String errorCode;

    public TicketCreationException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
