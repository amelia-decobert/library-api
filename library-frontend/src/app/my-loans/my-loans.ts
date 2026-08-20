import {Component, inject, OnInit, signal} from '@angular/core';
import {DatePipe} from '@angular/common';
import {LoanService} from '../services/loan.service';
import {Loan} from '../models/loan.model';

@Component({
  selector: 'app-my-loans',
  standalone: true,
  imports: [DatePipe],
  templateUrl: './my-loans.html',
  styleUrl: './my-loans.css',
})
export class MyLoans implements OnInit {
 private loanService = inject(LoanService);

  loans = signal<Loan[]>([]);
  loading = signal(true);
  errorMessage = signal<string | null>(null);

  ngOnInit(): void {
    this.loadLoans();
  }

  loadLoans(): void {
    this.loading.set(true);
    this.loanService.getMyLoans().subscribe({
      next: (loans) => {
        this.loans.set(loans);
        this.loading.set(false);
      },
      error: () => {
        this.errorMessage.set("Impossible de charger vos emprunts.");
        this.loading.set(false);
      }
    });
  }

  returnBook(loanId: number): void {
    this.loanService.returnLoan(loanId).subscribe({
      next: () => this.loadLoans(),   // recharge la liste après le retour
      error: () => this.errorMessage.set("Le retour du livre a échoué.")
    });
  }
}
