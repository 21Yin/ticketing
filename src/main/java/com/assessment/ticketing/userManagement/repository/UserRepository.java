package com.assessment.ticketing.userManagement.repository;

import com.assessment.ticketing.global.document.entities.User;
import com.assessment.ticketing.userManagement.domain.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query(value = "SELECT * FROM ticket_management.users u WHERE :query IS NULL OR LOWER(u.username) LIKE %:query%", nativeQuery = true)
    Page<User> searchByUsername(@Param("query") String query, Pageable pageable);


}
