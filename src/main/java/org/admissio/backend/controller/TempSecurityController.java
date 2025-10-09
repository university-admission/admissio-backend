package org.admissio.backend.controller;

import lombok.RequiredArgsConstructor;
import org.admissio.backend.entity.User;
import org.admissio.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
class TempSecurityController implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));

            userRepository.save(admin);
            System.out.println(">>> Admin user created successfully!");
        }
    }
}
