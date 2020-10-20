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

CREATE TABLE contents
(
    id         VARCHAR(255) PRIMARY KEY,
    user_id    VARCHAR(255) NOT NULL,
    content    LONGTEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE main_contents
(
    content_id  VARCHAR(255) PRIMARY KEY,
    FOREIGN KEY (content_id) REFERENCES contents (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE comments
(
    content_id          VARCHAR(255) PRIMARY KEY,
    main_content_id     VARCHAR(255) NOT NULL,
    FOREIGN KEY (content_id) REFERENCES contents (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (main_content_id) REFERENCES main_contents (content_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE questions
(
    content_id  VARCHAR(255) PRIMARY KEY,
    title       VARCHAR(255),
    FOREIGN KEY (content_id) REFERENCES main_contents (content_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE answers
(
    content_id  VARCHAR(255) PRIMARY KEY,
    question_id VARCHAR(255) NOT NULL,
    FOREIGN KEY (content_id) REFERENCES main_contents (content_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (question_id) REFERENCES questions (content_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE votes
(
    id          VARCHAR(255) PRIMARY KEY,
    content_id  VARCHAR(255) NOT NULL,
    user_id     VARCHAR(255) NOT NULL,
    state       VARCHAR(255) NOT NULL,
    FOREIGN KEY (content_id) REFERENCES contents (id)
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


INSERT INTO overflow.contents (id, user_id, content)
    VALUES ('73dbc27f-d54f-417c-b576-07f1c3cfd301', 'd2acfbba-13f1-4519-8fe5-0d977b3fffa6', 'Contenu 1');
INSERT INTO overflow.main_contents (content_id) VALUES ('73dbc27f-d54f-417c-b576-07f1c3cfd301');
INSERT INTO overflow.questions (content_id, title) VALUES ('73dbc27f-d54f-417c-b576-07f1c3cfd301', 'Question 1');

INSERT INTO overflow.contents (id, user_id, content)
    VALUES ('58dbc27f-d54f-417c-b576-07f1c3cfd301', '54ce8647-8742-4500-8b2a-ca7eb345da0c', 'Contenu 2');
INSERT INTO overflow.main_contents (content_id) VALUES ('58dbc27f-d54f-417c-b576-07f1c3cfd301');
INSERT INTO overflow.questions (content_id, title) VALUES ('58dbc27f-d54f-417c-b576-07f1c3cfd301', 'Question 2');

INSERT INTO overflow.contents (id, user_id, content)
    VALUES ('abdbc27f-d54f-417c-b576-07f1c3cfd301', '683d0d88-9ea2-4101-84a7-ccdb5fdad9db', 'Contenu 3');
INSERT INTO overflow.main_contents (content_id) VALUES ('abdbc27f-d54f-417c-b576-07f1c3cfd301');
INSERT INTO overflow.questions (content_id, title) VALUES ('abdbc27f-d54f-417c-b576-07f1c3cfd301', 'Question 3');

INSERT INTO overflow.contents (id, user_id, content)
    VALUES ('18dbc27f-d54f-417c-b576-07f1c3cfd301', '6a5d8d7a-c34d-49ed-8f6d-1843250b6e5e', 'Contenu 4');
INSERT INTO overflow.main_contents (content_id) VALUES ('18dbc27f-d54f-417c-b576-07f1c3cfd301');
INSERT INTO overflow.questions (content_id, title) VALUES ('18dbc27f-d54f-417c-b576-07f1c3cfd301', 'Question 4');


INSERT INTO overflow.contents (id, user_id, content)
    VALUES ('35dbc27f-d54f-417c-b576-07f1c3cfd301', '6a5d8d7a-c34d-49ed-8f6d-1843250b6e5e', 'Answer 1');
INSERT INTO overflow.main_contents (content_id) VALUES ('35dbc27f-d54f-417c-b576-07f1c3cfd301');
INSERT INTO overflow.answers (content_id, question_id) VALUES ('35dbc27f-d54f-417c-b576-07f1c3cfd301', '73dbc27f-d54f-417c-b576-07f1c3cfd301');

INSERT INTO overflow.contents (id, user_id, content)
    VALUES ('11dbc27f-d54f-417c-b576-07f1c3cfd301', '54ce8647-8742-4500-8b2a-ca7eb345da0c', 'Answer 2');
INSERT INTO overflow.main_contents (content_id) VALUES ('11dbc27f-d54f-417c-b576-07f1c3cfd301');
INSERT INTO overflow.answers (content_id, question_id) VALUES ('11dbc27f-d54f-417c-b576-07f1c3cfd301', '73dbc27f-d54f-417c-b576-07f1c3cfd301');


INSERT INTO overflow.contents (id, user_id, content)
VALUES ('778bc27f-d54f-417c-b576-07f1c3cfd301', '6a5d8d7a-c34d-49ed-8f6d-1843250b6e5e', 'Comment 1');
INSERT INTO overflow.comments (content_id, main_content_id) VALUES ('778bc27f-d54f-417c-b576-07f1c3cfd301', '73dbc27f-d54f-417c-b576-07f1c3cfd301');

INSERT INTO overflow.contents (id, user_id, content)
VALUES ('965bc27f-d54f-417c-b576-07f1c3cfd301', '6a5d8d7a-c34d-49ed-8f6d-1843250b6e5e', 'Comment 2');
INSERT INTO overflow.comments (content_id, main_content_id) VALUES ('965bc27f-d54f-417c-b576-07f1c3cfd301', '35dbc27f-d54f-417c-b576-07f1c3cfd301');

INSERT INTO overflow.contents (id, user_id, content)
VALUES ('555bc27f-d54f-417c-b576-07f1c3cfd301', '6a5d8d7a-c34d-49ed-8f6d-1843250b6e5e', 'Comment 3');
INSERT INTO overflow.comments (content_id, main_content_id) VALUES ('555bc27f-d54f-417c-b576-07f1c3cfd301', '58dbc27f-d54f-417c-b576-07f1c3cfd301');

INSERT INTO votes (id, content_id, user_id, state) VALUES ('111bc27f-d54f-417c-b576-07f1c3cfd301', '73dbc27f-d54f-417c-b576-07f1c3cfd301', '54ce8647-8742-4500-8b2a-ca7eb345da0c', 'UP');
INSERT INTO votes (id, content_id, user_id, state) VALUES ('112bc27f-d54f-417c-b576-07f1c3cfd301', '35dbc27f-d54f-417c-b576-07f1c3cfd301', '54ce8647-8742-4500-8b2a-ca7eb345da0c', 'UP');
INSERT INTO votes (id, content_id, user_id, state) VALUES ('113bc27f-d54f-417c-b576-07f1c3cfd301', '11dbc27f-d54f-417c-b576-07f1c3cfd301', '54ce8647-8742-4500-8b2a-ca7eb345da0c', 'DOWN');
INSERT INTO votes (id, content_id, user_id, state) VALUES ('114bc27f-d54f-417c-b576-07f1c3cfd301', '73dbc27f-d54f-417c-b576-07f1c3cfd301', 'd2acfbba-13f1-4519-8fe5-0d977b3fffa6', 'DOWN');
