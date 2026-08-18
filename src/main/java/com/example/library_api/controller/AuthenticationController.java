package com.example.library_api.controller;

import com.example.library_api.dto.LoginRequest;
import com.example.library_api.dto.AuthResponse;
import com.example.library_api.dto.RegisterRequest;
import com.example.library_api.security.JwtService;
import com.example.library_api.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthService authService;

    @PostMapping("/auth/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //    Document Swagger
    @Operation(
            summary = "Log In",
            description = "Authenticates a user using their username and password, "
                    + "and return a valid token for 24 hours, to be used in Authorization header "
                    + "of all subsequent requests."
    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login successful and token returned"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })

    //    Tell to Swagger this endpoint does not require authentication
    @SecurityRequirements

    @PostMapping("/auth/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
