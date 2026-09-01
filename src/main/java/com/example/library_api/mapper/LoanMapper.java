package com.example.library_api.mapper;

import com.example.library_api.dto.LoanResponse;
import com.example.library_api.model.Loan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LoanMapper {
    private final BookMapper bookMapper;

    public LoanResponse toResponse(Loan loan) {
        return new LoanResponse(
                loan.getId(),
                bookMapper.toResponse(loan.getBook()),
                loan.getUser().getEmail(),
                loan.getLoanDate(),
                loan.getDueDate(),
                loan.getReturnedDate(),
                loan.getStatus().name()
        );
    }
    public List<LoanResponse> toResponseList(List<Loan> loans) {
        return loans.stream().map(this::toResponse).toList();
    }
}
