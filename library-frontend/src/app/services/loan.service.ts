import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Loan} from '../models/loan.model';

@Injectable({ providedIn: 'root' })
export class LoanService {
  private http = inject(HttpClient);
  private baseUrl = 'http://localhost:8080/loans';

  getMyLoans(): Observable<Loan[]> {
    return this.http.get<Loan[]>(`${this.baseUrl}/me`);
  }

  returnLoan(id: number): Observable<Loan> {
    return this.http.put<Loan>(`${this.baseUrl}/${id}/return`, {});
  }
}
