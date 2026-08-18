import {Component, inject, OnInit, signal} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {BookService} from '../services/book.service';
import {Book} from '../models/book.model';

@Component({
  selector: 'app-book-detail',
  standalone: true,
  templateUrl: './book-detail.html',
  styleUrl: './book-detail.css',
})
export class BookDetail implements OnInit {
  private route = inject(ActivatedRoute);
  private bookService = inject(BookService);

  book = signal<Book | null>(null);

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    this.bookService.getBookById(id).subscribe({
      next: (book) => this.book.set(book),
      error: (err) => console.error('Erreur lors du chargement du livre', err)
    });
  }
}
