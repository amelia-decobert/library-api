import { Routes } from '@angular/router';
import { BookDetail } from './book-detail/book-detail';
import { Login } from './login/login';
import {BookList} from './book-list/book-list';
import {BookForm} from './book-form/book-form';
import {authGuard} from './auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: 'books', pathMatch: 'full' },
  { path: 'auth/login', component: Login },
  { path: 'books', component: BookList, canActivate: [authGuard] },
  { path: 'books/new', component: BookForm, canActivate: [authGuard] },
  { path: 'books/:id/edit', component: BookForm, canActivate: [authGuard] },
  { path: 'books/:id', component: BookDetail, canActivate: [authGuard] }
];
