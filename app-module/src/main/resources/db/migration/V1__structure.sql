CREATE SEQUENCE invoice_seq
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE income_invoice
(
    id          BIGINT DEFAULT NEXT VALUE FOR invoice_seq PRIMARY KEY ,
    date        TIMESTAMP   not null,
    no          INT         not null,
    from_ca     varchar(25) not null,
    to_ca       varchar(25) not null,
    doc_sum     DOUBLE     not null,
    description varchar(25)
);