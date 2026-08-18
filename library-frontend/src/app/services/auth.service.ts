import {Injectable, inject, signal, computed} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable, tap} from 'rxjs';
import {Router} from '@angular/router';
import {AuthResponse} from '../models/auth-response.model';

const TOKEN_KEY = 'token';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private http = inject(HttpClient);
  private router = inject(Router);
  private baseUrl = 'http://localhost:8080/auth'

  private token = signal<string | null>(localStorage.getItem(TOKEN_KEY));

  isAuthenticated = computed(() => this.token() !== null);

  register(email: string, password: string): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.baseUrl}/register`, {email, password}).pipe(
      tap((response) => this.storeToken(response.token))
    );
  }

  login(email: string, password: string): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.baseUrl}/login`, {email, password}).pipe(
      tap((response) => this.storeToken(response.token))
    );
  }

  logout():void {
    localStorage.removeItem(TOKEN_KEY);

    this.token.set(null);
    this.router.navigate(['/login']);
  }

  getToken(): string | null {
    return this.token();
  }

  private storeToken(token: string): void {
    localStorage.setItem(TOKEN_KEY, token);

    this.token.set(token);
  }
}
