import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PageResponse} from '../models/page-response.model';
import {Book} from '../models/book.model';

@Injectable({providedIn: 'root'})
export class BookService {
  private http = inject(HttpClient);
  private baseUrl: 'http://localhost:8080/books';

  getBooks(page: number = 0, size: number = 10): Observable<PageResponse<Book>> {
    return this.http.get<PageResponse<Book>>(`${this.baseUrl}?page=${page}&size=${size}`);
  }
  getBookById(id: number): Observable<Book> {
    return this.http.get<Book>(`${this.baseUrl}/${id}`);
  }
}
