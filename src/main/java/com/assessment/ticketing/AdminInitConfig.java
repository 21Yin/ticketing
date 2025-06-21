//package com.assessment.ticketing;
//
//import com.assessment.ticketing.global.document.entities.Role;
//import com.assessment.ticketing.global.document.entities.User;
//import com.assessment.ticketing.roleManagement.repository.RoleRepository;
//import com.assessment.ticketing.userManagement.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Collections;
//
//@Configuration
//@RequiredArgsConstructor
//public class AdminInitConfig {
//
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Bean
//    public CommandLineRunner initAdminAccount() {
//        return args -> {
//            String adminUsername = "admin";
//            String adminPassword = "admin123";
//            String adminRoleName = "ROLE_ADMIN";
//
//            if (userRepository.findByUsername(adminUsername).isEmpty()) {
//                Role adminRole = roleRepository.findByName(adminRoleName)
//                        .orElseGet(() -> roleRepository.save(Role.builder().name(adminRoleName).build()));
//
//                User adminUser = User.builder()
//                        .username(adminUsername)
//                        .password(passwordEncoder.encode(adminPassword))
//                        .roles(Collections.singleton(adminRole))
//                        .build();
//
//                userRepository.save(adminUser);
//                System.out.println("✅ Default admin account created.");
//            } else {
//                System.out.println("ℹ️ Admin user already exists.");
//            }
//        };
//    }
//}
