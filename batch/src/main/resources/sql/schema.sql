drop all objects;

create table APP_USER
(
    USERNAME varchar(255) not null
        constraint PRODUCT_PK
            primary key,
    FULLNAME varchar(255) not null,
    PASSWORD varchar(255) not null
);
