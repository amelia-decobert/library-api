export interface LoanBook {
  id: number;
  title: string
}

export interface Loan {
  id: number;
  book: LoanBook;
  loanDate: string;
  dueDate: string;
  returnedDate: string | null;
  status: 'BORROWED' | 'RETURNED'
}
