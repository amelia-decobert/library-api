import {Category} from './category.model';

export interface Book {
  id: number;
  title: string;
  isbn: string;
  publicationYear: number;
  available: boolean;
  author: string;
  categories: Category[];
}
