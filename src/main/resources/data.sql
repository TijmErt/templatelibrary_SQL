/* Users */

INSERT INTO public.users (id, email, user_name) VALUES ('user-id-1', 'user1@test.com', 'user1');
INSERT INTO public.users (id, email, user_name) VALUES ('user-id-2', 'user2@test.com', 'user2');


/* TemplatePosts */

INSERT INTO public.template_post (id, created_date, description, document_key, title, user_id) VALUES ('tempPost-id-1', '2024-10-29', 'descrip1', 'Key1', 'tempTitle1', 'user-id-1');
INSERT INTO public.template_post (id, created_date, description, document_key, title, user_id) VALUES ('tempPost-id-2', '2024-10-11', 'descrip2', 'key2', 'tempTitle2', 'user-id-1');
INSERT INTO public.template_post (id, created_date, description, document_key, title, user_id) VALUES ('tempPost-id-3', '2024-10-10', 'descrip3', 'key3', 'tempTitle3', 'user-id-2');
INSERT INTO public.template_post (id, created_date, description, document_key, title, user_id) VALUES ('tempPost-id-4', '2024-10-27', 'descrip4', 'key4', 'tempTitle4', 'user-id-1');


/* Reviews */


/* Category */
insert into public.category (id, name) values ('category-id-1', 'category1');
insert into public.category (id, name) values ('category-id-2', 'category2');
insert into public.category (id, name) values ('category-id-3', 'category3');


/* post_category */
insert into public.post_category (template_post_id, category_id) values ('tempPost-id-1', 'category-id-1');
insert into public.post_category (template_post_id, category_id) values ('tempPost-id-3', 'category-id-1');
insert into public.post_category (template_post_id, category_id) values ('tempPost-id-4', 'category-id-1');
insert into public.post_category (template_post_id, category_id) values ('tempPost-id-1', 'category-id-2');
insert into public.post_category (template_post_id, category_id) values ('tempPost-id-2', 'category-id-2');
insert into public.post_category (template_post_id, category_id) values ('tempPost-id-4', 'category-id-3');
insert into public.post_category (template_post_id, category_id) values ('tempPost-id-3', 'category-id-3');
insert into public.post_category (template_post_id, category_id) values ('tempPost-id-2', 'category-id-3');
