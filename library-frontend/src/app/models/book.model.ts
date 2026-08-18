import {Category} from './category.model';
import {Author} from './author.model';

export interface Book {
  id: number;
  title: string;
  isbn: string;
  publicationYear: number;
  available: boolean;
  author: Author;
  categories: Category[];
}
