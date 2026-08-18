ALTER TABLE users RENAME COLUMN username TO email;

ALTER TABLE users
    ADD COLUMN created_at TIMESTAMP NOT NULL DEFAULT now(),
    ADD COLUMN updated_at TIMESTAMP NOT NULL DEFAULT now();

ALTER TABLE users DROP CONSTRAINT IF EXISTS chk_users_role;

UPDATE users SET role = REPLACE(role, 'ROLE_', '');

ALTER TABLE users
    ADD CONSTRAINT chk_users_role CHECK ( role IN ('USER', 'ADMIN'));