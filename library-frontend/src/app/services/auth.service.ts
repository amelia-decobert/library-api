import { Injectable, inject, signal, computed } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { Router } from '@angular/router';
import { AuthResponse } from '../models/auth-response.model';

const TOKEN_KEY = 'token';
const ROLE_KEY = 'role';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private http = inject(HttpClient);
  private router = inject(Router);

  private baseUrl = 'http://localhost:8080/auth';

  private token = signal<string | null>(localStorage.getItem(TOKEN_KEY));
  private role = signal<string | null>(localStorage.getItem(ROLE_KEY));

  isAuthenticated = computed(() => this.token() !== null);
  isAdmin = computed(() => this.role() === 'ADMIN');

  register(email: string, password: string): Observable<AuthResponse> {
    return this.http
      .post<AuthResponse>(`${this.baseUrl}/register`, { email, password })
      .pipe(tap(response => this.storeAuth(response)));
  }

  login(email: string, password: string): Observable<AuthResponse> {
    return this.http
      .post<AuthResponse>(`${this.baseUrl}/login`, { email, password })
      .pipe(tap(response => this.storeAuth(response)));
  }

  logout(): void {
    localStorage.removeItem(TOKEN_KEY);
    localStorage.removeItem(ROLE_KEY);

    this.token.set(null);
    this.role.set(null);

    this.router.navigate(['auth/login']);
  }

  getToken(): string | null {
    return this.token();
  }

  private storeAuth(response: AuthResponse): void {
    localStorage.setItem(TOKEN_KEY, response.token);
    localStorage.setItem(ROLE_KEY, response.role);

    this.token.set(response.token);
    this.role.set(response.role);
  }
}
