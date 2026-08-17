import { Component, OnInit, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import {BookService} from '../services/book.service';

@Component({
  selector: 'app-book-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './book-form.html'
})
export class BookForm implements OnInit {
  private form = inject(FormBuilder);
  private bookService = inject(BookService);
  private route = inject(ActivatedRoute);
  private router = inject(Router);

  loading = signal(false);
  errorMessage = signal<string | null>(null);
  isEditMode = signal(false);
  private bookId: number | null = null;

  bookForm = this.form.group({
    title: ['', Validators.required],
    isbn: [''],
    publicationYear: [null as number | null],
    authorId: [null as number | null, Validators.required]
  });

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      this.isEditMode.set(true);
      this.bookId = Number(idParam);
      this.bookService.getBookById(this.bookId).subscribe({
        next: (book) => {
          this.bookForm.patchValue({
            title: book.title,
            isbn: book.isbn,
            publicationYear: book.publicationYear,
            authorId: book.author.id
          });
        },
        error: (err: HttpErrorResponse) => {
          this.errorMessage.set(err.error?.message ?? 'Livre introuvable.');
        }
      });
    }
  }

  onSubmit(): void {
    if (this.bookForm.invalid) return;

    this.loading.set(true);
    this.errorMessage.set(null);

    const request = {
      title: this.bookForm.value.title!,
      isbn: this.bookForm.value.isbn ?? undefined,
      publicationYear: this.bookForm.value.publicationYear ?? undefined,
      authorId: this.bookForm.value.authorId!
    };

    const action$ = this.isEditMode()
      ? this.bookService.updateBook(this.bookId!, request)
      : this.bookService.createBook(request);

    action$.subscribe({
      next: (book) => {
        this.loading.set(false);
        this.router.navigate(['/books', book.id]);
      },
      error: (err: HttpErrorResponse) => {
        this.loading.set(false);
        this.errorMessage.set(err.error?.message ?? 'Une erreur est survenue.');
      }
    });
  }
}
