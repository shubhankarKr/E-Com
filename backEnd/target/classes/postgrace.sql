-- Create category table
CREATE TABLE category (
    category_id SERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    name VARCHAR(100) NOT NULL,
    product_id INT
);

-- Add unique constraint
ALTER TABLE category ADD CONSTRAINT unique_name UNIQUE (name);

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
    category_id INT NOT NULL,
    stock INT DEFAULT 0,
    buyer VARCHAR(255)
);

-- Add foreign key constraints
ALTER TABLE products ADD CONSTRAINT FK_category FOREIGN KEY (category_id) REFERENCES category (category_id);

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

-- Insert data into users table
-- INSERT INTO users(user_id, password, user_name, email) VALUES (1, 'a', 'Clerk', 'he@gmail.com');

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
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

-- Select data from users table
SELECT * FROM users;
