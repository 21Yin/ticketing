package com.assessment.ticketing.userManagement.service;

import com.assessment.ticketing.userManagement.domain.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserResponse> getAllUsers(Pageable pageable);
}
