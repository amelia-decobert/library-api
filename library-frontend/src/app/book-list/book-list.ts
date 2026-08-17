import {Component, inject, OnInit, signal} from '@angular/core';
import {RouterLink} from '@angular/router';
import {BookService} from '../services/book.service';
import {Book} from '../models/book.model';

@Component({
  selector: 'app-book-list',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './book-list.html',
  styleUrl: './book-list.css',
})
export class BookList implements OnInit {
  private bookService = inject(BookService);

  books = signal<Book[]>([]);
  loading = signal(true);

  ngOnInit(): void {
    this.bookService.getBooks().subscribe({
      next: (response) => {
        this.books.set(response.content);
        this.loading.set(false);
      },
      error: (err) => {
        console.error('Erreur lors du chargement des livres', err);

        this.loading.set(false);
      }
    });
  }
}
