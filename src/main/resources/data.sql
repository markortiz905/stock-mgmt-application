DROP TABLE IF EXISTS product cascade;
DROP TABLE IF EXISTS brand cascade;

create table brand (
 id integer not null auto_increment,
 name varchar(255) not null,
 country varchar(255) not null,
 primary key(name)
);

create table product (
 id integer not null auto_increment,
 reference varchar(255) not null,
 brand_name varchar(255) not null,
 denomination varchar(255) not null,
 price double not null,
 weight double not null,
 volume double not null,
 primary key(reference),
 foreign key (brand_name)
         references brand(name)
         on delete cascade
);

INSERT INTO BRAND (ID, NAME, COUNTRY )
VALUES(10001,  'Apple', 'China');
INSERT INTO BRAND (ID, NAME, COUNTRY )
VALUES(10002,  'Samsung', 'US');

INSERT INTO PRODUCT (ID, REFERENCE, BRAND_NAME, DENOMINATION, PRICE, WEIGHT, VOLUME )
VALUES(10001,  'IPHONE10', 'Apple', 'USD', 10000.0, 1.0, 1.0);
INSERT INTO PRODUCT (ID, REFERENCE, BRAND_NAME, DENOMINATION, PRICE, WEIGHT, VOLUME )
VALUES(10002,  'IPHONE15', 'Apple', 'USD', 15000.0, 1.0, 1.0);

