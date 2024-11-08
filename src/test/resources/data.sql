-- -- Create Users Table
-- CREATE TABLE users (
--                        id VARCHAR(255) PRIMARY KEY,
--                        email VARCHAR(255) NOT NULL UNIQUE,
--                        user_name VARCHAR(255) NOT NULL
-- );
--
-- -- Create TemplatePost Table
-- CREATE TABLE template_post (
--                                id VARCHAR(255) PRIMARY KEY,
--                                created_date DATE NOT NULL,
--                                description VARCHAR(255),
--                                document_key VARCHAR(255) NOT NULL,
--                                title VARCHAR(255) NOT NULL,
--                                user_id VARCHAR(255) NOT NULL,
--                                FOREIGN KEY (user_id) REFERENCES users(id)
-- );
--
-- -- Create Category Table
-- CREATE TABLE category (
--                           id VARCHAR(255) PRIMARY KEY,
--                           name VARCHAR(255) NOT NULL
-- );
--
-- -- Create Post_Category Table (Join Table for Many-to-Many Relationship)
-- CREATE TABLE post_category (
--                                template_post_id VARCHAR(255) NOT NULL,
--                                category_id VARCHAR(255) NOT NULL,
--                                PRIMARY KEY (template_post_id, category_id),
--                                FOREIGN KEY (template_post_id) REFERENCES template_post(id),
--                                FOREIGN KEY (category_id) REFERENCES category(id)
-- );


-- Test Data for Users
INSERT INTO users (id, email, user_name) VALUES ('user-id-1', 'alice@test.com', 'Alice');
INSERT INTO users (id, email, user_name) VALUES ('user-id-2', 'bob@test.com', 'Bob');

-- Test Data for TemplatePosts
INSERT INTO template_post (id, created_date, description, document_key, title, user_id) VALUES
    ('tempPost-id-1', '2024-10-29', 'Description for template post 1', 'Key1', 'Template Post Title 1', 'user-id-1');
INSERT INTO template_post (id, created_date, description, document_key, title, user_id) VALUES
    ('tempPost-id-2', '2024-10-11', 'Description for template post 2', 'Key2', 'Template Post Title 2', 'user-id-1');
INSERT INTO template_post (id, created_date, description, document_key, title, user_id) VALUES
    ('tempPost-id-3', '2024-10-10', 'Description for template post 3', 'Key3', 'Template Post Title 3', 'user-id-2');
INSERT INTO template_post (id, created_date, description, document_key, title, user_id) VALUES
    ('tempPost-id-4', '2024-10-27', 'Description for template post 4', 'Key4', 'Template Post Title 4', 'user-id-1');

-- Test Data for Categories
INSERT INTO category (id, name) VALUES ('category-id-1', 'Technology');
INSERT INTO category (id, name) VALUES ('category-id-2', 'Health');
INSERT INTO category (id, name) VALUES ('category-id-3', 'Lifestyle');

-- Test Data for Post-Category Relationships
INSERT INTO post_category (template_post_id, category_id) VALUES ('tempPost-id-1', 'category-id-1');  -- Template Post 1: Technology
INSERT INTO post_category (template_post_id, category_id) VALUES ('tempPost-id-1', 'category-id-2');  -- Template Post 1: Health
INSERT INTO post_category (template_post_id, category_id) VALUES ('tempPost-id-2', 'category-id-2');  -- Template Post 2: Health
INSERT INTO post_category (template_post_id, category_id) VALUES ('tempPost-id-2', 'category-id-3');  -- Template Post 2: Lifestyle
INSERT INTO post_category (template_post_id, category_id) VALUES ('tempPost-id-3', 'category-id-1');  -- Template Post 3: Technology
INSERT INTO post_category (template_post_id, category_id) VALUES ('tempPost-id-3', 'category-id-3');  -- Template Post 3: Lifestyle
INSERT INTO post_category (template_post_id, category_id) VALUES ('tempPost-id-4', 'category-id-1');  -- Template Post 4: Technology

