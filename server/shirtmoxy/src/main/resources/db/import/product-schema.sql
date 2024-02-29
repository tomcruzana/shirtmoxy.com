-- Create the product_media table
CREATE TABLE IF NOT EXISTS `product_media` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255) UNIQUE NOT NULL,
  `url` VARCHAR(255) NOT NULL
) ENGINE = INNODB;

-- Create the product_type table
CREATE TABLE IF NOT EXISTS `product_type` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255) UNIQUE NOT NULL
) ENGINE = InnoDB;

-- Create the Category table
CREATE TABLE IF NOT EXISTS `category` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255) UNIQUE NOT NULL
) ENGINE = INNODB;

-- Create the Manufacturer table
CREATE TABLE IF NOT EXISTS `manufacturer` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255) UNIQUE NOT NULL
) ENGINE = INNODB;

-- Create the gender table
CREATE TABLE IF NOT EXISTS `gender` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `type` CHAR(12) UNIQUE NOT NULL
) ENGINE = InnoDB;

-- Create the color table
CREATE TABLE IF NOT EXISTS `color` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255) UNIQUE NOT NULL,
  `red` CHAR(3) NOT NULL,
  `green` CHAR(3) NOT NULL,
  `blue` CHAR(3) NOT NULL
) ENGINE = InnoDB;

-- Create the size table
CREATE TABLE IF NOT EXISTS `size` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `type` CHAR(6) UNIQUE NOT NULL,
  `length` DECIMAL(4, 1) NOT NULL,
  `width` DECIMAL(4, 1) NOT NULL
) ENGINE = InnoDB;

-- Create the material table
CREATE TABLE IF NOT EXISTS `material` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `type` VARCHAR(255) UNIQUE NOT NULL
) ENGINE = INNODB;

-- Create the barcode table
CREATE TABLE IF NOT EXISTS `barcode` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `type` VARCHAR(128) UNIQUE NOT NULL
) ENGINE = INNODB;

-- Create the Product table
CREATE TABLE IF NOT EXISTS `product` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `SKU` VARCHAR(255) UNIQUE NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `description` TEXT NOT NULL,
  `unit_price` DECIMAL(10, 2) NOT NULL,
  `weight` DECIMAL(10, 2),
  `units_in_stock` INT DEFAULT 0,
  `is_active` BOOLEAN DEFAULT TRUE,
  `date_created` DATETIME(6) DEFAULT NULL,
  `last_updated` DATETIME(6) DEFAULT NULL,
  
  `product_media_id` INT,
  `product_type_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  `manufacturer_id` INT NOT NULL,
  `gender_id` INT NOT NULL,
  `color_id` INT NOT NULL,
  `size_id` INT NOT NULL,
  `material_id` INT NOT NULL,
  `barcode_id` INT,
  
  FOREIGN KEY (`product_media_id`) REFERENCES `product_media` (`id`),
  FOREIGN KEY (`product_type_id`) REFERENCES `product_type` (`id`),
  FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  FOREIGN KEY (`manufacturer_id`) REFERENCES `manufacturer` (`id`),
  FOREIGN KEY (`gender_id`) REFERENCES `gender` (`id`),
  FOREIGN KEY (`color_id`) REFERENCES `color` (`id`),
  FOREIGN KEY (`size_id`) REFERENCES `size` (`id`),
  FOREIGN KEY (`material_id`) REFERENCES `material` (`id`),
  FOREIGN KEY (`barcode_id`) REFERENCES `barcode` (`id`)
) ENGINE = INNODB;

-- Create the junction table for product & product_media tables
-- to establish a M-M relationship
CREATE TABLE IF NOT EXISTS `product_and_product_media` (
  `product_id` BIGINT NOT NULL,
  `product_media_id` INT NOT NULL,
  PRIMARY KEY (`product_id`, `product_media_id`),
  FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  FOREIGN KEY (`product_media_id`) REFERENCES `product_media` (`id`)
) ENGINE = INNODB;