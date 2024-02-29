--USERS
INSERT INTO users (id, email, first_name, last_name, password)
VALUES
    ('ba804cb9-fa14-42a5-afaf-be488742fc54', 'admin@example.com', 'James', 'Bond', '$2a$10$TM3PAYG3b.H98cbRrHqWa.BM7YyCqV92e/kUTBfj85AjayxGZU7d6'), -- Password: 1234
    ('0d8fa44c-54fd-4cd0-ace9-2a7da57992de', 'user@example.com', 'Tyler', 'Durden', '$2a$10$TM3PAYG3b.H98cbRrHqWa.BM7YyCqV92e/kUTBfj85AjayxGZU7d6') -- Password: 1234
    ON CONFLICT DO NOTHING;

--ROLES
INSERT INTO role (id, name)
VALUES
    ('d29e709c-0ff1-4f4c-a7ef-09f656c390f1', 'DEFAULT'),
    ('ab505c92-7280-49fd-a7de-258e618df074', 'ADMIN'),
    ('c6aee32d-8c35-4481-8b3e-a876a39b0c02', 'USER')
    ON CONFLICT DO NOTHING;

--AUTHORITIES
INSERT INTO authority (id, name)
VALUES
    ('2ebf301e-6c61-4076-98e3-2a38b31daf86', 'DEFAULT'),
    ('76d2cbf6-5845-470e-ad5f-2edb9e09a868', 'USER_MODIFY'),
    ('21c942db-a275-43f8-bdd6-d048c21bf5ab', 'USER_DELETE'),
    ('1e29a363-d231-4a40-9ee9-b194909a3cb2', 'POST_CREATE'),
    ('1e29a363-d777-4a40-9ee9-b194909a3cb2', 'POST_UPDATE')
    ON CONFLICT DO NOTHING;

--ASSIGN AUTHORITIES TO ROLES
-- Assign "POST_CREATE" to DEFAULT role (for default users)
INSERT INTO role_authority (role_id, authority_id)
VALUES
    ('d29e709c-0ff1-4f4c-a7ef-09f656c390f1', '1e29a363-d231-4a40-9ee9-b194909a3cb2') -- "POST_CREATE" authority
    ON CONFLICT DO NOTHING;

-- Assign "POST_UPDATE" authority to the ADMIN role
INSERT INTO role_authority (role_id, authority_id)
VALUES
    ('ab505c92-7280-49fd-a7de-258e618df074', '1e29a363-d777-4a40-9ee9-b194909a3cb2') -- "POST_UPDATE" authority
    ON CONFLICT DO NOTHING;

--ASSIGN ROLES TO USERS
INSERT INTO users_role (users_id, role_id)
VALUES
    ('ba804cb9-fa14-42a5-afaf-be488742fc54', 'ab505c92-7280-49fd-a7de-258e618df074'), -- ADMIN role for James
    ('0d8fa44c-54fd-4cd0-ace9-2a7da57992de', 'd29e709c-0ff1-4f4c-a7ef-09f656c390f1')  -- DEFAULT role for Tyler
    ON CONFLICT DO NOTHING;

--POSTS
INSERT INTO image_post (id, image, description, like_count, author_id) VALUES
                                                                           ('af7c1fe6-d669-414e-b066-e9733f0de7a8', 'https://www.istockphoto.com/resources/images/PhotoFTLP/1040315976.jpg', 'Post1', 0, 'ba804cb9-fa14-42a5-afaf-be488742fc54'),
                                                                           ('08c71152-c552-42e7-b094-f510ff44e9cb', 'https://www.istockphoto.com/resources/images/PhotoFTLP/998044806.jpg', 'Post2', 0, 'ba804cb9-fa14-42a5-afaf-be488742fc54');