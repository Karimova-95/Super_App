CREATE SEQUENCE invoice_seq
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE income_invoice
(
    id          INT DEFAULT NEXT VALUE FOR invoice_seq,
    no          int         not null,
    from_ca     varchar(25) not null,
    to_ca       varchar(25) not null,
    doc_sum     int     not null,
    description varchar(25) not null
);

insert into income_invoice (no, from_ca, to_ca, doc_sum, description) values
(452, 'ffff', 'aaaa', 745, 'dhcbhjc'),
(452, 'dddd', 'eeee', 7459, 'fgbfxbbhxgdb'),
(452, 'vvvv', 'iiii', 7455, 'qwsdawd')