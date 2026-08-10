package com.example.library_api.repository;

import com.example.library_api.model.Loan;
import com.example.library_api.model.LoanStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    @EntityGraph(attributePaths = {"book", "book.author", "user"})
    List<Loan> findByUserUsername(String username);

    @Override
    @EntityGraph(attributePaths = {"book", "book.author", "user"})
    List<Loan> findAll();

    long countByUserUsernameAndStatus(String username, LoanStatus status);

    @EntityGraph(attributePaths = {"book", "book.author", "user"})
    List<Loan> findByStatusAndDueDateBefore(LoanStatus status, LocalDate date);
}
