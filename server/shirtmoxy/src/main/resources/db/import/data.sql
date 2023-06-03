### TEST DATA ###

-- Inserting data into the `media_format` table
INSERT INTO `media_format` (`type`)
VALUES 
  ('png'),
  ('jpeg'),
  ('jpg');

-- Inserting data into the `product_media` table
INSERT INTO `product_media` (`media_format_id`, `name`, `url`)
VALUES 
  (1, 'media 1', 'http://example.com/media1'),
  (2, 'media 2', 'http://example.com/media2'),
  (1, 'media 3', 'http://example.com/media3');

-- Inserting data into the `category` table
INSERT INTO `category` (`name`)
VALUES 
  ('t-shirt'),
  ('sweatshirt'),
  ('hoodie');

-- Inserting data into the `color` table
INSERT INTO `color` (`color`)
VALUES 
  ('red'),
  ('blue'),
  ('yellow'),
  ('orange'),
  ('green');

-- Inserting data into the size table
INSERT INTO `size` (`type`, `length`, `width`)
VALUES 
  ('XS', 24.4, 16.5),
  ('S', 25.6, 17.7),
  ('M', 26.4, 18.1),
  ('L', 27.2, 19.3),
  ('XL', 28.3, 23.2),
  ('2XL', 29.1, 27.2),
  ('3XL', 29.5, 28);
  
-- Inserting data into the `gender` table
INSERT INTO `gender` (`type`)
VALUES 
  ('M'),
  ('F');

-- Inserting data into the `material` table
INSERT INTO `material` (`material`)
VALUES 
  ('Cotton'),
  ('Polyester'),
  ('Nylon');
  
-- Inserting data into the `variant` table
INSERT INTO `variant` (`inventory_count`, `color_id`, `size_id`, `gender_id`, `material_id`)
SELECT 
  FLOOR(RAND() * 1001), -- Random inventory count between 0 and 1000
  `c`.`id`, -- Color ID
  `s`.`id`, -- Size ID
  `g`.`id`, -- Gender ID
  `m`.`id` -- Material ID
FROM
  `color` `c`
CROSS JOIN
  `size` `s`
CROSS JOIN
  `gender` `g`
CROSS JOIN
  `material` `m`;

-- Inserting data into the barcode table
INSERT INTO `barcode` (`type`) VALUES
  ('UPC-A'),
  ('EAN-13'),
  ('QR Code'),
  ('Code 128');

-- Inserting data into the product table
INSERT INTO `product` (`SKU`, `product_media_id`, `category_id`, `name`, `description`, `manufacturer`, `weight`, `price`, `tax`, `variant_id`, `barcode_id`, `is_active`) VALUES
  ('SKU001', 1, 2, 'Product 1', 'Description 1', 'Manufacturer 1', 1.5, 19.99, 2.50, 1, 2, true),
  ('SKU002', 2, 1, 'Product 2', 'Description 2', 'Manufacturer 2', 2.0, 29.99, 3.00, 2, 1, true),
  ('SKU003', 3, 1, 'Product 3', 'Description 3', 'Manufacturer 3', 12.8, 24.99, 2.75, 1, 4, true),
  ('SKU004', 1, 3, 'Product 4', 'Description 4', 'Manufacturer 4', 0.9, 14.99, 1.50, 3, 3, true),
  ('SKU005', 2, 2, 'Product 5', 'Description 5', 'Manufacturer 5', 2.5, 39.99, 4.00, 4, 4, true),
  ('SKU006', 3, 1, 'Product 6', 'Description 6', 'Manufacturer 6', 1.2, 9.99, 1.00, 5, 4, true),
  ('SKU007', 1, 3, 'Product 7', 'Description 7', 'Manufacturer 7', 5.7, 54.99, 5.50, 6, 1, true),
  ('SKU008', 1, 2, 'Product 8', 'Description 8', 'Manufacturer 8', 0.8, 12.99, 1.25, 7, 1, true),
  ('SKU009', 1, 1, 'Product 9', 'Description 9', 'Manufacturer 9', 3.1, 49.99, 4.75, 8, 1, true),
  ('SKU010', 2, 2, 'Product 10', 'Description 10', 'Manufacturer 10', 1.7, 24.99, 2.50, 9, 1, true),
  ('SKU011', 3, 3, 'Product 11', 'Description 11', 'Manufacturer 11', 2.3, 34.99, 3.50, 10, 1, true),
  ('SKU012', 3, 1, 'Product 12', 'Description 12', 'Manufacturer 12', 1.4, 17.99, 1.75, 11, 2, true),
  ('SKU013', 1, 2, 'Product 13', 'Description 13', 'Manufacturer 13', 1.9, 27.99, 2.75, 12, 3, true),
  ('SKU014', 2, 3, 'Product 14', 'Description 14', 'Manufacturer 14', 0.6, 9.99, 1.00, 13, 4, true),
  ('SKU015', 2, 1, 'Product 15', 'Description 15', 'Manufacturer 15', 2.8, 64.99, 6.50, 14, 2, true),
  ('SKU016', 2, 2, 'Product 16', 'Description 16', 'Manufacturer 16', 1.1, 14.99, 1.50, 15, 2, true),
  ('SKU017', 3, 3, 'Product 17', 'Description 17', 'Manufacturer 17', 3.5, 44.99, 4.50, 16, 2, true),
  ('SKU018', 1, 1, 'Product 18', 'Description 18', 'Manufacturer 18', 2.2, 19.99, 2.00, 17, 2, true),
  ('SKU019', 2, 2, 'Product 19', 'Description 19', 'Manufacturer 19', 0.7, 11.99, 1.25, 18, 4, true),
  ('SKU020', 2, 3, 'Product 20', 'Description 20', 'Manufacturer 20', 4.0, 79.99, 8.00, 19, 3, true);


-- Insert statements for the product_and_product_media table
INSERT INTO `product_and_product_media` (`product_id`, `product_media_id`) VALUES
  (1, 1),
  (1, 2),
  (2, 3),
  (3, 1),
  (3, 3),
  (4, 2),
  (5, 1),
  (6, 3),
  (7, 1),
  (8, 2),
  (9, 3),
  (10, 1),
  (10, 2),
  (11, 1),
  (12, 2),
  (13, 3),
  (14, 1),
  (15, 3),
  (16, 2),
  (17, 1),
  (17, 3),
  (18, 2),
  (19, 1),
  (20, 1),
  (20, 2);