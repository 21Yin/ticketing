package com.assessment.ticketing.authManagement.service;

import com.assessment.ticketing.authManagement.domain.request.AuthRequest;
import com.assessment.ticketing.authManagement.domain.response.AuthResponse;

public interface AuthService {
    String register(AuthRequest request);
    AuthResponse login(AuthRequest request);
    void logout(String tokenHeader);
}
