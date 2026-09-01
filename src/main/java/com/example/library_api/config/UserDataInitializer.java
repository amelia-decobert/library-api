package com.example.library_api.config;

import com.example.library_api.model.Role;
import com.example.library_api.model.User;
import com.example.library_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class UserDataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Avoid recreate accounts each time the application is restarted
        if (userRepository.count() > 0) {
            return;
        }

        User admin = new User();
        admin.setEmail("admin@library.com");
        admin.setPassword(passwordEncoder.encode("admin123")); // Hash password with BCrypt
        admin.setRole(Role.ADMIN);
        userRepository.save(admin);

        User jim = new User();
        jim.setEmail("jimkol@library.com");
        jim.setPassword(passwordEncoder.encode("jimkol123"));
        jim.setRole(Role.USER);
        userRepository.save(jim);
    }
}
