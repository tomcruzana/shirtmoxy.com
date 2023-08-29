-- Create the address table
CREATE TABLE `address` (
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
  `full_name` VARCHAR(255) NOT NULL,
  `city` VARCHAR(255) NOT NULL,
  `country` VARCHAR(255) NOT NULL,
  `state` VARCHAR(255) NOT NULL,
  `street` VARCHAR(255) NOT NULL,
  `line2` VARCHAR(255),
  `zip_code` VARCHAR(255) NOT NULL
) ENGINE = InnoDB;

-- Create the customer table
CREATE TABLE `customer` (
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `company` VARCHAR(255),
  `email` VARCHAR(255) UNIQUE NOT NULL,
  `password` varchar(500) NOT NULL,
  `role` varchar(100) NOT NULL,
  `date_created` DATETIME(6) NOT NULL,
  `last_updated` DATETIME(6) NOT NULL
) ENGINE = INNODB;

-- Create the authorities table
CREATE TABLE `authorities` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `customer_id` BIGINT NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
);

-- Create the order table
CREATE TABLE `orders` (
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
  `order_tracking_number` VARCHAR(255) NOT NULL,
  `total_price` DECIMAL(19,2) NOT NULL,
  `total_quantity` INT NOT NULL,
  `billing_address_id` BIGINT NOT NULL,
  `customer_id` BIGINT NOT NULL,
  `shipping_address_id` BIGINT NOT NULL,
  `status` VARCHAR(128) DEFAULT NULL,
  `date_created` DATETIME(6) NOT NULL,
  `last_updated` DATETIME(6) NOT NULL,
  
  UNIQUE KEY `UK_billing_address_id` (`billing_address_id`), ##POSSIBLE BUG -- if registered for a cx, other cx cant use this
  UNIQUE KEY `UK_shipping_address_id` (`shipping_address_id`), ##POSSIBLE BUG -- if registered for a cx, other cx cant use this
  KEY `K_customer_id` (`customer_id`),
  CONSTRAINT `FK_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_billing_address_id` FOREIGN KEY (`billing_address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FK_shipping_address_id` FOREIGN KEY (`shipping_address_id`) REFERENCES `address` (`id`)
) ENGINE = InnoDB;

-- Create the order item table
CREATE TABLE `order_item` (
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
  `image_url` VARCHAR(255) NOT NULL,
  `quantity` INT NOT NULL,
  `unit_price` DECIMAL(19,2) NOT NULL,
  `order_id` BIGINT NOT NULL,
  `product_id` BIGINT NOT NULL,

  KEY `K_order_id` (`order_id`),
  CONSTRAINT `FK_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FK_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE = INNODB;
