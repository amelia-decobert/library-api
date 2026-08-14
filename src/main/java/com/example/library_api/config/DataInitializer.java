package com.example.library_api.config;

import com.example.library_api.model.Author;
import com.example.library_api.model.Book;
import com.example.library_api.model.Category;
import com.example.library_api.repository.AuthorRepository;
import com.example.library_api.repository.BookRepository;
import com.example.library_api.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public void run(String... args) {
        if (bookRepository.count() > 0) {
            return;
        }

        List<Author> authors = authorRepository.saveAll(List.of(
                newAuthor("Robert C. Martin"),
                newAuthor("Joshua Bloch"),
                newAuthor("Erich Gamma"),
                newAuthor("Martin Fowler"),
                newAuthor("Kent Beck")
        ));

        List<Category> categories = categoryRepository.saveAll(List.of(
                newCategory("Informatique"),
                newCategory("Développement Logiciel"),
                newCategory("Architecture"),
                newCategory("Bonnes Pratiques")
        ));

        for (int i = 1; i <= 20; i++) {
            Book book = new Book();
            book.setTitle("Livre Technique n°" + i);
            book.setAuthor(authors.get(i % authors.size()));
            book.setCategories(Set.of(
                    categories.get(i % categories.size()),
                    categories.get((i + 1) % categories.size())
            ));
            book.setIsbn("978-0-000-000" + String.format("%02d", i));
            book.setPublicationYear(2000 + (i % 24));
            bookRepository.save(book);
        }

        System.out.println("Jeu de données initial créé : "
        + authors.size()
        + " auteurs, "
        + categories.size()
        + " catégories, 20 livres.");
    }

    private Author newAuthor(String name) {
        Author author = new Author();
        author.setName(name);
        return author;
    }

    private Category newCategory(String name) {
        Category category = new Category();
        category.setName(name);
        return category;
    }
}
