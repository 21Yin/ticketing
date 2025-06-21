package com.assessment.ticketing.authManagement.service.impl;

import com.assessment.ticketing.authManagement.domain.request.AuthRequest;
import com.assessment.ticketing.authManagement.domain.response.AuthResponse;
import com.assessment.ticketing.authManagement.repository.BlacklistedTokenRepository;
import com.assessment.ticketing.authManagement.service.AuthService;
import com.assessment.ticketing.global.constants.ErrorCodeConstants;
import com.assessment.ticketing.global.document.entities.BlacklistedToken;
import com.assessment.ticketing.global.document.entities.Role;
import com.assessment.ticketing.global.document.entities.User;
import com.assessment.ticketing.global.exception.model.AccessDeniedException;
import com.assessment.ticketing.global.exception.model.BadCredentialsException;
import com.assessment.ticketing.global.exception.model.UsernameAlreadyExistsException;
import com.assessment.ticketing.global.utils.MessageBundle;
import com.assessment.ticketing.roleManagement.repository.RoleRepository;
import com.assessment.ticketing.security.JwtUtil;
import com.assessment.ticketing.userManagement.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;

@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final MessageBundle messageBundle;
    private final BlacklistedTokenRepository blacklistedTokenRepository;

    @Override
    public String register(AuthRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getAuthorities().stream()
                .noneMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            throw new AccessDeniedException(
                    ErrorCodeConstants.ERR_COM002,
                    messageBundle.getMessage(ErrorCodeConstants.ERR_COM002));
        }

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException(
                    ErrorCodeConstants.ERR_COM002,
                    messageBundle.getMessage(ErrorCodeConstants.ERR_ACC001)
            );
        }

        Role roleEmployee = roleRepository.findByName("ROLE_EMPLOYEE")
                .orElseGet(() -> roleRepository.save(Role.builder().name("ROLE_EMPLOYEE").build()));

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Collections.singleton(roleEmployee))
                .build();

        userRepository.save(user);
        return "User registered successfully";
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword()
                    )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtUtil.generateToken(userDetails.getUsername());

            return AuthResponse.of(token, userDetails.getUsername());

        } catch (AuthenticationException ex) {
            throw new BadCredentialsException(
                    ErrorCodeConstants.ERR_AUTH001,
                    messageBundle.getMessage(ErrorCodeConstants.ERR_AUTH001)
            );
        }
    }

    @Override
    public void logout(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new BadCredentialsException(ErrorCodeConstants.ERR_TKT001,
                    messageBundle.getMessage(ErrorCodeConstants.ERR_TKT001));
        }

        String token = authHeader.substring(7);

        try {
            if (jwtUtil.isTokenExpired(token)) {
                throw new BadCredentialsException(ErrorCodeConstants.ERR_TKT002,
                        messageBundle.getMessage(ErrorCodeConstants.ERR_TKT002));
            }
        } catch (ExpiredJwtException ex) {
            throw new BadCredentialsException(ErrorCodeConstants.ERR_TKT002,
                    messageBundle.getMessage(ErrorCodeConstants.ERR_TKT002));
        }
        catch (Exception ex) {
            throw new BadCredentialsException(ErrorCodeConstants.ERR_JWT003,
                    messageBundle.getMessage(ErrorCodeConstants.ERR_JWT003));
        }

        if (blacklistedTokenRepository.existsByToken(token)) {
            throw new BadCredentialsException(ErrorCodeConstants.ERR_TKT003,
                    messageBundle.getMessage(ErrorCodeConstants.ERR_TKT003));
        }

        blacklistedTokenRepository.save(
                BlacklistedToken.builder()
                        .token(token)
                        .blacklistedAt(new Date())
                        .build()
        );
    }



}
