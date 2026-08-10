CREATE TABLE authors (
                         id BIGSERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL
);

CREATE TABLE categories (
                            id BIGSERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE books (
                       id BIGSERIAL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       isbn VARCHAR(255) UNIQUE,
                       publication_year INTEGER,
                       author_id BIGINT NOT NULL REFERENCES authors(id)
);

CREATE TABLE book_categories (
                                 book_id BIGINT NOT NULL REFERENCES books(id),
                                 category_id BIGINT NOT NULL REFERENCES categories(id),
                                 PRIMARY KEY (book_id, category_id)
);

CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(255) NOT NULL
);