package com.assessment.ticketing.authManagement.controller;

import com.assessment.ticketing.authManagement.domain.request.AuthRequest;
import com.assessment.ticketing.authManagement.domain.response.AuthResponse;
import com.assessment.ticketing.authManagement.service.AuthService;
import com.assessment.ticketing.global.BaseController;
import com.assessment.ticketing.global.GlobalResponse;
import com.assessment.ticketing.global.constants.SuccessCodeConstants;
import com.assessment.ticketing.global.utils.MessageBundle;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController extends BaseController {

    private final AuthService authService;
    private final MessageBundle messageBundle;

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GlobalResponse<String>> register(@RequestBody AuthRequest request) {
        return createResponse(HttpStatus.CREATED, authService.register(request), messageBundle.getMessage(SuccessCodeConstants.SUC_COM001));
    }

    @PostMapping("/login")
    public ResponseEntity<GlobalResponse<AuthResponse>> login(@RequestBody AuthRequest request) {
        return createResponse(HttpStatus.OK, authService.login(request), messageBundle.getMessage(SuccessCodeConstants.SUC_COM001));
    }
}
