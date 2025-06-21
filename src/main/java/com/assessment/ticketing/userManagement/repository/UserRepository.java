package com.assessment.ticketing.userManagement.repository;

import com.assessment.ticketing.global.document.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
