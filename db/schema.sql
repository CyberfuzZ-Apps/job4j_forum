/* At first create database with name 'forum' */

CREATE TABLE IF NOT EXISTS answers
(
    id          SERIAL PRIMARY KEY,
    author      VARCHAR(255) NOT NULL,
    created     TIMESTAMP    NOT NULL,
    answer_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS posts
(
    id          SERIAL PRIMARY KEY,
    author      VARCHAR(255) NOT NULL,
    created     TIMESTAMP    NOT NULL,
    description VARCHAR(255) NOT NULL,
    name        VARCHAR(255) NOT NULL,
    nickname    VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS posts_answers
(
    post_id    INTEGER NOT NULL REFERENCES posts (id),
    answers_id INTEGER NOT NULL UNIQUE REFERENCES answers (id)
);

/* Security */

CREATE TABLE authorities
(
    id        SERIAL PRIMARY KEY,
    authority VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(100)  NOT NULL,
    username     VARCHAR(100)  NOT NULL UNIQUE,
    password     VARCHAR(100)  NOT NULL,
    enabled      BOOLEAN       NOT NULL DEFAULT TRUE,
    authority_id INTEGER       NOT NULL REFERENCES authorities (id)
);

INSERT INTO authorities (authority)
VALUES ('ROLE_USER');
INSERT INTO authorities (authority)
VALUES ('ROLE_ADMIN');

INSERT INTO users (username, email, password, enabled, authority_id)
VALUES ('Admin', 'root@local',
        '$2a$10$wY1twJhMQjGVxv4y5dBC5ucCBlzkzT4FIGa4FNB/pS9GaXC2wm9/W', TRUE,
        (SELECT id FROM authorities WHERE authority = 'ROLE_ADMIN'));