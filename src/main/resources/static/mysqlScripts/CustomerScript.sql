INSERT INTO customer (full_name, user_name, email, password, address) VALUES
('Alice Tan',    'alice.t',    'alice.tan@example.com',    'Password123!', '10 Orchard Road, Singapore 238852'),
('Bob Lim',      'boblim',     'bob.lim@example.com',      'qwerty!234',   '42 Clementi Ave, Singapore 120042'),
('Chloe Wong',   'chloe_w',    'chloe.wong@example.com',   'MyPass@2025',  '99 Bukit Timah Rd, Singapore 589876'),
('Daniel Ng',    'danielng',   'daniel.ng@example.com',    'Welcome1!',    '15 Marina Blvd, Singapore 018940'),
('Esha Patel',   'eshap',      'esha.patel@example.com',   'EshaPass!9',   '33 Serangoon Ave, Singapore 550333'),
('Farhan Ali',   'farhan_a',   'farhan.ali@example.com',   'Farhan#2025',  '8 Tampines St 11, Singapore 528667'),
('Grace Lee',    'gracelee',   'grace.lee@example.com',    'Grace_4444',   '72 HarbourFront Walk, Singapore 098595'),
('Hiroshi Sato', 'hiro_s',     'hiro.sato@example.com',    'SatoPass12',   '7 Keppel Rd, Singapore 089053'),
('Ivy Chen',     'ivychen',    'ivy.chen@example.com',     'Ivy!Pwd321',   '20 Holland Ave, Singapore 278775'),
('Jason Zhou',   'jasonz',     'jason.zhou@example.com',   'Jason2025$',    '1 Nanson Rd, Singapore 238909');

-- products
INSERT INTO product (product_name, description, discount, unit_price, category_id) VALUES
('Apple', 'Fresh red apple', 0.00, 1.50, NULL),
('Pizza', '12-inch cheese pizza', 0.10, 12.00, NULL),
('Coca Cola', 'Coca Cola 330ml can', 0.00, 1.20, NULL),
('Banana', 'Sweet ripe banana', 0.00, 0.80, NULL),
('Orange Juice', 'Fresh orange juice 1L', 0.05, 3.50, NULL),
('Bread', 'Whole wheat bread loaf', 0.00, 2.20, NULL),
('Milk', '1L fresh milk carton', 0.00, 2.50, NULL),
('Eggs', '12-pack organic eggs', 0.00, 4.20, NULL),
('Chocolate Bar', 'Dark chocolate 70% cocoa', 0.10, 2.80, NULL),
('Ice Cream', 'Vanilla ice cream tub 500ml', 0.15, 6.00, NULL),
('Laptop', '14-inch ultrabook laptop', 0.20, 899.00, NULL),
('Headphones', 'Wireless noise-cancelling headphones', 0.15, 129.00, NULL),
('T-Shirt', 'Cotton round-neck t-shirt', 0.05, 15.00, NULL),
('Shoes', 'Running shoes size 42', 0.10, 65.00, NULL),
('Backpack', 'Waterproof school backpack', 0.00, 39.90, NULL),
('Coffee', 'Ground coffee 250g pack', 0.00, 5.50, NULL),
('Tea', 'Green tea 20 bags', 0.00, 3.20, NULL),
('Toothpaste', 'Whitening toothpaste 120g', 0.00, 2.80, NULL),
('Shampoo', 'Anti-dandruff shampoo 400ml', 0.05, 7.50, NULL),
('Soap', 'Herbal bathing soap bar', 0.00, 1.00, NULL),
('Toilet Paper', '3-ply soft toilet tissue 10 rolls', 0.00, 6.80, NULL),
('Rice', '5kg fragrant white rice', 0.00, 9.90, NULL),
('Pasta', 'Italian spaghetti 500g pack', 0.00, 3.60, NULL),
('Tomato Sauce', 'Classic tomato cooking sauce 400g', 0.00, 2.50, NULL),
('Olive Oil', 'Extra virgin olive oil 1L', 0.10, 12.90, NULL),
('Butter', 'Salted butter 250g', 0.00, 4.00, NULL),
('Cheese', 'Mozzarella cheese 200g pack', 0.05, 5.80, NULL),
('Cereal', 'Honey oat cereal 500g box', 0.00, 4.50, NULL),
('Yogurt', 'Strawberry yogurt cup 150ml', 0.00, 1.50, NULL),
('Water Bottle', 'Mineral water 1.5L', 0.00, 1.00, NULL),
('Notebook', 'A5 lined notebook 200 pages', 0.00, 3.00, NULL),
('Pen', 'Black ink ballpoint pen', 0.00, 0.80, NULL),
('Pencil', 'HB graphite pencil', 0.00, 0.50, NULL),
('Eraser', 'Soft white eraser', 0.00, 0.60, NULL),
('Desk Lamp', 'Adjustable LED desk lamp', 0.10, 22.00, NULL),
('Mouse', 'Wireless optical mouse', 0.10, 18.00, NULL),
('Keyboard', 'Mechanical keyboard with RGB light', 0.15, 49.00, NULL),
('Monitor', '24-inch full HD monitor', 0.10, 159.00, NULL),
('USB Drive', '32GB USB 3.0 flash drive', 0.00, 9.50, NULL),
('Smartphone', '5G smartphone 128GB', 0.12, 699.00, NULL);


-- Orders for Alice (customerId = 1)
INSERT INTO orders (customer_id, purchase_date, unit_amount, status)
VALUES 
(1, '2025-09-20', 159.80, 'Completed'),
(1, '2025-10-01',  89.90, 'Completed');

-- Order Details for Alice's orders
INSERT INTO order_detail (order_id, product_id, quantity, is_refunded)
VALUES
(1, 1, 2, false),  -- 2x Product 1
(1, 2, 1, false),  -- 1x Product 2
(2, 3, 1, false);  -- 1x Product 3

-- Bob (customerId = 2)
INSERT INTO orders (customer_id, purchase_date, unit_amount, status)
VALUES (2, '2025-09-25', 249.70, 'Completed');

INSERT INTO order_detail (order_id, product_id, quantity, is_refunded)
VALUES
(3, 4, 2, false),
(3, 5, 1, false);

-- Chloe (customerId = 3)
INSERT INTO orders (customer_id, purchase_date, unit_amount, status)
VALUES (3, '2025-09-28', 59.90, 'Completed');

INSERT INTO order_detail (order_id, product_id, quantity, is_refunded)
VALUES (4, 2, 1, false);

-- Daniel (customerId = 4)
INSERT INTO orders (customer_id, purchase_date, unit_amount, status)
VALUES (4, '2025-10-02', 199.80, 'Pending');

INSERT INTO order_detail (order_id, product_id, quantity, is_refunded)
VALUES (5, 1, 4, false);

-- Esha (customerId = 5)
INSERT INTO orders (customer_id, purchase_date, unit_amount, status)
VALUES (5, '2025-09-29', 129.85, 'Completed');

INSERT INTO order_detail (order_id, product_id, quantity, is_refunded)
VALUES
(6, 2, 1, false),
(6, 5, 2, false);

-- Farhan (customerId = 6)
INSERT INTO orders (customer_id, purchase_date, unit_amount, status)
VALUES (6, '2025-09-27', 349.70, 'Shipped');

INSERT INTO order_detail (order_id, product_id, quantity, is_refunded)
VALUES
(7, 4, 3, false),
(7, 3, 1, false);

-- Grace (customerId = 7)
INSERT INTO orders (customer_id, purchase_date, unit_amount, status)
VALUES (7, '2025-10-03', 79.90, 'Completed');

INSERT INTO order_detail (order_id, product_id, quantity, is_refunded)
VALUES (8, 5, 2, false);

-- Hiroshi (customerId = 8)
INSERT INTO orders (customer_id, purchase_date, unit_amount, status)
VALUES (8, '2025-09-30', 99.90, 'Completed');

INSERT INTO order_detail (order_id, product_id, quantity, is_refunded)
VALUES (9, 1, 2, false);

-- Ivy (customerId = 9)
INSERT INTO orders (customer_id, purchase_date, unit_amount, status)
VALUES (9, '2025-10-01', 189.85, 'Completed');

INSERT INTO order_detail (order_id, product_id, quantity, is_refunded)
VALUES
(10, 2, 2, false),
(10, 3, 1, false);

-- Jason (customerId = 10)
INSERT INTO orders (customer_id, purchase_date, unit_amount, status)
VALUES (10, '2025-10-02', 149.90, 'Completed');

INSERT INTO order_detail (order_id, product_id, quantity, is_refunded)
VALUES
(11, 4, 1, false),
(11, 5, 1, false);

