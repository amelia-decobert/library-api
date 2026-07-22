package com.example.library_api.config;

import com.example.library_api.model.User;
import com.example.library_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
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
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123")); // Hash password with BCrypt
        admin.setRole("ROLE_ADMIN");
        userRepository.save(admin);

        User jim = new User();
        jim.setUsername("jim");
        jim.setPassword(passwordEncoder.encode("jim123"));
        jim.setRole("ROLE_USER");
        userRepository.save(jim);

        User lilia = new User();
        lilia.setUsername("lilia");
        lilia.setPassword(passwordEncoder.encode("lilia123"));
        lilia.setRole("ROLE_USER");
        userRepository.save(lilia);

        User paul = new User();
        paul.setUsername("paul");
        paul.setPassword(passwordEncoder.encode("paul123"));
        paul.setRole("ROLE_USER");
        userRepository.save(paul);

        User remy = new User();
        remy.setUsername("remy");
        remy.setPassword(passwordEncoder.encode("remy123"));
        remy.setRole("ROLE_ADMIN");
        userRepository.save(remy);
    }
}
