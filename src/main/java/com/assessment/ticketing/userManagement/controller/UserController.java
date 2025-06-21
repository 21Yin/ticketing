package com.assessment.ticketing.userManagement.controller;

import com.assessment.ticketing.authManagement.service.AuthService;
import com.assessment.ticketing.global.BaseController;
import com.assessment.ticketing.global.GlobalResponse;
import com.assessment.ticketing.global.constants.SuccessCodeConstants;
import com.assessment.ticketing.global.utils.MessageBundle;
import com.assessment.ticketing.userManagement.domain.response.UserResponse;
import com.assessment.ticketing.userManagement.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {
    private final MessageBundle messageBundle;

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GlobalResponse<Page<UserResponse>>> getAllUsers(
            @RequestParam(required = false) String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("username").ascending());
        Page<UserResponse> userPage = userService.getAllUsers(query, pageable);

        return createResponse(
                HttpStatus.OK,
                userPage,
                messageBundle.getMessage(SuccessCodeConstants.SUC_COM001)
        );
    }

}
