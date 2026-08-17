import { Injectable, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private http = inject(HttpClient);
  token = signal<string | null>(localStorage.getItem('token'));

  login(username: string, password: string) {
    return this.http.post<{ token: string }>('http://localhost:8080/auth/login', { username, password }).pipe(
      tap((response) => {
        this.token.set(response.token);
        localStorage.setItem('token', response.token);
      })
    );
  }
}
