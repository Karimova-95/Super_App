CREATE SEQUENCE security_seq
    START WITH 1
    INCREMENT BY 5;

CREATE TABLE sec_group
(
    id   INT DEFAULT NEXT VALUE FOR security_seq,
    name varchar(30) not null
);

CREATE TABLE sec_user
(
    id            INT DEFAULT NEXT VALUE FOR security_seq,
    id_group      int         not null,
    name          varchar(30) not null,
    password_hash varchar(32),
    FOREIGN KEY (id_group) REFERENCES sec_group (id)
);

INSERT INTO sec_group(name)
VALUES ('ADMIN'),
       ('USER');
INSERT INTO sec_user(name, id_group, password_hash)
VALUES ('alex', 1, '96e79218965eb72c92a549dd5a330112');
INSERT INTO sec_user(name, id_group, password_hash)
VALUES ('tom', 6, 'e3ceb5881a0a1fdaad01296d7554868d');