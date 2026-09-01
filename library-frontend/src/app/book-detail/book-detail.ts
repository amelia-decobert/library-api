import { Component, inject, OnInit, signal } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { BookService } from '../services/book.service';
import { LoanService } from '../services/loan.service';
import { Book } from '../models/book.model';

@Component({
  selector: 'app-book-detail',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './book-detail.html',
  styleUrl: './book-detail.css',
})
export class BookDetail implements OnInit {
  private route = inject(ActivatedRoute);
  private bookService = inject(BookService);
  private loanService = inject(LoanService);

  book = signal<Book | null>(null);
  borrowing = signal(false);
  successMessage = signal<string | null>(null);
  errorMessage = signal<string | null>(null);

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    this.loadBook(id);
  }

  private loadBook(id: number): void {
    this.bookService.getBookById(id).subscribe({
      next: book => this.book.set(book),

      error: err => {
        console.error(err);

        this.errorMessage.set("Impossible de charger le livre.");
      }
    });
  }

  borrowBook(): void {
    const book = this.book();

    if (!book || !book.available) {
      return;
    }

    this.borrowing.set(true);
    this.successMessage.set(null);
    this.errorMessage.set(null);

    this.loanService.borrowBook(book.id).subscribe({
      next: () => {
        this.borrowing.set(false);

        this.successMessage.set("Livre emprunté avec succès.");

        this.loadBook(book.id);
      },

      error: err => {
        console.error(err);

        this.borrowing.set(false);

        this.errorMessage.set("Impossible d'emprunter ce livre.");
      }
    });
  }
}
