import {Book} from './book.model';

export interface Loan {
  id: number;
  book: Book;
  email: string;
  loanDate: string;
  dueDate: string;
  returnedDate: string | null;
  status: 'BORROWED' | 'RETURNED'
}
