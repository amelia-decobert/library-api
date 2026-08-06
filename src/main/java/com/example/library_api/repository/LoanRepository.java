package com.example.library_api.repository;

import com.example.library_api.model.Loan;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    @EntityGraph(attributePaths = {"book", "book.author", "user"})
    List<Loan> findByUserUsername(String username);

    @Override
    @EntityGraph(attributePaths = {"book", "book.author", "user"})
    List<Loan> findAll();
}
