package com.example.library_api.controller;

import com.example.library_api.dto.LoanResponse;
import com.example.library_api.mapper.LoanMapper;
import com.example.library_api.model.Loan;
import com.example.library_api.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;
    private final LoanMapper loanMapper;

    @GetMapping("/loans/me")
    public List<LoanResponse> getMyLoans(Authentication authentication) {
        return loanMapper.toResponseList(loanService.getMyLoans(authentication.getName()));
    }

    @GetMapping("/loans")
    public List<LoanResponse> getAllLoans() {
        return loanMapper.toResponseList(loanService.getAllLoans());
    }

    @PostMapping("/books/{id}/borrow")
    public ResponseEntity<LoanResponse> borrowBook(@PathVariable Long id, Authentication authentication) {
        Loan loan = loanService.borrowBook(id, authentication.getName());

        return ResponseEntity.status(HttpStatus.CREATED).body(loanMapper.toResponse(loan));
    }

    @PutMapping("/books/{id}/return")
    public LoanResponse returnBook(@PathVariable Long id, Authentication authentication) {
        boolean isAdmin = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN"));

        Loan loan = loanService.returnBook(id, authentication.getName(), isAdmin);

        return loanMapper.toResponse(loan);
    }
}