drop all objects;

create table PRODUCT
(
    PRODUCT_ID   numeric      not null
        constraint PRODUCT_PK
            primary key,
    PRODUCT_NAME varchar(255) not null
);
