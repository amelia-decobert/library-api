package com.example.library_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    // Declare security rules
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/health").permitAll() // Allow any request to this endpoint without authentication
                .requestMatchers(HttpMethod.GET, "/books", "/books/**").permitAll() // Allow read-only to these endpoints without authentication
                .anyRequest().authenticated()); // Any other request requires valid authentication

        // Activate a feature with its default configuration
        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    // Transform a password
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
