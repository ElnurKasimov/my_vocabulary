INSERT INTO tags (name) SELECT 'Sales' WHERE NOT EXISTS (SELECT 1 FROM tags WHERE name = 'Sales');
INSERT INTO tags (name) SELECT 'Programming' WHERE NOT EXISTS (SELECT 1 FROM tags WHERE name = 'Programming');
INSERT INTO tags (name) SELECT 'Negotiations' WHERE NOT EXISTS (SELECT 1 FROM tags WHERE name = 'Negotiations');
INSERT INTO tags (name) SELECT 'Learning' WHERE NOT EXISTS (SELECT 1 FROM tags WHERE name = 'Learning');
INSERT INTO tags (name) SELECT 'Activities' WHERE NOT EXISTS (SELECT 1 FROM tags WHERE name = 'Activities');
INSERT INTO tags (name) SELECT 'IT' WHERE NOT EXISTS (SELECT 1 FROM tags WHERE name = 'IT');
INSERT INTO tags (name) SELECT 'Interview' WHERE NOT EXISTS (SELECT 1 FROM tags WHERE name = 'Interview');
INSERT INTO tags (name) SELECT 'Business' WHERE NOT EXISTS (SELECT 1 FROM tags WHERE name = 'Business');