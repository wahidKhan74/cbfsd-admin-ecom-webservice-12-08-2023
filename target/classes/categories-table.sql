create database ecomorg_db;
use ecomorg_db;

 CREATE TABLE CATEGORIES (
  categoryId            INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  categoryName          VARCHAR(255) NOT NULL,
  categoryDescription   VARCHAR(255),
  categoryImageUrl      VARCHAR(500),
  active                INTEGER DEFAULT 0,
  addedOn               DATETIME DEFAULT CURRENT_TIMESTAMP
);

  
SHOW TABLES;

desc USERS;