-- Create the Contact Us Category table
CREATE TABLE IF NOT EXISTS `contact_us_category` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255) NOT NULL
) ENGINE=INNODB;

-- Insert values into the Contact Us Category table
INSERT INTO `contact_us_category` (`name`) VALUES
  ('General Inquiry'),
  ('Sales or Product Inquiries'),
  ('Technical Problems'),
  ('Billing or Payment Issues'),
  ('Feedback or Suggestions'),
  ('Partnership or Collaboration'),
  ('Media or Press Inquiries'),
  ('Employment or Careers'),
  ('Privacy or Data Concerns'),
  ('Marketing or Advertising Inquiries'),
  ('Request for Information');

-- Create the Contact Us table
CREATE TABLE `contact_us` (
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(15),
  `subject` VARCHAR(255) NOT NULL,
  `category_id` INT NOT NULL,
  `message` VARCHAR(1000) NOT NULL,
  `date_created` DATETIME(6) NOT NULL,
  `last_updated` DATETIME(6) NOT NULL,
  
   FOREIGN KEY (`category_id`) REFERENCES `contact_us_category` (`id`)
) ENGINE = INNODB;

-- Insert dummy data into the Contact Us table
INSERT INTO `contact_us` (`first_name`, `last_name`, `email`, `phone`, `subject`, `category_id`, `message`, `date_created`, `last_updated`)
VALUES
  ('John', 'Doe', 'john.doe@example.com', '1234567890', 'Question about a product', 2, 'I have a question about one of your products.', NOW(), NOW()),
  ('Jane', 'Smith', 'jane.smith@example.com', '3609324555', 'Technical Issue', 3, 'Im experiencing technical problems with your website.', NOW(), NOW());

-- Check all contact us messages
SELECT * FROM	`contact_us`;
