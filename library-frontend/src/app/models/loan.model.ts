export interface Loan {
  id: number;
  book: {
    id: number;
    title: string
  };
  loanDate: string;
  dueDate: string;
  returnedDate: string | null;
  status: 'BORROWED' | 'RETURNED'
}
