create table passport
(
    id         serial primary key,
    fio        varchar(100) not null,
    serial     varchar(100) not null,
    expiration timestamp without time zone not null default now()

);
