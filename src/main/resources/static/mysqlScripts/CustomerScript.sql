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

