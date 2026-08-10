CREATE TABLE loans (
                       id BIGSERIAL PRIMARY KEY,
                       loan_date DATE NOT NULL,
                       due_date DATE NOT NULL,
                       returned_date DATE,
                       status VARCHAR(50) NOT NULL,
                       book_id BIGINT NOT NULL REFERENCES books(id),
                       user_id BIGINT NOT NULL REFERENCES users(id)
);