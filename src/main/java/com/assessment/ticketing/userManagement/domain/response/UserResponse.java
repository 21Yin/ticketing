package com.assessment.ticketing.userManagement.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserResponse {
    private int id;
    private String username;
    private Set<String> roles;
}
