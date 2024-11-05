-- Test Data for Users
INSERT INTO users (id, email, user_name) VALUES
('user-id-1', 'alice@test.com', 'Alice'),
('user-id-2', 'bob@test.com', 'Bob');

-- Test Data for TemplatePosts
INSERT INTO template_post (id, created_date, description, document_key, title, user_id) VALUES
('tempPost-id-1', '2024-10-29', 'Description for template post 1', 'Key1', 'Template Post Title 1', 'user-id-1'),
('tempPost-id-2', '2024-10-11', 'Description for template post 2', 'Key2', 'Template Post Title 2', 'user-id-1'),
('tempPost-id-3', '2024-10-10', 'Description for template post 3', 'Key3', 'Template Post Title 3', 'user-id-2'),
('tempPost-id-4', '2024-10-27', 'Description for template post 4', 'Key4', 'Template Post Title 4', 'user-id-1');

-- Test Data for Categories
INSERT INTO category (id, name) VALUES
('category-id-1', 'Technology'),
('category-id-2', 'Health'),
('category-id-3', 'Lifestyle');

-- Test Data for Post-Category Relationships
INSERT INTO post_category (template_post_id, category_id) VALUES
('tempPost-id-1', 'category-id-1'),  -- Template Post 1: Technology
('tempPost-id-1', 'category-id-2'),  -- Template Post 1: Health
('tempPost-id-2', 'category-id-2'),  -- Template Post 2: Health
('tempPost-id-2', 'category-id-3'),  -- Template Post 2: Lifestyle
('tempPost-id-3', 'category-id-1'),  -- Template Post 3: Technology
('tempPost-id-3', 'category-id-3'),  -- Template Post 3: Lifestyle
('tempPost-id-4', 'category-id-1');  -- Template Post 4: Technology
