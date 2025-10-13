--
-- * mySQL script
-- * Authors: Glenn Min, Claude
-- * Date: 2025-10-13
-- * Last Modified by: -
-- * New Updates: -
-- * Last Modified: 2025-10-13
--

INSERT INTO category (name) VALUES
                                ('Stationery'),
                                ('Electronics'),
                                ('Snacks'),
                                ('Beverages'),
                                ('Books');

-- Optional Verification
-- SELECT * FROM category;

INSERT INTO product
(product_name, description, image_url, discount, unit_price, average_rating, category_id)
VALUES
-- ==============================
-- Category 1: Stationery (ID 1)
-- ==============================
('Premium Gel Pen Set (Black)',
 'Smooth writing gel pens with quick-dry ink, perfect for notes and journaling.',
 'https://images.unsplash.com/photo-1518674660708-0e2c0473e68e?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=2048', 0.0, 12.50, 4.5, 1),
('Minimalist Desk Planner',
 'Undated weekly planner pad for organizing tasks and appointments.',
 'https://images.unsplash.com/photo-1513128034602-7814ccaddd4e?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=3024', 0.0, 18.00, 4.1, 1),
('Luxury Fountain Pen',
 'Elegant refillable fountain pen with gold nib, perfect for gifting.',
 'https://images.unsplash.com/photo-1637725736802-f95edc89b4ac?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=3134', 0.05, 35.00, 4.7, 1),
('A5 Hardcover Notebook',
 'Durable hardcover notebook with dotted pages for journaling or sketching.',
 'https://images.unsplash.com/photo-1599081595921-238c45c87bda?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=2940', 0.0, 9.90, 4.4, 1),
('Sticky Note Set',
 'Pack of pastel sticky notes for reminders and quick notes.',
 'https://images.unsplash.com/photo-1591462391971-9ffc57b382b9?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=2070', 0.0, 5.20, 4.1, 1),
('Mechanical Pencil (0.5mm)',
 'Smooth-click pencil with ergonomic grip and spare leads included.',
 'https://images.unsplash.com/photo-1713566770925-becef7cbdd92?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=2940', 0.10, 6.50, 4.3, 1),
('Highlighter Pack (6 colors)',
 'Smudge-free, quick-drying highlighters in assorted pastel shades.',
 'https://images.unsplash.com/photo-1580569214296-5cf2bffc5ccd?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=2070', 0.0, 8.80, 4.6, 1),
('Desk Organizer Tray',
 'Compact bamboo desk tray for pens, clips, and small stationery.',
 'https://images.unsplash.com/photo-1644463589256-02679b9c0767?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=2622', 0.0, 14.50, 4.5, 1),
('Binder Clip Set (Assorted)',
 'Sturdy metal clips in 3 sizes for documents and notes.',
 'https://images.unsplash.com/photo-1592967483436-6117ba22fae1?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=2070', 0.0, 4.20, 4.2, 1),

-- ==============================
-- Category 2: Electronics (ID 2)
-- ==============================
('Portable Bluetooth Speaker',
 'Compact waterproof speaker with 12 hours of playtime and deep bass.',
 'https://images.unsplash.com/photo-1589256469067-ea99122bbdc4?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=3174', 0.10, 49.99, 4.2, 2),
('Wireless Mouse',
 'Ergonomic mouse with silent clicks and rechargeable battery.',
 'https://images.unsplash.com/photo-1739742473235-34a7bd9b8f87?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=3087', 0.0, 25.90, 4.5, 2),
('USB-C Hub (6-in-1)',
 'Includes HDMI, SD card, and 3 USB ports. Compact aluminum body.',
 'https://images.unsplash.com/photo-1616578273577-5d54546f4dec?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=3087', 0.10, 34.99, 4.4, 2),
('Noise Cancelling Headphones',
 'Over-ear Bluetooth headphones with active noise cancellation.',
 'https://images.unsplash.com/photo-1612478120679-5b7412e15f84?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=2940', 0.15, 129.00, 4.8, 2),
('Smart Power Strip',
 'Wi-Fi enabled power strip with surge protection and voice control.',
 'https://images.unsplash.com/photo-1726748839470-d9b0506ce844?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=3132', 0.05, 45.00, 4.3, 2),
('Mini Projector',
 'Portable home projector supporting 1080p video playback.',
 'https://images.unsplash.com/photo-1535016120720-40c646be5580?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=2940', 0.20, 99.99, 4.6, 2),
('Wireless Keyboard',
 'Compact Bluetooth keyboard with multi-device pairing.',
 'https://images.unsplash.com/photo-1694405145070-e58cc29771fa?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=2940', 0.0, 29.90, 4.2, 2),
('LED Desk Lamp',
 'Adjustable brightness lamp with USB charging port.',
 'https://images.unsplash.com/photo-1571406487954-dc11b0c0767d?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=2048', 0.0, 22.50, 4.7, 2),

-- ==============================
-- Category 3: Snacks (ID 3)
-- ==============================
('Gourmet Trail Mix',
 'A blend of almonds, cashews, dried cranberries, and dark chocolate chips.',
 'https://images.unsplash.com/photo-1615485737651-580c9159c89a?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=2962', 0.05, 7.99, 4.8, 3),
('Organic Granola Bars (6 Pack)',
 'Made with oats, honey, almonds, and cranberries. 100% natural.',
 'https://images.unsplash.com/photo-1621057621391-7ed446a24b41?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=2914', 0.05, 8.50, 4.5, 3),
('Seaweed Crisps',
 'Crispy seaweed chips lightly salted with sesame oil flavor.',
 'https://images.unsplash.com/photo-1710963049272-eac1c7964e1c?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=2942', 0.0, 4.99, 4.3, 3),
('Potato Chips (Salted)',
 'Classic thin-cut potato chips with sea salt. 150g pack.',
 'https://images.unsplash.com/photo-1613462847848-f65a8b231bb5?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=3087', 0.10, 3.50, 4.0, 3),
('Chocolate Chip Cookies',
 'Homemade-style cookies baked with Belgian chocolate.',
 'https://images.unsplash.com/photo-1499636136210-6f4ee915583e?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=2678', 0.0, 6.50, 4.8, 3),
('Wasabi Peas',
 'Crunchy green peas coated in spicy wasabi seasoning.',
 'https://images.unsplash.com/photo-1575262599410-837a72005862?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=2958', 0.0, 4.20, 4.1, 3),
('Rice Crackers Mix',
 'Assorted Japanese rice crackers with soy and seaweed flavor.',
 'https://images.unsplash.com/photo-1648788767168-aa2df5105037?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=2940', 0.0, 5.80, 4.4, 3),
('Fruit Gummies',
 'Chewy fruit gummies made with natural juice concentrate.',
 'https://images.unsplash.com/photo-1648139347040-857f024f8da4?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=3087', 0.0, 3.99, 4.2, 3),

-- ==============================
-- Category 4: Beverages (ID 4)
-- ==============================
('Artisanal Cold Brew Coffee',
 'Smooth, low-acidity coffee concentrate, ready to mix. 32oz bottle.',
 'https://images.unsplash.com/photo-1561641377-f7456d23aa9b?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=2559', 0.0, 15.00, 4.6, 4),
('Sparkling Mineral Water (6 Pack)',
 'Refreshing carbonated water sourced from natural springs.',
 'https://images.unsplash.com/photo-1591719482505-fcde0bdd0ed1?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=2940', 0.0, 8.90, 4.3, 4),
('Matcha Green Tea Latte',
 'Premium matcha blended with oat milk for a smooth finish.',
 'https://images.unsplash.com/photo-1565117661210-fd54898de423?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=2000', 0.05, 6.50, 4.7, 4),
('Lemon Iced Tea Bottle',
 'Cool and tangy lemon tea made from real black tea leaves.',
 'https://images.unsplash.com/photo-1739138056344-3c852f4efc28?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=3135', 0.0, 4.80, 4.2, 4),
('Coconut Water (Pure)',
 '100% natural coconut water with no added sugar.',
 'https://images.unsplash.com/photo-1588413336009-1f4219f2d5dd?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=3087', 0.0, 5.20, 4.4, 4),
('Dark Roast Coffee Beans (1kg)',
 'Freshly roasted Arabica beans with rich flavor and aroma.',
 'https://images.unsplash.com/photo-1549403610-177341eb0f67?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=3164', 0.10, 24.99, 4.8, 4),
('Herbal Chamomile Tea (20 Bags)',
 'Caffeine-free chamomile tea for calm and relaxation.',
 'https://images.unsplash.com/photo-1630543378528-10043e324452?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=2960', 0.0, 7.50, 4.5, 4),
('Energy Drink (250ml)',
 'Lightly carbonated drink with vitamins and natural caffeine.',
 'https://images.unsplash.com/photo-1560689189-65b6ed6228e7?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=2942', 0.15, 2.80, 4.1, 4),

-- ==============================
-- Category 5: Books (ID 5)
-- ==============================
('Harry Potter and the Cursed Child',
 'Did Voldemort have a child? Find out now from the newly released book.',
 'https://images.unsplash.com/photo-1598153346810-860daa814c4b?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=3132', 0.15, 22.99, 4.4, 5),
('The Art of Minimalism',
 'A practical guide to decluttering and living intentionally.',
 'https://images.unsplash.com/photo-1536662788222-6927ce05daea?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=3087', 0.10, 18.99, 4.5, 5),
('Atomic Habits',
 'James Clear’s breakthrough guide to building good habits.',
 'https://images.unsplash.com/photo-1613520761471-8d5f28e343c0?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=2070', 0.15, 21.50, 4.9, 5),
('The Psychology of Money',
 'Timeless lessons on wealth, greed, and happiness by Morgan Housel.',
 'https://images.unsplash.com/photo-1592496431122-2349e0fbc666?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=2112', 0.10, 19.90, 4.8, 5),
('The Midnight Library',
 'A novel exploring life choices and infinite possibilities by Matt Haig.',
 'https://images.unsplash.com/photo-1711896099542-53253887088a?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=987', 0.05, 16.80, 4.6, 5),
('Thinking, Fast and Slow',
 'Daniel Kahneman’s classic on human psychology and decision-making.',
 'https://images.unsplash.com/photo-1558025623-2aafbebe8daf?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=2070', 0.0, 23.00, 4.8, 5),
('Deep Work',
 'Cal Newport’s guide to focused success in a distracted world.',
 'https://images.unsplash.com/photo-1621948415096-db7be1f040bd?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=2940', 0.0, 20.00, 4.7, 5);

-- ============================================================
-- TOTAL PRODUCTS INSERTED: 41
-- ============================================================

-- Optional Verification
-- SELECT * FROM product;