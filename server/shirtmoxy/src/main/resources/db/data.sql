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
  (1, 'media 1', 'assets/img/products/products1.jpg'),
  (2, 'media 2', 'assets/img/products/products1.jpg'),
  (1, 'media 3', 'assets/img/products/products1.jpg');

-- Inserting data into the `category` table
INSERT INTO `category` (`name`)
VALUES 
  ('t-shirt'),
  ('v-neck'),
  ('tank top'),
  ('sweatshirt'),
    ('hoodie'),
  ('long sleeve');

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
  ('men'),
  ('women'),
  ('kids'),
  ('baby tee'),
  ('unisex');

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
  
  -- Inserting data into the manufacturer table
  INSERT INTO `manufacturer` (`name`) VALUES
  ('adidas'),
  ('allmade'),
  ('abercrombie & fitch'),
  ('bella + canvas'),
  ('fruit of the loom'),
  ('anvil');

-- Inserting data into the product table
INSERT INTO `product` (
    `SKU`, `product_media_id`, `category_id`, `name`, `description`, `manufacturer_id`, 
    `weight`, `unit_price`, `variant_id`, `barcode_id`, `is_active`, `units_in_stock`, 
    `date_created`, `last_updated`
) VALUES
    ('SKU001', 1, 2, 'Product 1', 'Description 1', 1, 1.5, 19.99, 2, 2, true, 10, NOW(), NOW()),
    ('SKU002', 2, 1, 'Product 2', 'Description 2', 2, 2.0, 29.99, 3, 1, true, 5, NOW(), NOW()),
    ('SKU003', 3, 1, 'Product 3', 'Description 3', 3, 12.8, 24.99, 2, 4, true, 15, NOW(), NOW()),
    ('SKU004', 1, 3, 'Product 4', 'Description 4', 4, 0.9, 14.99, 1, 3, true, 20, NOW(), NOW()),
    ('SKU005', 2, 2, 'Product 5', 'Description 5', 5, 2.5, 39.99, 4, 4, true, 8, NOW(), NOW()),
    ('SKU006', 3, 1, 'Product 6', 'Description 6', 6, 1.2, 9.99, 1, 4, true, 12, NOW(), NOW()),
    ('SKU007', 1, 3, 'Product 7', 'Description 7', 1, 5.7, 54.99, 5, 4, true, 18, NOW(), NOW()),
    ('SKU008', 1, 2, 'Product 8', 'Description 8', 2, 0.8, 12.99, 1, 4, true, 7, NOW(), NOW()),
    ('SKU009', 1, 1, 'Product 9', 'Description 9', 3, 3.1, 49.99, 4, 4, true, 9, NOW(), NOW()),
    ('SKU010', 2, 2, 'Product 10', 'Description 10', 4, 1.7, 24.99, 2, 1, true, 14, NOW(), NOW()),
    ('SKU011', 3, 3, 'Product 11', 'Description 11', 5, 2.3, 34.99, 3, 1, true, 6, NOW(), NOW()),
    ('SKU012', 3, 1, 'Product 12', 'Description 12', 6, 1.4, 17.99, 1, 1, true, 11, NOW(), NOW()),
    ('SKU013', 1, 2, 'Product 13', 'Description 13', 1, 1.9, 27.99, 2, 1, true, 13, NOW(), NOW()),
    ('SKU014', 2, 3, 'Product 14', 'Description 14', 2, 0.6, 9.99, 1, 3, true, 4, NOW(), NOW()),
    ('SKU015', 2, 1, 'Product 15', 'Description 15', 3, 2.8, 64.99, 6, 4, true, 21, NOW(), NOW()),
    ('SKU016', 2, 2, 'Product 16', 'Description 16', 4, 1.1, 14.99, 1, 1, true, 6, NOW(), NOW()),
    ('SKU017', 3, 3, 'Product 17', 'Description 17', 5, 3.5, 44.99, 4, 2, true, 11, NOW(), NOW()),
    ('SKU018', 1, 1, 'Product 18', 'Description 18', 6, 2.2, 19.99, 2, 2, true, 9, NOW(), NOW()),
    ('SKU019', 2, 2, 'Product 19', 'Description 19', 1, 0.7, 11.99, 1, 3, true, 5, NOW(), NOW()),
    ('SKU020', 2, 3, 'Product 20', 'Description 20', 2, 4.0, 79.99, 8, 3, true, 15, NOW(), NOW());



-- Insert statements for the product_and_product_media table
INSERT INTO `product_and_product_media` (`product_id`, `product_media_id`)
VALUES
  (1, 1),
  (2, 2),
  (3, 3),
  (4, 1),
  (5, 2),
  (6, 3),
  (7, 1),
  (8, 2),
  (9, 3),
  (10, 1),
  (11, 2),
  (12, 3),
  (13, 1),
  (14, 2),
  (15, 3),
  (16, 1),
  (17, 2),
  (18, 3),
  (19, 1),
  (20, 2);

-- Inserting data into the country table
INSERT INTO `country` VALUES 
(1,'US','United States');

-- Inserting data into the state table
INSERT INTO `state` VALUES 
(1,'Alabama',1),
(2,'Alaska',1),
(3,'Arizona',1),
(4,'Arkansas',1),
(5,'California',1),
(6,'Colorado',1),
(7,'Connecticut',1),
(8,'Delaware',1),
(9,'District Of Columbia',1),
(10,'Florida',1),
(11,'Georgia',1),
(12,'Hawaii',1),
(13,'Idaho',1),
(14,'Illinois',1),
(15,'Indiana',1),
(16,'Iowa',1),
(17,'Kansas',1),
(18,'Kentucky',1),
(19,'Louisiana',1),
(20,'Maine',1),
(21,'Maryland',1),
(22,'Massachusetts',1),
(23,'Michigan',1),
(24,'Minnesota',1),
(25,'Mississippi',1),
(26,'Missouri',1),
(27,'Montana',1),
(28,'Nebraska',1),
(29,'Nevada',1),
(30,'New Hampshire',1),
(31,'New Jersey',1),
(32,'New Mexico',1),
(33,'New York',1),
(34,'North Carolina',1),
(35,'North Dakota',1),
(36,'Ohio',1),
(37,'Oklahoma',1),
(38,'Oregon',1),
(39,'Pennsylvania',1),
(40,'Rhode Island',1),
(41,'South Carolina',1),
(42,'South Dakota',1),
(43,'Tennessee',1),
(44,'Texas',1),
(45,'Utah',1),
(46,'Vermont',1),
(47,'Virginia',1),
(48,'Washington',1),
(49,'West Virginia',1),
(50,'Wisconsin',1),
(51,'Wyoming',1);
