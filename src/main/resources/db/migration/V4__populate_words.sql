INSERT INTO words (foreign_word, translation_word, description, tag_id)
 SELECT 'consistent', 'последовательный, согласованный', '', (SELECT id FROM tags WHERE name = 'Sales')
 WHERE NOT EXISTS (SELECT 1 FROM words WHERE foreign_word = 'consistent');

INSERT INTO words (foreign_word, translation_word, description, tag_id)
 SELECT 'to urge', 'побуждать, настоять', '', (SELECT id FROM tags WHERE name = 'Sales')
 WHERE NOT EXISTS (SELECT 1 FROM words WHERE foreign_word = 'to urge');

INSERT INTO words (foreign_word, translation_word, description, tag_id)
 SELECT 'descending', 'по убыванию', '', (SELECT id FROM tags WHERE name = 'Programming')
 WHERE NOT EXISTS (SELECT 1 FROM words WHERE foreign_word = 'descending');

INSERT INTO words (foreign_word, translation_word, description, tag_id)
 SELECT 'to cast', 'кидать, бросать', 'в IT - преобразовывать тип данных', (SELECT id FROM tags WHERE name = 'Programming')
 WHERE NOT EXISTS (SELECT 1 FROM words WHERE foreign_word = 'to cast');

INSERT INTO words (foreign_word, translation_word, description, tag_id)
 SELECT 'bootstrapping', 'загрузка, создание', '', (SELECT id FROM tags WHERE name = 'Negotiations')
 WHERE NOT EXISTS (SELECT 1 FROM words WHERE foreign_word = 'bootstrapping');

INSERT INTO words (foreign_word, translation_word, description, tag_id)
 SELECT 'trade-off', 'компромисс', '', (SELECT id FROM tags WHERE name = 'Negotiations')
 WHERE NOT EXISTS (SELECT 1 FROM words WHERE foreign_word = 'trade-off');

INSERT INTO words (foreign_word, translation_word, description, tag_id)
 SELECT 'clue', 'подсказка', '', (SELECT id FROM tags WHERE name = 'Learning')
 WHERE NOT EXISTS (SELECT 1 FROM words WHERE foreign_word = 'clue');

INSERT INTO words (foreign_word, translation_word, description, tag_id)
 SELECT 'pertinent', 'актуально', '', (SELECT id FROM tags WHERE name = 'Learning')
 WHERE NOT EXISTS (SELECT 1 FROM words WHERE foreign_word = 'pertinent');

INSERT INTO words (foreign_word, translation_word, description, tag_id)
 SELECT 'call it a day', 'прекратить что-то делать', 'to stop something. Ex.The consumer fixed you beta version and call it a day', (SELECT id FROM tags WHERE name = 'Activities')
 WHERE NOT EXISTS (SELECT 1 FROM words WHERE foreign_word = 'call it a day');

INSERT INTO words (foreign_word, translation_word, description, tag_id)
 SELECT 'to move on', 'двигаться дальше', 'Ex. Let''s move on!', (SELECT id FROM tags WHERE name = 'Activities')
 WHERE NOT EXISTS (SELECT 1 FROM words WHERE foreign_word = 'to move on');

INSERT INTO words (foreign_word, translation_word, description, tag_id)
 SELECT 'iterative', 'итерационный, циклический; повторяющийся, повторный', '', (SELECT id FROM tags WHERE name = 'IT')
 WHERE NOT EXISTS (SELECT 1 FROM words WHERE foreign_word = 'iterative');

INSERT INTO words (foreign_word, translation_word, description, tag_id)
 SELECT 'agile', 'специальный подход в разработке', 'a set of methodologies and practices in software development focused on iterative development. Produce part of software - get feed back - analyze feed back - correct or produce new one', (SELECT id FROM tags WHERE name = 'IT')
 WHERE NOT EXISTS (SELECT 1 FROM words WHERE foreign_word = 'agile');

INSERT INTO words (foreign_word, translation_word, description, tag_id)
 SELECT 'to my mind / I guess / I believe that', 'Я считаю, думаю', '', (SELECT id FROM tags WHERE name = 'Interview')
 WHERE NOT EXISTS (SELECT 1 FROM words WHERE foreign_word = 'to my mind / I guess / I believe that');

INSERT INTO words (foreign_word, translation_word, description, tag_id)
 SELECT 'I really feel that ...', 'Мне действительно кажется, что ...', '', (SELECT id FROM tags WHERE name = 'Interview')
 WHERE NOT EXISTS (SELECT 1 FROM words WHERE foreign_word = 'I really feel that ...');

INSERT INTO words (foreign_word, translation_word, description, tag_id)
 SELECT 'employer', 'работодатель', '', (SELECT id FROM tags WHERE name = 'Business')
 WHERE NOT EXISTS (SELECT 1 FROM words WHERE foreign_word = 'employer');

INSERT INTO words (foreign_word, translation_word, description, tag_id)
 SELECT 'employee', 'работник', '', (SELECT id FROM tags WHERE name = 'Business')
 WHERE NOT EXISTS (SELECT 1 FROM words WHERE foreign_word = 'employee');
