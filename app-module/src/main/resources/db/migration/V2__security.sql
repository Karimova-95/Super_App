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

