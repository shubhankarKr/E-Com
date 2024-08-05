create table address (
  id integer not null auto_increment, 
  active smallint, 
  created_at datetime(6), 
  created_by varchar(255), 
  updated_at datetime(6), 
  updated_by varchar(255), 
  address_type enum ('HOME', 'WORK'), 
  city varchar(255), 
  landmark varchar(255), 
  locality varchar(255), 
  mobile_number varchar(255) not null, 
  name varchar(255), 
  pin_code integer not null, 
  state varchar(255), 
  user_id integer, 
  primary key (id)
) engine = InnoDB;

create table api_details (
  id integer not null auto_increment, 
  api varchar(255), 
  api_path varchar(255), 
  description varchar(255), 
  host varchar(255), 
  method varchar(255), 
  primary key (id)
) engine = InnoDB;

create table authorities (
  id integer not null auto_increment, 
  active smallint, 
  created_at datetime(6), 
  created_by varchar(255), 
  updated_at datetime(6), 
  updated_by varchar(255), 
  md smallint, 
  name varchar(255), 
  user_id integer, 
  primary key (id)
) engine = InnoDB;

create table cart (
  id integer not null, 
  user_name varchar(255), 
  primary key (id)
) engine = InnoDB;

create table category (
  category_id integer not null, 
  active smallint, 
  created_at datetime(6), 
  created_by varchar(255), 
  updated_at datetime(6), 
  updated_by varchar(255), 
  name varchar(255), 
  primary key (category_id)
) engine = InnoDB;

create table color (
  id integer not null, 
  active smallint, 
  created_at datetime(6), 
  created_by varchar(255), 
  updated_at datetime(6), 
  updated_by varchar(255), 
  color_code varchar(255), 
  product_id integer, 
  primary key (id)
) engine = InnoDB;

create table image (
  pk integer not null auto_increment, 
  image LONGBLOB not null, 
  user_id integer, 
  primary key (pk)
) engine = InnoDB;

create table image_products (
  image_pk integer not null, products_product_id integer not null
) engine = InnoDB;

create table products (
  product_id integer not null, 
  active smallint, 
  created_at datetime(6), 
  created_by varchar(255), 
  updated_at datetime(6), 
  updated_by varchar(255), 
  description varchar(255), 
  discount integer, 
  name varchar(255), 
  price integer, 
  seller varchar(255), 
  cart_id integer, 
  primary key (product_id)
) engine = InnoDB;

create table products_category_mapping (
  id integer not null, 
  category_id integer not null, 
  product_id integer not null, 
  primary key (id, product_id, category_id)
) engine = InnoDB;

create table users (
  user_id integer not null, 
  active smallint, 
  created_at datetime(6), 
  created_by varchar(255), 
  updated_at datetime(6), 
  updated_by varchar(255), 
  email varchar(255) not null, 
  first_name varchar(255) not null, 
  gender enum ('MALE', 'FEMALE', 'OTHERS'), 
  last_name varchar(255) not null, 
  password varchar(255), 
  user_name varchar(255), 
  primary key (user_id)
) engine = InnoDB;
