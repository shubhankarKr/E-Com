 -- use `u730611153_ecommerce`;
 -- drop table users;

 -- drop database e_com;
-- CREATE DATABASE  IF NOT EXISTS  `e_com`;
 -- use `e_com`;


use `u730611153_ecommerce`;
SELECT TABLE_SCHEMA, TABLE_NAME, COLUMN_NAME, CONSTRAINT_NAME
       FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
       WHERE REFERENCED_TABLE_SCHEMA IS NOT NULL;
       
SET foreign_key_checks = 0;
drop table if exists category CASCADE;
drop table if exists products CASCADE;
drop table if exists address CASCADE;
drop table if exists users CASCADE;
drop table if exists orders CASCADE;
drop table if exists authorities CASCADE;
drop table if exists products_category_mapping CASCADE; 
drop table if exists color CASCADE;
drop table if exists api_details CASCADE;
      
SET foreign_key_checks = 1;


create table category(
	category_id int PRIMARY KEY AUTO_INCREMENT,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	name varchar(100) UNIQUE NOT NULL,
    md int default 0,
    active smallint NOT NULL DEFAULT 1
);
 -- ALTER TABLE category ADD UNIQUE(NAME);
insert into category(name,md) values('Mobile',1);
insert into category(name,md) values('Mobile Accessories',1);
insert into category(name,md) values('Smart Wearable Tech',1);
insert into category(name,md) values('Health Care Appliances',1);
insert into category(name,md) values('Laptops',1);
insert into category(name,md) values('Desktop PCs',1);
insert into category(name,md) values('Speakers',1);
insert into category(name,md) values('Camera',1);
insert into category(name,md) values('Camera Accessories ',1);
insert into category(name,md) values('Kitchen Appliances',1);

create table products(
	product_id int PRIMARY KEY AUTO_INCREMENT,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    description varchar(500) DEFAULT NULL,
	name varchar(100) NOT NULL,
    updated_by varchar(255) ,
    price int DEFAULT null,
    discount int DEFAULT null,
    seller varchar(50) default null,
    active smallint NOT NULL DEFAULT 1
);

-- DROP TABLE users;
create table users(
	user_id int PRIMARY KEY AUTO_INCREMENT,
    password VARCHAR(200) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	user_name varchar(100) UNIQUE NOT NULL,
    email varchar(100) UNIQUE NOT NULL,
    gender enum ('MALE','FEMALE','OTHERS') not null,
    first_name varchar(100),
    last_name varchar(100),
	active smallint NOT NULL DEFAULT 1
);

-- insert into users(password,user_name,email) values ('a','Clerk','he@gmail.com');

 -- drop table api_details;
create table orders(
	id int PRIMARY KEY AUTO_INCREMENT,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	buyer varchar(50) DEFAULT NULL,
    payment_status int DEFAULT NULL,
    order_status int DEFAULT NULL,
    active smallint NOT NULL DEFAULT 1
);

-- drop table authorities;
-- drop table color;
-- drop table products_category_mapping;
create table authorities(
	id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    user_id int ,
    md smallint NOT NULL DEFAULT 1
);
insert into authorities (name,md) values('ROLE_USER',1);
insert into authorities (name,md) values('ROLE_SELLER',1);
insert into authorities (name,md) values('ROLE_MANAGER',1);
insert into authorities (name,md) values('ROLE_ADMIN',1);
insert into authorities (name,md) values('ROLE_SUPER',1);

create table products_category_mapping(
	id int PRIMARY KEY AUTO_INCREMENT,
    category_id int ,
    product_id int 
);

create table color(
	id int PRIMARY KEY AUTO_INCREMENT,
    color_code varchar(50) NOT NULL ,
    product_id int 
);

 -- ALTER TABLE authorities drop COLUMN role;

select * from products_category_mapping;
select * from products;

 -- drop table products_category_mapping;
-- drop table users;
-- insert into authorities(name,user_id) values('ROLE_USER',852);

-- update authorities set name='ROLE_USER' where user_id=852;
-- drop tables authorities;
desc products;

select * from products;


create table address(
	id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(100) NOT NULL ,
    pin_code int NOT NULL,
    mobile_number bigint NOT NULL,
    locality varchar(50),
    city varchar(50),
    state varchar(50),
    landmark varchar(200),
    address_type enum ('HOME','WORK') ,
    user_id int,
    active smallint NOT NULL DEFAULT 1
);

  -- drop table api_details;

create table api_details(
   id int PRIMARY KEY AUTO_INCREMENT,
   method varchar(10) NOT NULL,
   api varchar(400) NOT NULL,
   api_path varchar (255) NOT NULL,
   description varchar(500) NOT NULL,
   host varchar(100) NOT NULL
);


select * from products;
-- delete from products where product_id <>6;

-- delete from api_details where id=21;
select * from users;

select * from address;