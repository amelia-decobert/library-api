package com.example.library_api.service;

import com.example.library_api.exception.*;
import com.example.library_api.model.Book;
import com.example.library_api.model.Loan;
import com.example.library_api.model.LoanStatus;
import com.example.library_api.model.User;
import com.example.library_api.repository.BookRepository;
import com.example.library_api.repository.LoanRepository;
import com.example.library_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {
    private static final int LOAN_PERIOD_DAYS = 14;
    private static final int MAX_ACTIVE_LOANS = 5;

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Transactional
    public Loan borrowBook(Long bookId, String username) {
        long activeLoans = loanRepository.countByUserUsernameAndStatus(username, LoanStatus.BORROWED);
        if (activeLoans >= MAX_ACTIVE_LOANS) {
            throw new MaxLoansExceededException(username);
        }

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        if (!book.isAvailable()) {
            throw new BookNotAvailableException(bookId);
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));

        book.setAvailable(false);
        bookRepository.save(book);

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setUser(user);
        loan.setLoanDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusDays(LOAN_PERIOD_DAYS));
        loan.setStatus(LoanStatus.BORROWED);

        return loanRepository.save(loan);
    }

    @Transactional
    public Loan returnBook(Long loanId, String username) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException(loanId));

        if (!loan.getUser().getUsername().equals(username)) {
            throw new ForbiddenLoanAccessException();
        }

        loan.setReturnedDate(LocalDate.now());
        loan.setStatus(LoanStatus.RETURNED);

        Book book = loan.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        return loanRepository.save(loan);
    }

    public List<Loan> getMyLoans(String username) {
        return loanRepository.findByUserUsername(username);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public List<Loan> getOverdueLoans() {
        return loanRepository.findByStatusAndDueDateBefore(LoanStatus.BORROWED, LocalDate.now());
    }
}
