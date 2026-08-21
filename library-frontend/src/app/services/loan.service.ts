import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Loan } from '../models/loan.model';

@Injectable({
  providedIn: 'root'
})
export class LoanService {
  private http = inject(HttpClient);

  private baseUrl = 'http://localhost:8080';

  getMyLoans(): Observable<Loan[]> {
    return this.http.get<Loan[]>(`${this.baseUrl}/loans/me`);
  }

  getLoans(): Observable<Loan[]> {
    return this.http.get<Loan[]>(`${this.baseUrl}/loans`);
  }

  borrowBook(bookId: number): Observable<Loan> {
    return this.http.post<Loan>(`${this.baseUrl}/books/${bookId}/borrow`, {});
  }

  returnLoan(loanId: number): Observable<Loan> {
    return this.http.put<Loan>(`${this.baseUrl}/loans/${loanId}/return`, {});
  }
}
