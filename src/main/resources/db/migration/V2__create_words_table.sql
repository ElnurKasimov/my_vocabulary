CREATE TABLE words (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    foreign_word VARCHAR(255) NOT NULL,
    translation_word VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    tag_id VARCHAR(255),
    FOREIGN KEY (tag_id) REFERENCES tags (id)
)