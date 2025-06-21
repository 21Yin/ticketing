package com.assessment.ticketing.roleManagement.repository;

import com.assessment.ticketing.global.document.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
