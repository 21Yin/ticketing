package com.assessment.ticketing.userManagement.domain.request;

import lombok.Data;

@Data
public class UserRequest {
    private int page = 0;
    private int size = 10;
}
