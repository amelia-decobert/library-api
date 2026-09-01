import { Component, inject, OnInit, signal } from '@angular/core';
import { DatePipe } from '@angular/common';
import { RouterLink } from '@angular/router';
import { LoanService } from '../services/loan.service';
import { Loan } from '../models/loan.model';

@Component({
  selector: 'app-loans',
  standalone: true,
  imports: [DatePipe, RouterLink],
  templateUrl: './loans.html',
  styleUrl: './loans.css',
})
export class Loans implements OnInit {
  private loanService = inject(LoanService);

  loans = signal<Loan[]>([]);
  loading = signal(true);
  errorMessage = signal<string | null>(null);

  ngOnInit(): void {
    this.loadLoans();
  }

  loadLoans(): void {
    this.loading.set(true);

    this.loanService.getLoans().subscribe({
      next: loans => {
        this.loans.set(loans);

        this.loading.set(false);
      },

      error: err => {
        console.error(err);

        this.errorMessage.set("Impossible de charger les emprunts.")

        this.loading.set(false);
      }
    });
  }
}
