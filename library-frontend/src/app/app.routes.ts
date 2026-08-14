import {Routes} from '@angular/router';
import {BookDetail} from './book-detail/book-detail';
import {BookList} from './book-list/book-list';

export const routes: Routes = [
  {path: '', redirectTo: 'books', pathMatch: 'full'},
  {path: 'books', component: BookList},
  {path: 'books/:id', component: BookDetail}
];
