 -- drop database e_com;
CREATE DATABASE  IF NOT EXISTS  `u730611153_ecommerce`;
use `u730611153_ecommerce`;

-- drop table users;

create table `category`(
	`category_id` int(11) PRIMARY KEY AUTO_INCREMENT,
	`created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`name` varchar(100) NOT NULL,
    `product_id` int DEFAULT NULL
)ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
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

create table `products`(
	`product_id` int(11) PRIMARY KEY AUTO_INCREMENT,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `description` varchar(500) DEFAULT NULL,
	`name` varchar(100) NOT NULL,
    `updated_by` varchar(255) NOT NULL,
    `price` int DEFAULT -1,
    `discount` int DEFAULT 0,
    `color` varchar(20) DEFAULT NULL,
    `image_id` varchar(255) DEFAULT NULL,
    `category_id` int NOT NULL,
	`stock` int DEFAULT 0,
    `buyer` varchar(255) DEFAULT NULL
)ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

alter table category add constraint FK_categoy foreign key (category_id) references products (product_id);
alter table products add constraint FK_product foreign key (product_id) references category (category_id);
create table `users`(
	`user_id` int(11) PRIMARY KEY AUTO_INCREMENT,
    `password` VARCHAR(200) NOT NULL,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`user_name` varchar(100) UNIQUE NOT NULL,
    `email` varchar(100) UNIQUE NOT NULL,
    `role` varchar(50) NOT NULL default 'USER',
	`active` int NOT NULL DEFAULT 1
)ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
-- insert into users(user_id,password,user_name,email) values (1,'a','Clerk','he@gmail.com');
create table `order`(
	`id` int(11) PRIMARY KEY AUTO_INCREMENT,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `cart_id` int,
	`buyer_id` int DEFAULT NULL,
	`seller_id` int DEFAULT NULL,
    `payment_status` int DEFAULT NULL,
    `order_status` int DEFAULT NULL
    
)ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `cart` (
	`cart_id`INT AUTO_INCREMENT PRIMARY KEY,
    `user_id` INT,
    `product_id` INT,
    `product_name` VARCHAR(255),
    `price` DECIMAL(10, 2),
    `quantity` INT,
    `total` DECIMAL(10, 2),
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
)ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


select * from users;




