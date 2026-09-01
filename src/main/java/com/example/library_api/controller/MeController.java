package com.example.library_api.controller;

import com.example.library_api.model.User;
import com.example.library_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MeController {
    private final UserRepository userRepository;

    @GetMapping("/me")
    public Map<String, String> me(Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return Map.of(
                "email", user.getEmail(),
                "role", user.getRole().name()
        );
    }
}
