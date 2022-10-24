drop
all objects;

create table PRODUCT
(
    PRODUCT_ID   numeric      not null
        constraint PRODUCT_PK
            primary key,
    PRODUCT_NAME varchar(255) not null
);

create table CATEGORY
(
    CATEGORY_ID   numeric      not null
        constraint CATEGORY_PK
            primary key,
    CATEGORY_NAME varchar(255) not null
);

create table PRODUCT_CATEGORY
(
    CATEGORY_ID numeric not null,
    PRODUCT_ID  numeric not null,
    constraint PRODUCT_CATEGORY_PK
        primary key (CATEGORY_ID, PRODUCT_ID)
);
