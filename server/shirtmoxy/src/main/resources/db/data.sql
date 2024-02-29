### TEST DATA ###

-- Inserting data into the `product_type` table
INSERT INTO `product_type` (`id`, `name`)
VALUES (1, 'clothing');

-- Inserting data into the `product_media` table
INSERT INTO `product_media` (`name`, `url`)
VALUES 
  ('media 1', 'assets/img/products/products1.jpg'),
  ('media 2', 'assets/img/products/products1.jpg'),
  ('media 3', 'assets/img/products/products1.jpg');

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
INSERT INTO `color` (`name`, `red`, `green`, `blue`)
VALUES 
   ('beige', 249, 238, 214),
   ('black', 0, 17, 26),
   ('blue', 0, 28, 245),
   ('brown', 130, 72, 32),
   ('gold', 255, 254, 84),
   ('gray', 128, 128, 128),
   ('green', 53, 125, 34),
   ('orange', 241, 134, 51),
   ('pink', 246, 195, 203),
   ('purple', 117, 31, 192),
   ('red', 236, 51, 35),
   ('white', 255, 255, 255),
   ('yellow', 255, 254, 84);


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
INSERT INTO `material` (`type`)
VALUES 
  ('Cotton'),
  ('Polyester'),
  ('Nylon');

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
  ('abercrombie and fitch'),
  ('bella canvas'),
  ('fruit of the loom'),
  ('anvil');

-- Inserting 20 dummy products into the `product` table
INSERT INTO `product` (
    `SKU`,
    `name`,
    `description`,
    `unit_price`,
    `weight`,
    `units_in_stock`,
    `is_active`,
    `date_created`,
    `last_updated`,
    `product_media_id`,
    `product_type_id`,
    `category_id`,
    `manufacturer_id`,
    `gender_id`,
    `color_id`,
    `size_id`,
    `material_id`,
    `barcode_id`
)
VALUES
	-- ATTENTION! CREATE AN ENTRY IN product_and_product_media table for media ids too! This is temporary & will be fixed soon!!!!!!!!!!!!!!!!!!!!!!
    ('SKU001', 'Product 1', 'Lorem ipsum dolor text', 7.99, 1.0, 0, TRUE, NOW(), NOW(), 1, 1, 1, 1, 1, 1, 1, 1, 1),
    ('SKU002', 'Product 2', 'Lorem ipsum dolor text', 17.99, 2.0, 10, TRUE, NOW(), NOW(), 2, 1, 2, 2, 2, 2, 2, 2, 2),
    ('SKU003', 'Product 3', 'Lorem ipsum dolor text', 29.99, 3.0, 20, TRUE, NOW(), NOW(), 3, 1, 3, 3, 3, 3, 3, 3, 3),
    ('SKU004', 'Product 4', 'Lorem ipsum dolor text', 39.99, 4.0, 30, TRUE, NOW(), NOW(), 1, 1, 4, 4, 4, 4, 4, 1, 4),
    ('SKU005', 'Product 5', 'Lorem ipsum dolor text', 49.99, 5.0, 40, TRUE, NOW(), NOW(), 2, 1, 5, 5, 5, 5, 5, 1, 4),
    ('SKU006', 'Product 6', 'Lorem ipsum dolor text', 29.99, 3.0, 50, TRUE, NOW(), NOW(), 3, 1, 6, 6, 1, 6, 1, 1, 2),
    ('SKU007', 'Product 7', 'Lorem ipsum dolor text', 19.99, 2.5, 60, TRUE, NOW(), NOW(), 1, 1, 1, 1, 2, 2, 2, 2, 1),
    ('SKU008', 'Product 8', 'Lorem ipsum dolor text', 12.99, 1.5, 70, TRUE, NOW(), NOW(), 2, 1, 2, 2, 3, 3, 3, 2, 4),
    ('SKU009', 'Product 9', 'Lorem ipsum dolor text', 24.99, 4.5, 80, TRUE, NOW(), NOW(), 3, 1, 3, 3, 4, 4, 4, 2, 3),
    ('SKU010', 'Product 10', 'Lorem ipsum dolor text', 34.99, 3.2, 90, TRUE, NOW(), NOW(), 1, 1, 4, 4, 5, 5, 5, 1, 2),
    ('SKU011', 'Product 11', 'Lorem ipsum dolor text', 9.99, 1.8, 0, TRUE, NOW(), NOW(), 2, 1, 5, 5, 1, 6, 1, 3, 1),
    ('SKU012', 'Product 12', 'Lorem ipsum dolor text', 22.99, 2.7, 10, TRUE, NOW(), NOW(), 3, 1, 1, 6, 2, 2, 2, 2, 4),
    ('SKU013', 'Product 13', 'Lorem ipsum dolor text', 42.99, 4.2, 20, TRUE, NOW(), NOW(), 1, 1, 2, 1, 3, 3, 3, 3, 3),
    ('SKU014', 'Product 14', 'Lorem ipsum dolor text', 31.99, 3.9, 30, TRUE, NOW(), NOW(), 2, 1, 3, 2, 4, 4, 4, 1, 2),
    ('SKU015', 'Product 15', 'Lorem ipsum dolor text', 15.99, 2.3, 40, TRUE, NOW(), NOW(), 3, 1, 4, 3, 5, 5, 5, 2, 1),
    ('SKU016', 'Product 16', 'Lorem ipsum dolor text', 27.99, 4.0, 50, TRUE, NOW(), NOW(), 1, 1, 5, 4, 1, 6, 1, 3, 4),
    ('SKU017', 'Product 17', 'Lorem ipsum dolor text', 8.99, 1.2, 60, TRUE, NOW(), NOW(), 2, 1, 1, 5, 2, 2, 2, 2, 3),
    ('SKU018', 'Product 18', 'Lorem ipsum dolor text', 36.99, 3.5, 70, TRUE, NOW(), NOW(), 3, 1, 2, 6, 3, 3, 3, 3, 2),
    ('SKU019', 'Product 19', 'Lorem ipsum dolor text', 19.99, 2.1, 80, TRUE, NOW(), NOW(), 1, 1, 3, 1, 4, 4, 4, 2, 1),
    ('SKU020', 'Product 20', 'Lorem ipsum dolor text', 19.99, 2.1, 80, TRUE, NOW(), NOW(), 1, 1, 3, 1, 4, 4, 4, 2, 1),
    ('SKU021', 'Adult Topflight Heather Performance T-Shirt', 'Lorem ipsum dolor text', 19.99, 2.1, 80, TRUE, NOW(), NOW(), 1, 1, 3, 1, 4, 4, 1, 2, 1),
    ('SKU022', 'Adult Topflight Heather Performance T-Shirt', 'Lorem ipsum dolor text', 19.99, 2.1, 0, TRUE, NOW(), NOW(), 2, 1, 3, 1, 4, 4, 2, 2, 1),
    ('SKU023', 'Adult Topflight Heather Performance T-Shirt', 'Lorem ipsum dolor text', 19.99, 2.1, 21, TRUE, NOW(), NOW(), 2, 1, 3, 1, 4, 4, 3, 2, 1),
    ('SKU024', 'Adult Topflight Heather Performance T-Shirt', 'Lorem ipsum dolor text', 19.99, 2.1, 10, TRUE, NOW(), NOW(), 2, 1, 3, 1, 4, 4, 4, 2, 1),
    ('SKU025', 'Adult Topflight Heather Performance T-Shirt', 'Lorem ipsum dolor text', 19.99, 2.1, 5, TRUE, NOW(), NOW(), 2, 1, 3, 1, 4, 4, 5, 2, 1),
    ('SKU026', 'Adult Topflight Heather Performance T-Shirt', 'Lorem ipsum dolor text', 19.99, 2.1, 4, TRUE, NOW(), NOW(), 2, 1, 3, 1, 4, 4, 6, 2, 1),
    ('SKU027', 'Adult Topflight Heather Performance T-Shirt', 'Lorem ipsum dolor text', 19.99, 2.1, 0, TRUE, NOW(), NOW(), 2, 1, 3, 1, 4, 4, 7, 2, 1),
    
    ('SKU028', 'Adult Topflight Heather Performance T-Shirt', 'Lorem ipsum dolor text', 19.99, 2.1, 80, TRUE, NOW(), NOW(), 2, 1, 3, 1, 4, 3, 1, 2, 1),
    ('SKU029', 'Adult Topflight Heather Performance T-Shirt', 'Lorem ipsum dolor text', 19.99, 2.1, 0, TRUE, NOW(), NOW(), 2, 1, 3, 1, 4, 3, 2, 2, 1),
    ('SKU030', 'Adult Topflight Heather Performance T-Shirt', 'Lorem ipsum dolor text', 19.99, 2.1, 21, TRUE, NOW(), NOW(), 2, 1, 3, 1, 4, 3, 3, 2, 1),
    ('SKU031', 'Adult Topflight Heather Performance T-Shirt', 'Lorem ipsum dolor text', 19.99, 2.1, 10, TRUE, NOW(), NOW(), 2, 1, 3, 1, 4, 3, 4, 2, 1),
    ('SKU032', 'Adult Topflight Heather Performance T-Shirt', 'Lorem ipsum dolor text', 19.99, 2.1, 5, TRUE, NOW(), NOW(), 2, 1, 3, 1, 4, 3, 5, 2, 1),
    ('SKU033', 'Adult Topflight Heather Performance T-Shirt', 'Lorem ipsum dolor text', 19.99, 2.1, 4, TRUE, NOW(), NOW(), 2, 1, 3, 1, 4, 3, 6, 2, 1),
    ('SKU034', 'Adult Topflight Heather Performance T-Shirt', 'Lorem ipsum dolor text', 19.99, 2.1, 0, TRUE, NOW(), NOW(), 2, 1, 3, 1, 4, 3, 7, 2, 1),
    
    ('SKU035', 'Product 21', 'Lorem ipsum dolor text', 19.99, 2.1, 2, TRUE, NOW(), NOW(), 1, 1, 3, 1, 4, 4, 1, 2, 1),
    ('SKU036', 'Product 21', 'Lorem ipsum dolor text', 19.99, 2.1, 8, TRUE, NOW(), NOW(), 1, 1, 3, 1, 4, 6, 2, 2, 1);

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
  (20, 1),
  (21, 1),
  (22, 2),
  (23, 2),
  (24, 2),
  (25, 2),
  (26, 2),
  (27, 2),
  (28, 2),
  (29, 2),
  (30, 2),
  (31, 2),
  (32, 2),
  (33, 2),
  (34, 2),
  (35, 1),
  (36, 1);

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