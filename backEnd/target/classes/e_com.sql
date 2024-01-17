 -- drop database e_com;
CREATE DATABASE  IF NOT EXISTS  `u730611153_ecommerce`;
use `u730611153_ecommerce`;

drop table category;
drop table products;
drop table cart;
drop table users;

-- Create category table
CREATE TABLE category (
    category_id SERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- Insert data into category table
INSERT INTO category(name) VALUES ('Mobile');
INSERT INTO category(name) VALUES ('Mobile Accessories');
INSERT INTO category(name) VALUES ('Smart Wearable Tech');
INSERT INTO category(name) VALUES ('Health Care Appliances');
INSERT INTO category(name) VALUES ('Laptops');
INSERT INTO category(name) VALUES ('Desktop PCs');
INSERT INTO category(name) VALUES ('Speakers');
INSERT INTO category(name) VALUES ('Camera');
INSERT INTO category(name) VALUES ('Camera Accessories ');
INSERT INTO category(name) VALUES ('Kitchen Appliances');

-- Create products table
CREATE TABLE products (
    product_id SERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    description VARCHAR(500),
    name VARCHAR(100) NOT NULL,
    updated_by VARCHAR(255) NOT NULL,
    price INT DEFAULT -1,
    discount INT DEFAULT 0,
    color VARCHAR(20),
    image_id VARCHAR(255),
    stock INT DEFAULT 0,
    buyer VARCHAR(255)
);

-- Create users table
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    password VARCHAR(200) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_name VARCHAR(100) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    role VARCHAR(50) NOT NULL DEFAULT 'USER',
    active INT NOT NULL DEFAULT 1
);

-- Create order table
CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    cart_id INT,
    buyer_id INT,
    seller_id INT,
    payment_status INT,
    order_status INT
);

-- Create cart table
CREATE TABLE cart (
    cart_id SERIAL PRIMARY KEY,
    user_id INT,
    product_id INT,
    product_name VARCHAR(255),
    price DECIMAL(10, 2),
    quantity INT,
    total DECIMAL(10, 2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Select data from users table
SELECT * FROM users;
