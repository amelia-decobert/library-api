import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
import {Book} from '../models/book.model';
import {PageResponse} from '../models/page-response.model';

export interface BookRequest {
  title: string;
  isbn?: string;
  publicationYear?: number;
  authorId: number;
  categoryIds?: number[];
}

@Injectable({ providedIn: 'root' })
export class BookService {
  private http = inject(HttpClient);
  private baseUrl = 'http://localhost:8080/books';

  getBooks(page: number = 0, size: number = 10, sort?: string, search?: string): Observable<PageResponse<Book>> {
    let params = new HttpParams().set('page', page).set('size', size);
    if (sort) params = params.set('sort', sort);
    if (search) params = params.set('title', search);

    const url = search ? `${this.baseUrl}/search` : this.baseUrl;
    return this.http.get<PageResponse<Book>>(url, { params });
  }

  getBookById(id: number): Observable<Book> {
    return this.http.get<Book>(`${this.baseUrl}/${id}`);
  }

  createBook(request: BookRequest): Observable<Book> {
    return this.http.post<Book>(this.baseUrl, request);
  }

  updateBook(id: number, request: BookRequest): Observable<Book> {
    return this.http.put<Book>(`${this.baseUrl}/${id}`, request);
  }

  deleteBook(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
