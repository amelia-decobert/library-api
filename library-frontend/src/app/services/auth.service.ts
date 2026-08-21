import {Injectable, inject, signal, computed} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, tap} from 'rxjs';
import {Router} from '@angular/router';
import {AuthResponse} from '../models/auth-response.model';
import {UserProfile} from '../models/user-profile.model';

const TOKEN_KEY = 'token';

@Injectable({providedIn: 'root'})
export class AuthService {
  private http = inject(HttpClient);
  private router = inject(Router);
  private baseUrl = 'http://localhost:8080/auth';
  private apiUrl = 'http://localhost:8080';

  private token = signal<string | null>(localStorage.getItem(TOKEN_KEY));

  private role = signal<'USER' | 'ADMIN' | null>(null);

  isAuthenticated = computed(() => this.token() !== null);

  isAdmin = computed(() => this.role() === 'ADMIN');

  register(email: string, password: string): Observable<AuthResponse> {
    return this.http
      .post<AuthResponse>(`${this.baseUrl}/register`, {email, password})
      .pipe(
        tap((response) => {
          this.storeToken(response.token);
          this.role.set(response.role as 'USER' | 'ADMIN');
        })
      );
  }

  login(email: string, password: string): Observable<AuthResponse> {
    return this.http
      .post<AuthResponse>(`${this.baseUrl}/login`, {email, password})
      .pipe(
        tap((response) => {
          this.storeToken(response.token);
          this.role.set(response.role as 'USER' | 'ADMIN');
        })
      );
  }

  loadProfile(): void {
    if (!this.token()) {
      return;
    }

    this.http
      .get<UserProfile>(`${this.apiUrl}/me`)
      .subscribe({
        next: (profile) => {
          this.role.set(profile.role);
        },
        error: () => {
          this.logout();
        }
      });
  }

  logout(): void {
    localStorage.removeItem(TOKEN_KEY);

    this.token.set(null);
    this.role.set(null);
    this.router.navigate(['auth/login']);
  }

  getToken(): string | null {
    return this.token();
  }

  private storeToken(token: string): void {
    localStorage.setItem(TOKEN_KEY, token);

    this.token.set(token);
  }
}
