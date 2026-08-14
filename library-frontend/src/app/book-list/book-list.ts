import {Component, inject, OnInit} from '@angular/core';
import {RouterLink} from '@angular/router';
import {BookService} from '../services/BookService';
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

  books: Book[] = [];
  loading = true;

  ngOnInit(): void {
    this.bookService.getBooks().subscribe({
      next: (response) => {
        this.books = response.content;
        this.loading = false;
      },
      error: (err) => {
        console.log('Erreur lors du chargement des livres', err);

        this.loading = false;
      }
    });
  }
}
