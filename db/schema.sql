CREATE TABLE IF NOT EXISTS answers
(
    id          SERIAL PRIMARY KEY,
    author      VARCHAR(255) NOT NULL,
    created     TIMESTAMP NOT NULL,
    answer_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS posts
(
    id          SERIAL PRIMARY KEY,
    author      VARCHAR(255) NOT NULL,
    created     TIMESTAMP NOT NULL,
    description VARCHAR(255) NOT NULL,
    name        VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS posts_answers
(
    post_id    INTEGER NOT NULL REFERENCES posts (id),
    answers_id INTEGER NOT NULL UNIQUE REFERENCES answers (id)
);

CREATE TABLE IF NOT EXISTS users
(
    id       SERIAL PRIMARY KEY,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL
);
