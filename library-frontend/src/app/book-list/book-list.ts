import { Component, OnInit, inject, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Book } from '../models/book.model';
import {BookService} from '../services/book.service';
import {AuthService} from '../services/auth.service';

@Component({
  selector: 'app-book-list',
  standalone: true,
  imports: [RouterLink, FormsModule],
  templateUrl: './book-list.html'
})
export class BookList implements OnInit {
  private bookService = inject(BookService);
  public authService = inject(AuthService);

  books = signal<Book[]>([]);
  loading = signal(true);
  errorMessage = signal<string | null>(null);

  page = signal(0);
  totalPages = signal(0);
  searchTerm = '';
  sort = 'title';

  ngOnInit(): void {
    this.loadBooks();
  }

  loadBooks(): void {
    this.loading.set(true);
    this.bookService.getBooks(this.page(), 10, this.sort, this.searchTerm || undefined).subscribe({
      next: (response) => {
        this.books.set(response.content);
        this.totalPages.set(response.totalPages);
        this.loading.set(false);
      },
      error: () => {
        this.errorMessage.set('Impossible de charger les livres.');
        this.loading.set(false);
      }
    });
  }

  onSearch(): void {
    this.page.set(0);
    this.loadBooks();
  }

  nextPage(): void {
    this.page.set(this.page() + 1);
    this.loadBooks();
  }

  previousPage(): void {
    this.page.set(this.page() - 1);
    this.loadBooks();
  }

  deleteBook(id: number): void {
    if (!confirm('Supprimer ce livre ?')) return;

    this.bookService.deleteBook(id).subscribe({
      next: () => this.loadBooks(),
      error: () => this.errorMessage.set('Suppression impossible.')
    });
  }
}
