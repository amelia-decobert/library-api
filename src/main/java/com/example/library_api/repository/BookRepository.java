package com.example.library_api.repository;

import com.example.library_api.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Override
    @EntityGraph(attributePaths = "author", "categories")
    Page<Book> findAll(Pageable pageable);

    @EntityGraph(attributePaths = "author", "categories")
    List<Book> findByAuthor(String author);

    @EntityGraph(attributePaths = "author", "categories")
    List<Book> findByAuthorId(Long authorId);

    @EntityGraph(attributePaths = "author", "categories")
    List<Book> findByTitleContainingIgnoreCase(String title);

    @EntityGraph(attributePaths = "author", "categories")
    List<Book> findByPublicationYearGreaterThan(Integer year);

    @EntityGraph(attributePaths = "author", "categories")
    @Query("SELECT b FROM Book b WHERE " +
            "(:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', CAST(:title AS string), '%'))) AND " +
            "(:author IS NULL OR LOWER(b.author.name) LIKE LOWER(CONCAT('%', CAST(:author AS string), '%'))) AND " +
            "(:year IS NULL OR b.publicationYear = :year)")
    List<Book> search(@Param("title") String title,
                      @Param("author") String author,
                      @Param("year") Integer year);

    @Query("SELECT b FROM Book b WHERE b.publicationYear > :year ORDER BY b.publicationYear DESC")
    List<Book> findBooksPublishedAfter(@Param("year") Integer year);
}