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
    answers_id INTEGER NOT NULL REFERENCES answers (id)
);


INSERT INTO posts (author, created, description, name, nickname)
VALUES ('root@local',
        '2021-12-10 10:11:23.728425',
        '1 - Админ всегда прав!)
2 - Если Админ не прав, смотри пункт 1.
3 - Мат запрещен.
4 - Оскорбления по расовому или половому признаку, а также любые другие оскорбления - БАН! ',
        'Правила форума!',
        'Admin')
ON CONFLICT DO NOTHING;

INSERT INTO posts (author, created, description, name, nickname)
VALUES ('root@local',
        '2021-12-10 10:08:44.604517',
        'Это форум обо всём. Можно общаться на любые темы.',
        'О чем этот форум?',
        'Admin')
ON CONFLICT DO NOTHING;

INSERT INTO answers (author, created, answer_name)
VALUES ('Admin', '2021-12-10 10:12:45.076404',
        'Пункт номер 2 - самый главный)))')
        ON CONFLICT DO NOTHING;


/* Security */

CREATE TABLE IF NOT EXISTS authorities
(
    id        SERIAL PRIMARY KEY,
    authority VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(100) NOT NULL,
    username     VARCHAR(100) NOT NULL UNIQUE,
    password     VARCHAR(100) NOT NULL,
    enabled      BOOLEAN      NOT NULL DEFAULT TRUE,
    authority_id INTEGER      NOT NULL REFERENCES authorities (id)
);

INSERT INTO authorities (authority)
VALUES ('ROLE_USER')
ON CONFLICT DO NOTHING;

INSERT INTO authorities (authority)
VALUES ('ROLE_ADMIN')
ON CONFLICT DO NOTHING;

INSERT INTO users (username, name, password, enabled, authority_id)
VALUES ('root@local',
        'Admin',
        '$2a$10$wY1twJhMQjGVxv4y5dBC5ucCBlzkzT4FIGa4FNB/pS9GaXC2wm9/W',
        TRUE,
        (SELECT id FROM authorities WHERE authority = 'ROLE_ADMIN'))
ON CONFLICT DO NOTHING;

INSERT INTO users (name, username, password, enabled, authority_id)
VALUES ('User', 'user', 'password', TRUE,
        (SELECT id FROM authorities WHERE authority = 'ROLE_USER'))
ON CONFLICT DO NOTHING;