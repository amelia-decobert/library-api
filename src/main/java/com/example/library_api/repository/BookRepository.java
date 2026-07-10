package com.example.library_api.repository;

import com.example.library_api.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(String author);

    List<Book> findByPublicationYearGreaterThan(Integer year);

    @Query("SELECT b FROM Book b WHERE " +
            "(:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', CAST(:title AS string), '%'))) AND " +
            "(:author IS NULL OR LOWER(b.author) LIKE LOWER(CONCAT('%', CAST(:author AS string), '%'))) AND " +
            "(:year IS NULL OR b.publicationYear = :year)")
    List<Book> search(@Param("title") String title,
                      @Param("author") String author,
                      @Param("year") Integer year);
}