DROP SCHEMA IF EXISTS overflow;

CREATE SCHEMA IF NOT EXISTS overflow CHARACTER SET utf8;

USE overflow;

CREATE TABLE users
(
    id         VARCHAR(255) PRIMARY KEY,
    username   VARCHAR(255),
    password   VARCHAR(255),
    email      VARCHAR(255),
    first_name VARCHAR(255),
    last_name  VARCHAR(255)
);

CREATE TABLE questions
(
    id      INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    title   VARCHAR(255),
    content LONGTEXT,
    user_id VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE answers
(
    id          INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(255),
    content     LONGTEXT,
    question_id INT UNSIGNED NOT NULL,
    user_id     VARCHAR(255) NOT NULL,
    FOREIGN KEY (question_id) REFERENCES questions (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE votes
(
    id          INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    question_id INT UNSIGNED,
    answer_id   INT UNSIGNED,
    user_id     VARCHAR(255) NOT NULL,
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

SET NAMES utf8;

INSERT INTO overflow.users (id, username, password, email, first_name, last_name)
VALUES ('d2acfbba-13f1-4519-8fe5-0d977b3fffa6', 'gil', '$2a$10$AZRCfWhRDyS05nP7MR98p.UtA6jXjDac/8gkUqusMrJSmIHbYnjSG',
        'gil.balsiger@heig-vd.ch', 'Gil', 'Balsiger');
INSERT INTO overflow.users (id, username, password, email, first_name, last_name)
VALUES ('54ce8647-8742-4500-8b2a-ca7eb345da0c', 'julien',
        '$2a$10$XpbWbJX3ygbBrFsd5z5Qpum.fC.u1eYFgj52fuQ9suCbkH5HjH8WW', 'julien.beguin@heig-vd.ch', 'Julien', 'Béguin');
INSERT INTO overflow.users (id, username, password, email, first_name, last_name)
VALUES ('683d0d88-9ea2-4101-84a7-ccdb5fdad9db', 'chris', '$2a$10$iKxcLBuneprXIZOPEMM1RODetfg0sVevEhBa3YmoDKgT23vIEXL6a',
        'chris.barros@heig-vd.ch', 'Chris', 'Barros');
INSERT INTO overflow.users (id, username, password, email, first_name, last_name)
VALUES ('6a5d8d7a-c34d-49ed-8f6d-1843250b6e5e', 'gaetan',
        '$2a$10$jAgAK9NaXLQpODyqom3as.epUdaHrzL7ZMBHTktBbFkludXMYUEMa', 'gaetan.daubresse.beguin@heig-vd.ch', 'Gaëtan',
        'Daubresse');
