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
INSERT INTO users (id, email, user_name) VALUES ('user-id-3', 'charlie@test.com', 'Charlie');
INSERT INTO users (id, email, user_name) VALUES ('user-id-4', 'diana@test.com', 'Diana');
INSERT INTO users (id, email, user_name) VALUES ('user-id-5', 'eve@test.com', 'Eve');

-- Test Data for TemplatePosts
INSERT INTO template_post (id, created_date, description, file_key, title, user_id) VALUES
    ('tempPost-id-1', '2024-10-29', 'Description for template post 1', 'Key1', 'Template Post Title 1', 'user-id-1');
INSERT INTO template_post (id, created_date, description, file_key, title, user_id) VALUES
    ('tempPost-id-2', '2024-10-11', 'Description for template post 2', 'Key2', 'Template Post Title 2', 'user-id-1');
INSERT INTO template_post (id, created_date, description, file_key, title, user_id) VALUES
    ('tempPost-id-3', '2024-10-10', 'Description for template post 3', 'Key3', 'Template Post Title 3', 'user-id-2');
INSERT INTO template_post (id, created_date, description, file_key, title, user_id) VALUES
    ('tempPost-id-4', '2024-10-27', 'Description for template post 4', 'Key4', 'Template Post Title 4', 'user-id-1');
INSERT INTO template_post (id, created_date, description, file_key, title, user_id) VALUES
    ('tempPost-id-5', '2024-11-15', 'Description for template post 5', 'Key5', 'Template Post Title 5', 'user-id-3');
INSERT INTO template_post (id, created_date, description, file_key, title, user_id) VALUES
    ('tempPost-id-6', '2024-12-05', 'Description for template post 6', 'Key6', 'Template Post Title 6', 'user-id-4');
INSERT INTO template_post (id, created_date, description, file_key, title, user_id) VALUES
    ('tempPost-id-7', '2024-12-01', 'Description for template post 7', 'Key7', 'Template Post Title 7', 'user-id-5');

-- Test Data for Categories
INSERT INTO category (id, name) VALUES ('category-id-1', 'Technology');
INSERT INTO category (id, name) VALUES ('category-id-2', 'Health');
INSERT INTO category (id, name) VALUES ('category-id-3', 'Lifestyle');
INSERT INTO category (id, name) VALUES ('category-id-4', 'Education');
INSERT INTO category (id, name) VALUES ('category-id-5', 'Finance');

-- Test Data for Post-Category Relationships
INSERT INTO post_category (template_post_id, category_id) VALUES ('tempPost-id-1', 'category-id-1');  -- Template Post 1: Technology
INSERT INTO post_category (template_post_id, category_id) VALUES ('tempPost-id-1', 'category-id-2');  -- Template Post 1: Health
INSERT INTO post_category (template_post_id, category_id) VALUES ('tempPost-id-2', 'category-id-2');  -- Template Post 2: Health
INSERT INTO post_category (template_post_id, category_id) VALUES ('tempPost-id-2', 'category-id-3');  -- Template Post 2: Lifestyle
INSERT INTO post_category (template_post_id, category_id) VALUES ('tempPost-id-3', 'category-id-1');  -- Template Post 3: Technology
INSERT INTO post_category (template_post_id, category_id) VALUES ('tempPost-id-3', 'category-id-3');  -- Template Post 3: Lifestyle
INSERT INTO post_category (template_post_id, category_id) VALUES ('tempPost-id-4', 'category-id-1');  -- Template Post 4: Technology
INSERT INTO post_category (template_post_id, category_id) VALUES ('tempPost-id-5', 'category-id-4');  -- Template Post 5: Education
INSERT INTO post_category (template_post_id, category_id) VALUES ('tempPost-id-5', 'category-id-2');  -- Template Post 5: Health
INSERT INTO post_category (template_post_id, category_id) VALUES ('tempPost-id-6', 'category-id-5');  -- Template Post 6: Finance
INSERT INTO post_category (template_post_id, category_id) VALUES ('tempPost-id-7', 'category-id-4');  -- Template Post 7: Education
INSERT INTO post_category (template_post_id, category_id) VALUES ('tempPost-id-7', 'category-id-5');  -- Template Post 7: Finance

-- Test Data for Bookmark Lists
INSERT INTO bookmark_list (id, title, user_id) VALUES ('bookmark-id-1', 'My Favorite Posts', 'user-id-1');
INSERT INTO bookmark_list (id, title, user_id) VALUES ('bookmark-id-2', 'Health Related Posts', 'user-id-2');
INSERT INTO bookmark_list (id, title, user_id) VALUES ('bookmark-id-3', 'Technology Posts', 'user-id-1');
INSERT INTO bookmark_list (id, title, user_id) VALUES ('bookmark-id-4', 'Finance Posts', 'user-id-3');
INSERT INTO bookmark_list (id, title, user_id) VALUES ('bookmark-id-5', 'Lifestyle Posts', 'user-id-4');
INSERT INTO bookmark_list (id, title, user_id) VALUES ('bookmark-id-6', 'Educational Posts', 'user-id-5');

-- Test Data for Bookmark List - Template Post Relationships
INSERT INTO bookmark_list_template_post (bookmark_list_id, template_post_id) VALUES ('bookmark-id-1', 'tempPost-id-1');  -- Bookmark List 1: Template Post 1
INSERT INTO bookmark_list_template_post (bookmark_list_id, template_post_id) VALUES ('bookmark-id-1', 'tempPost-id-2');  -- Bookmark List 1: Template Post 2
INSERT INTO bookmark_list_template_post (bookmark_list_id, template_post_id) VALUES ('bookmark-id-2', 'tempPost-id-2');  -- Bookmark List 2: Template Post 2
INSERT INTO bookmark_list_template_post (bookmark_list_id, template_post_id) VALUES ('bookmark-id-2', 'tempPost-id-3');  -- Bookmark List 2: Template Post 3
INSERT INTO bookmark_list_template_post (bookmark_list_id, template_post_id) VALUES ('bookmark-id-3', 'tempPost-id-1');  -- Bookmark List 3: Template Post 1
INSERT INTO bookmark_list_template_post (bookmark_list_id, template_post_id) VALUES ('bookmark-id-3', 'tempPost-id-4');  -- Bookmark List 3: Template Post 4
INSERT INTO bookmark_list_template_post (bookmark_list_id, template_post_id) VALUES ('bookmark-id-4', 'tempPost-id-6');  -- Bookmark List 4: Template Post 6
INSERT INTO bookmark_list_template_post (bookmark_list_id, template_post_id) VALUES ('bookmark-id-5', 'tempPost-id-7');  -- Bookmark List 5: Template Post 7
INSERT INTO bookmark_list_template_post (bookmark_list_id, template_post_id) VALUES ('bookmark-id-6', 'tempPost-id-5');  -- Bookmark List 6: Template Post 5

-- Test Data for Reviews Ordered by Template Post

-- Reviews for Template Post 1
INSERT INTO review (id, content, rating, template_post_id, user_id) VALUES ('review-id-1', 'Great template post with detailed information. I was able to apply it directly to my project and saved a lot of time!', 5, 'tempPost-id-1', 'user-id-1');
INSERT INTO review (id, content, rating, template_post_id, user_id) VALUES ('review-id-5', 'This post was okay, but I found some parts to be outdated. Needs an update with newer practices.', 3, 'tempPost-id-1', 'user-id-2');

-- Reviews for Template Post 2
INSERT INTO review (id, content, rating, template_post_id, user_id) VALUES ('review-id-2', 'Informative but could use more examples to clarify the concepts. Still a useful resource!', 4, 'tempPost-id-2', 'user-id-2');
INSERT INTO review (id, content, rating, template_post_id, user_id) VALUES ('review-id-7', 'The post was decent, but there were a few typos that made it a bit difficult to follow. A little more polish would be great.', 4, 'tempPost-id-2', 'user-id-1');

-- Reviews for Template Post 3
INSERT INTO review (id, content, rating, template_post_id, user_id) VALUES ('review-id-6', 'I absolutely loved this template! The structure was clear and made it easy to implement. Would love to see more like this.', 5, 'tempPost-id-3', 'user-id-2');
INSERT INTO review (id, content, rating, template_post_id, user_id) VALUES ('review-id-3', 'Helpful, but a little too basic for my needs. Would be great if it included more advanced use cases.', 3, 'tempPost-id-3', 'user-id-1');

-- Reviews for Template Post 4
INSERT INTO review (id, content, rating, template_post_id, user_id) VALUES ('review-id-4', 'Excellent content! Very detailed and useful. I would highly recommend this template to others.', 5, 'tempPost-id-4', 'user-id-2');
INSERT INTO review (id, content, rating, template_post_id, user_id) VALUES ('review-id-8', 'Not what I expected. The post is too broad and lacks the depth I was hoping for. Would not recommend for advanced users.', 2, 'tempPost-id-4', 'user-id-1');

-- Reviews for Template Post 5
INSERT INTO review (id, content, rating, template_post_id, user_id) VALUES ('review-id-9', 'Very useful post, helped me a lot with my project on financial planning.', 5, 'tempPost-id-5', 'user-id-3');
INSERT INTO review (id, content, rating, template_post_id, user_id) VALUES ('review-id-10', 'The examples were not relevant to my situation, but it was still informative.', 3, 'tempPost-id-5', 'user-id-4');

-- Reviews for Template Post 6
INSERT INTO review (id, content, rating, template_post_id, user_id) VALUES ('review-id-11', 'Great insights into the world of finance, but could benefit from more specific case studies.', 4, 'tempPost-id-6', 'user-id-4');

-- Reviews for Template Post 7
INSERT INTO review (id, content, rating, template_post_id, user_id) VALUES ('review-id-12', 'An excellent educational post, very detailed and clear!', 5, 'tempPost-id-7', 'user-id-5');
