-- Drop the existing tables (if necessary)
DROP TABLE IF EXISTS country;
DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS book;
-- ...

-- Create the tables

CREATE TABLE country (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    continent VARCHAR(255) NOT NULL
);

CREATE TABLE author (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    country_id BIGINT REFERENCES country(id)
);


CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(50) NOT NULL,
    author_id BIGINT REFERENCES author(id),
    available_copies INTEGER NOT NULL
);


-- Insert data into the tables (if necessary)
INSERT INTO country (name, countinent) VALUES ('England', 'Europe');
INSERT INTO author (name, surname, country_id) VALUES ('J.K.', 'Rowling', 1);
INSERT INTO book (name, category, author_id, available_copies)  VALUES ('Harry Potter and The Chamber of Secrets', 'FANTASY', 1, 200);
INSERT INTO book (name, category, author_id, available_copies)  VALUES ('Harry Potter and The Goblet of Fire', 'FANTASY', 1, 250);
-- ...
