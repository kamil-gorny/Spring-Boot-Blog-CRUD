INSERT INTO ROLE (role_id, role)
VALUES (1, 'ROLE_ADMIN');
INSERT INTO ROLE (role_id, role)
VALUES (2, 'ROLE_USER');

INSERT INTO USER(user_id, first_name, last_name, email, password, active, username)
VALUES(1, 'Kamil', 'Górny', 'kamil.gorny99@gmail.com', '$2a$10$N2c/iLnMuyXFunQxS9d9fu1P9TxkL4uS.5aYCD0K6WUf0ksnBWBdK', 1, 'Kamil');

INSERT INTO USER(user_id, first_name, last_name, email, password, active, username)
VALUES(2, 'Kamil', 'Górny', 'oliwia.gorny99@gmail.com', '$2a$10$N2c/iLnMuyXFunQxS9d9fu1P9TxkL4uS.5aYCD0K6WUf0ksnBWBdK', 1, 'Oliwia');

INSERT INTO USER(user_id, first_name, last_name, email, password, active, username)
VALUES(3, 'Kamil', 'Górny', 'oliwia.gorny@gmail.com', '$2a$10$N2c/iLnMuyXFunQxS9d9fu1P9TxkL4uS.5aYCD0K6WUf0ksnBWBdK', 1, 'Pozdro');

INSERT INTO USER(user_id, first_name, last_name, email, password, active, username)
VALUES(4, 'Admin', 'Górny', 'kamil.gorny@gmail.com', '$2a$10$N2c/iLnMuyXFunQxS9d9fu1P9TxkL4uS.5aYCD0K6WUf0ksnBWBdK', 1, 'Admin');




INSERT INTO USER_ROLE (user_id, role_id)
VALUES (1, 1);
INSERT INTO USER_ROLE (user_id, role_id)
VALUES (2, 2);
INSERT INTO USER_ROLE (user_id, role_id)
VALUES (3, 2);
INSERT INTO USER_ROLE (user_id, role_id)
VALUES (4, 1);
INSERT INTO USER_ROLE (user_id, role_id)
VALUES (4, 2);


INSERT INTO POST(post_id, content, title, user_id)
VALUES(1, 'Pierwszy post', 'Tytul pierwszego postu', 1);

INSERT INTO POST(post_id, content, title, user_id)
VALUES(2, 'Drugi post', 'Tytul drugiego postu', 2);

INSERT INTO POST(post_id, content, title, user_id)
VALUES(3, 'Trzeci post', 'Tytul trzeciego postu', 3);


INSERT INTO POST(post_id, content, title, user_id)
VALUES(4, 'Czwarty post', 'Tytul czwartego postu', 1);