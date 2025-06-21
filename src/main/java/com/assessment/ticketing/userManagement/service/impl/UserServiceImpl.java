package com.assessment.ticketing.userManagement.service.impl;

import com.assessment.ticketing.global.document.entities.Role;
import com.assessment.ticketing.userManagement.domain.response.UserResponse;
import com.assessment.ticketing.userManagement.repository.UserRepository;
import com.assessment.ticketing.userManagement.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public Page<UserResponse> getAllUsers(String query, Pageable pageable) {
        String normalizedQuery = (query == null || query.trim().isEmpty()) ? null : query.trim();


        return userRepository.searchByUsername(query, pageable)
                    .map(user -> new UserResponse(
                            user.getId(),
                            user.getUsername(),
                            user.getRoles().stream()
                                    .map(Role::getName)
                                    .collect(Collectors.toSet())
                    ));

    }
}
