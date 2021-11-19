CREATE SEQUENCE invoice_seq
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE income_invoice
(
    id          INT DEFAULT NEXT VALUE FOR invoice_seq,
    date        date        not null,
    no          int         not null,
    from_ca     varchar(25) not null,
    to_ca       varchar(25) not null,
    doc_sum     numeric     not null,
    description varchar(25) not null
);