package com.example.library_api.repository;

import com.example.library_api.model.Loan;
import com.example.library_api.model.LoanStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    @EntityGraph(attributePaths = {"book", "book.author", "book.categories", "user"})
    List<Loan> findByUserUsername(String username);

    @Override
    @EntityGraph(attributePaths = {"book", "book.author", "book.categories", "user"})
    List<Loan> findAll();

    @EntityGraph(attributePaths = {"book", "book.author", "book.categories", "user"})
    Optional<Loan> findById(Long id);

    long countByUserUsernameAndStatus(String username, LoanStatus status);

    @EntityGraph(attributePaths = {"book", "book.author", "user"})
    List<Loan> findByStatusAndDueDateBefore(LoanStatus status, LocalDate date);
}
