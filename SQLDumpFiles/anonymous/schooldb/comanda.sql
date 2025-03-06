create table if not exists comanda
(
    id        int         not null
        primary key,
    idClient  int         null,
    idProdus  int         null,
    cantitate varchar(45) null
);

