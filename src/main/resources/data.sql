USE gold_db;

-- Seed data for User table
INSERT INTO user (user_name, city, state, zip_code, peanut_allergy, dairy_allergy, egg_allergy)
VALUES
    ('JohnDoe', 'Dallas', 'TX', 12345, true, false, true),
    ('JaneSmith', 'Frisco', 'TX', 75035, false, true, false),
    ('BobJohnson', 'Plano', 'TX', 75024, false, false, true);

-- Seed data for Reviews table
INSERT INTO reviews (author, restaurant_id, peanut_score, dairy_score, egg_score, commentary)
VALUES
    ('Alice', 1, 5, 4, 3, 'Great experience!'),
    ('Charlie', 2, 2, 1, 5, 'Not impressed.'),
    ('Eva', 3, 4, 3, 2, 'Decent food, okay service.');

-- Seed data for Restaurant table
INSERT INTO restaurant (name, zip_code, overall_score, peanut_score, egg_score, dairy_score)
VALUES
    ('Tasty Grill', 12345, 4, 4, 5, 3),
    ('Seafood Haven', 75035, 3, 2, 4, 1),
    ('Pizza Paradise', 75024, 5, 5, 2, 4);

-- Seed data for Auth table
-- Seed data for Auth table
INSERT INTO auth (approved)
VALUES
    (2), -- APPROVED
    (0), -- PENDING
    (1); -- DENIED
