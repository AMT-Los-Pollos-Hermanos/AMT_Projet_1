DROP SCHEMA IF EXISTS overflow;

CREATE SCHEMA IF NOT EXISTS overflow;

USE overflow;

CREATE TABLE users
(
    id         INT UNSIGNED PRIMARY KEY,
    username   VARCHAR(255),
    password   VARCHAR(255),
    email      VARCHAR(255),
    first_name VARCHAR(255),
    last_name  VARCHAR(255)
);

CREATE TABLE questions
(
    id      INT UNSIGNED PRIMARY KEY,
    title   VARCHAR(255),
    content LONGTEXT,
    user_id INT UNSIGNED,
    FOREIGN KEY (user_id) REFERENCES users (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE answers
(
    id          INT UNSIGNED PRIMARY KEY,
    title       VARCHAR(255),
    content     LONGTEXT,
    question_id INT UNSIGNED NOT NULL,
    user_id     INT UNSIGNED NOT NULL,
    FOREIGN KEY (question_id) REFERENCES questions (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE votes
(
    id          INT UNSIGNED PRIMARY KEY,
    question_id INT UNSIGNED,
    answer_id   INT UNSIGNED,
    user_id     INT UNSIGNED NOT NULL,
    FOREIGN KEY (question_id) REFERENCES questions (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (answer_id) REFERENCES answers (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
