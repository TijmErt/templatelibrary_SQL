-- Test Data for Users
INSERT INTO users (id, email, user_name) VALUES
('user-id-1', 'alice@test.com', 'Alice'),
('user-id-2', 'bob@test.com', 'Bob');

-- Test Data for TemplatePosts
INSERT INTO template_post (id, created_date, description, file_key, title, user_id) VALUES
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

-- Test Data for Bookmark Lists
INSERT INTO bookmark_list (id, title, user_id) VALUES
('bookmark-id-1', 'My Favorite Posts', 'user-id-1'),
('bookmark-id-2', 'Health Related Posts', 'user-id-2'),
('bookmark-id-3', 'Technology Posts', 'user-id-1');

-- Test Data for Bookmark List - Template Post Relationships
INSERT INTO bookmark_list_template_post (bookmark_list_id, template_post_id) VALUES
('bookmark-id-1', 'tempPost-id-1'),  -- Bookmark List 1: Template Post 1
('bookmark-id-1', 'tempPost-id-2'),  -- Bookmark List 1: Template Post 2
('bookmark-id-2', 'tempPost-id-2'),  -- Bookmark List 2: Template Post 2
('bookmark-id-2', 'tempPost-id-3'),  -- Bookmark List 2: Template Post 3
('bookmark-id-3', 'tempPost-id-1'),  -- Bookmark List 3: Template Post 1
('bookmark-id-3', 'tempPost-id-4');  -- Bookmark List 3: Template Post 4


-- Test Data for Reviews Ordered by Template Post
INSERT INTO review (id, content, rating, template_post_id, user_id) VALUES
-- Reviews for Template Post 1
('review-id-1', 'Great template post with detailed information. I was able to apply it directly to my project and saved a lot of time!', 5, 'tempPost-id-1', 'user-id-1'),  -- Review 1: Template Post 1 by User 1
('review-id-5', 'This post was okay, but I found some parts to be outdated. Needs an update with newer practices.', 3, 'tempPost-id-1', 'user-id-2'),  -- Review 5: Template Post 1 by User 2

-- Reviews for Template Post 2
('review-id-2', 'Informative but could use more examples to clarify the concepts. Still a useful resource!', 4, 'tempPost-id-2', 'user-id-2'),  -- Review 2: Template Post 2 by User 2
('review-id-7', 'The post was decent, but there were a few typos that made it a bit difficult to follow. A little more polish would be great.', 4, 'tempPost-id-2', 'user-id-1'),  -- Review 7: Template Post 2 by User 1

-- Reviews for Template Post 3
('review-id-6', 'I absolutely loved this template! The structure was clear and made it easy to implement. Would love to see more like this.', 5, 'tempPost-id-3', 'user-id-2'),  -- Review 6: Template Post 3 by User 2
('review-id-3', 'Helpful, but a little too basic for my needs. Would be great if it included more advanced use cases.', 3, 'tempPost-id-3', 'user-id-1'),  -- Review 3: Template Post 3 by User 1

-- Reviews for Template Post 4
('review-id-4', 'Excellent content! Very detailed and useful. I would highly recommend this template to others.', 5, 'tempPost-id-4', 'user-id-2'),  -- Review 4: Template Post 4 by User 2
('review-id-8', 'Not what I expected. The post is too broad and lacks the depth I was hoping for. Would not recommend for advanced users.', 2, 'tempPost-id-4', 'user-id-1');  -- Review 8: Template Post 4 by User 1
