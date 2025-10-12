-- Step 1: Insert the specified product categories
-- Assuming 'category_id' is an auto-incrementing primary key.

-- Category IDs are assumed to be 1 to 5 after these insertions.
INSERT INTO category (name) VALUES ('Stationery');
INSERT INTO category (name) VALUES ('Electronics');
INSERT INTO category (name) VALUES ('Snacks');
INSERT INTO category (name) VALUES ('Beverages');
INSERT INTO category (name) VALUES ('Books');

-- Verification check (Optional)
SELECT * FROM category;

-- Step 2: Insert sample products (referencing Category IDs 1, 2, 3, 4, 5)

INSERT INTO product (product_name, description, image_url, discount, unit_price, average_rating, category_id) VALUES
(
    -- Category: Stationery (ID 1)
    'Premium Gel Pen Set (Black)',
    'Smooth writing gel pens with quick-dry ink, perfect for notes and journaling.',
    'https://images.unsplash.com/photo-1518674660708-0e2c0473e68e?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=2048',
    0.0,
    12.50,
    4.5,
    1
),
(
    -- Category: Electronics (ID 2)
    'Portable Bluetooth Speaker',
    'Compact waterproof speaker with 12 hours of playtime and deep bass.',
    'https://images.unsplash.com/photo-1589256469067-ea99122bbdc4?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=3174',
    0.10,
    49.99,
    4.2,
    2
),
(
    -- Category: Snacks (ID 3)
    'Gourmet Trail Mix',
    'A blend of almonds, cashews, dried cranberries, and dark chocolate chips.',
    'https://images.unsplash.com/photo-1615485737651-580c9159c89a?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=2962',
    0.05,
    7.99,
    4.8,
    3
),
(
    -- Category: Beverages (ID 4)
    'Artisanal Cold Brew Coffee',
    'Smooth, low-acidity coffee concentrate, ready to mix. 32oz bottle.',
    'https://images.unsplash.com/photo-1561641377-f7456d23aa9b?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=2559',
    0.0,
    15.00,
    4.6,
    4
),
(
    -- Category: Books (ID 5)
    'Harry Potter and the Cursed Child',
    'Did Voldemort have a child? Find out now from the newly released book.',
    'https://images.unsplash.com/photo-1598153346810-860daa814c4b?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=3132',
    0.15,
    22.99,
    4.4,
    5
),
(
    -- Category: Stationery (ID 1)
    'Minimalist Desk Planner',
    'Undated weekly planner pad for organizing tasks and appointments.',
    'https://images.unsplash.com/photo-1513128034602-7814ccaddd4e?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=3024',
    0.0,
    18.00,
    4.1,
    1
);
