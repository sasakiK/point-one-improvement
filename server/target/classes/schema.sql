DROP TABLE improvement IF EXISTS;

CREATE TABLE improvement (
    id INT AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR(200) NOT NULL,
    created_at DATE
) ENGINE = INNODB;