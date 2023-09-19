-- Drop the database if it exists
DROP DATABASE IF EXISTS `shirtmoxy_db_dev`;

-- Create the database
CREATE DATABASE IF NOT EXISTS `shirtmoxy_db_dev`;

-- Use the newly created database
USE `shirtmoxy_db_dev`;

-- Enable FK checks
SET FOREIGN_KEY_CHECKS=1;

-- Create the email_subscription table
CREATE TABLE IF NOT EXISTS `email_subscription` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `date_created` DATE NOT NULL DEFAULT (CURRENT_DATE),
  `email` VARCHAR(255) UNIQUE NOT NULL
) ENGINE = INNODB;

-- Create the Country table
CREATE TABLE IF NOT EXISTS `country` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `code` VARCHAR(2) NOT NULL,
  `name` VARCHAR(255) NOT NULL
) ENGINE=INNODB;

-- Create the State table
CREATE TABLE `state` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255) NOT NULL,
  `country_id` INT NOT NULL,
  FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=INNODB;