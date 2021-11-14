create table passport
(
    id              serial primary key,
    fio             varchar(100) not null,
    email           varchar(100) null,
    passport_serial int          not null,
    passport_number int          not null,
    birth_date      timestamp without time zone not null,
    expiration      timestamp without time zone not null
);
