package com.assessment.ticketing.authManagement.service;

import com.assessment.ticketing.authManagement.repository.BlacklistedTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TokenCleanupService {

    private final BlacklistedTokenRepository blacklistedTokenRepository;

    @Scheduled(cron = "0 0 2 * * *") // Runs every day at 2 AM
    public void cleanOldTokens() {
        LocalDateTime cutoff = LocalDateTime.now().minusDays(2);
        blacklistedTokenRepository.deleteAllByBlacklistedAtBefore(cutoff);
    }
}
