-- Create the email_subscription table
CREATE TABLE IF NOT EXISTS `email_subscription` (
   `id` INT PRIMARY KEY AUTO_INCREMENT,
   `date_created` DATE NOT NULL DEFAULT (CURRENT_DATE),
   `email` VARCHAR(255) UNIQUE NOT NULL
) ENGINE=INNODB;

-- Create the media_format table
CREATE TABLE IF NOT EXISTS `media_format` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `type` CHAR(50) UNIQUE NOT NULL
) ENGINE=INNODB;

-- Create the product_media table
CREATE TABLE IF NOT EXISTS `product_media` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `media_format_id` INT NOT NULL,
  `name` VARCHAR(255) UNIQUE NOT NULL,
  `url` VARCHAR(255) NOT NULL,
  
  FOREIGN KEY (`media_format_id`) REFERENCES `media_format` (`id`)
) ENGINE=InnoDB;

-- Create the Category table
CREATE TABLE IF NOT EXISTS `category` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255) UNIQUE NOT NULL
) ENGINE=INNODB;

-- Create the color table
CREATE TABLE IF NOT EXISTS `color` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `color` VARCHAR(255) UNIQUE NOT NULL
) ENGINE=InnoDB;

-- Create the size table
CREATE TABLE IF NOT EXISTS `size` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `type` CHAR(3) UNIQUE NOT NULL,
  `length` DECIMAL(4,1) NOT NULL,
  `width` DECIMAL(4,1) NOT NULL
) ENGINE=InnoDB;

-- Create the gender table
CREATE TABLE IF NOT EXISTS `gender` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `type` CHAR(1) UNIQUE NOT NULL
) ENGINE=InnoDB;

-- Create the material table
CREATE TABLE IF NOT EXISTS `material` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `material` VARCHAR(255) UNIQUE NOT NULL
) ENGINE=INNODB;

-- Create the variant table
CREATE TABLE IF NOT EXISTS `variant` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `inventory_count` INT NOT NULL DEFAULT 0,
  `color_id` INT,
  `size_id` INT,
  `gender_id` INT,
  `material_id` INT,
  
  FOREIGN KEY (`color_id`) REFERENCES `color` (`id`),
  FOREIGN KEY (`size_id`) REFERENCES `size` (`id`),
  FOREIGN KEY (`gender_id`) REFERENCES `gender` (`id`),
  FOREIGN KEY (`material_id`) REFERENCES `material` (`id`)
) ENGINE=InnoDB;

-- Create the barcode table
CREATE TABLE IF NOT EXISTS `barcode` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `type` VARCHAR(128) UNIQUE NOT NULL
) ENGINE=InnoDB;

-- Create the Product table
CREATE TABLE IF NOT EXISTS `product` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `SKU` VARCHAR(255) UNIQUE NOT NULL,
  `product_media_id` INT,
  `category_id` INT NOT NULL,
  `name` VARCHAR(255) UNIQUE NOT NULL,
  `description` TEXT NOT NULL,
  `manufacturer` VARCHAR(255),
  `weight` DECIMAL(10,2),
  `price` DECIMAL(10,2) NOT NULL,
  `tax` DECIMAL(10,2) NOT NULL,
  `variant_id` INT NOT NULL,
  `barcode_id` INT,
  `is_active` BOOLEAN DEFAULT TRUE,
  
  FOREIGN KEY (`product_media_id`) REFERENCES `product_media` (`id`),
  FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  FOREIGN KEY (`variant_id`) REFERENCES `variant` (`id`),
  FOREIGN KEY (`barcode_id`) REFERENCES `barcode` (`id`)
) ENGINE=INNODB;

-- Add FULLTEXT index for search functionality
ALTER TABLE `product`
ADD FULLTEXT(`SKU`, `name`, `description`, `manufacturer`);

-- Create the junction table for product & product_media tables
-- to establish a M-M relationship
CREATE TABLE IF NOT EXISTS `product_and_product_media` (
  `product_id` INT NOT NULL,
  `product_media_id` INT NOT NULL,
  PRIMARY KEY (`product_id`, `product_media_id`),
  
  FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  FOREIGN KEY (`product_media_id`) REFERENCES `product_media` (`id`)
) ENGINE=INNODB;