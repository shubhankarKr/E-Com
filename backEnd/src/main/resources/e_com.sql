use `u730611153_ecommerce`;
 -- drop table users;

create table category(
	category_id int(11) PRIMARY KEY AUTO_INCREMENT,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	name varchar(100) UNIQUE NOT NULL,
    product_id int DEFAULT NULL
);
ALTER TABLE category ADD UNIQUE(NAME);
insert into category(name) values('Mobile');
insert into category(name) values('Mobile Accessories');
insert into category(name) values('Smart Wearable Tech');
insert into category(name) values('Health Care Appliances');
insert into category(name) values('Laptops');
insert into category(name) values('Desktop PCs');
insert into category(name) values('Speakers');
insert into category(name) values('Camera');
insert into category(name) values('Camera Accessories ');
insert into category(name) values('Kitchen Appliances');

create table products(
	product_id int(11) PRIMARY KEY AUTO_INCREMENT,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    description varchar(500) DEFAULT NULL,
	name varchar(100) NOT NULL,
    updated_by varchar(255) NOT NULL,
    price int DEFAULT -1,
    discount int DEFAULT 0,
    color varchar(20) DEFAULT NULL,
    image_id varchar(255) DEFAULT NULL,
	stock int DEFAULT 0,
    buyer varchar(255) DEFAULT NULL
);

create table users(
	user_id int(11) PRIMARY KEY AUTO_INCREMENT,
    password VARCHAR(200) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	user_name varchar(100) UNIQUE NOT NULL,
    email varchar(100) UNIQUE NOT NULL,
	active int NOT NULL DEFAULT 1
);
-- insert into users(user_id,password,user_name,email) values (1,'a','Clerk','he@gmail.com');


create table orders(
	id int(11) PRIMARY KEY AUTO_INCREMENT,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    cart_id int,
	buyer_id int DEFAULT NULL,
	seller_id int DEFAULT NULL,
    payment_status int DEFAULT NULL,
    order_status int DEFAULT NULL
    
);
select * from category;
select * from products_category_mapping;
desc products;
select next_val as id_val from products_seq for update;

update products_seq set next_val= 10000 where next_val=1101;
insert into products_category_mapping values(1003,6);

ALTER TABLE category add unique(name);
ALTER TABLE products ALTER category_id SET DEFAULT 0;

insert into products (buyer,color,created_at,description,discount,image_id,name,price,stock,updated_at,updated_by,product_id) values (NULL,NULL,'2024-01-18 20:24:42',NULL,NULL,NULL,'Tesiing ',NULL,NULL,'2024-01-18 20:24:42','anonymousUser',2009);


create table authorities(
	id int (11) PRIMARY KEY AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    user_id int not NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

create table products_category_mapping(
	id int (11) PRIMARY KEY AUTO_INCREMENT,
    category_id int ,
    product_id int 
);

 ALTER TABLE authorities drop COLUMN role;

select * from products_category_mapping;
select * from products;

 drop table products_category_mapping;
-- drop table users;
insert into authorities(name,user_id) values('ROLE_USER',852);

update authorities set name='ROLE_USER' where user_id=852;

desc products_category_mapping;