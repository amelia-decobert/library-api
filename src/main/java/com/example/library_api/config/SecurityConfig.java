package com.example.library_api.config;

import com.example.library_api.security.SecurityExceptionHandler;
import jakarta.servlet.http.HttpServletResponse;
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
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            SecurityExceptionHandler securityExceptionHandler) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Temporarily disable CSRF to allow POST /books with Basic Auth
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/health").permitAll() // Allow any request to this endpoint without authentication
                        .requestMatchers(HttpMethod.GET, "/books", "/books/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/books").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/books/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/books/**").hasRole("ADMIN")
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated()); // Any other request requires valid authentication

        // Activate a feature with its default configuration
        http.httpBasic(Customizer.withDefaults());

        // HttpSecurity config section dedicated to handling security errors
        http.exceptionHandling(exception -> exception
                .authenticationEntryPoint(securityExceptionHandler)
                .accessDeniedHandler(securityExceptionHandler));

        return http.build();
    }

    @Bean
    // Transform a password
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
