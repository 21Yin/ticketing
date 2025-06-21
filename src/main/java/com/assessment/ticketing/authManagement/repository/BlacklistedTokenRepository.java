package com.assessment.ticketing.authManagement.repository;

import com.assessment.ticketing.global.document.entities.BlacklistedToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface BlacklistedTokenRepository extends JpaRepository<BlacklistedToken, Long> {
    boolean existsByToken(String token);
    void deleteAllByBlacklistedAtBefore(LocalDateTime cutoff);
}
