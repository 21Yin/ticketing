package com.assessment.ticketing.global;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Builder
@Data
@AllArgsConstructor
public class GlobalResponse<T> {
    private T data;
    private String message;
    private String successCode;
    private OffsetDateTime timestamp;
}
