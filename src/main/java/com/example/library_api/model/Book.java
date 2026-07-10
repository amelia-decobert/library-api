package com.example.library_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Define the class as an entity
@Entity
//Rename the table
@Table(name = "books")
// Generate getters & setters automatically (Lombok)
@Getter
@Setter
// Generate an empty Constructor (mandatory for Hibernate to rebuild an object)
@NoArgsConstructor
// Generate a Constructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(unique = true)
    private String isbn;

    @Column(name = "publication_year")
    private Integer publicationYear;
}
