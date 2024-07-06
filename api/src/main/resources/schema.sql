CREATE SCHEMA IF NOT EXISTS user_access;

CREATE TABLE IF NOT EXISTS user_access.users
(
    id       UUID PRIMARY KEY,
    email    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);