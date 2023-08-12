create database ecomorg_db;
use ecomorg_db;

CREATE USER 'devuser'@'localhost' IDENTIFIED BY 'DevUser!74#';
GRANT ALL PRIVILEGES ON * . * TO 'devuser'@'localhost';
FLUSH PRIVILEGES;

CREATE USER 'devuser'@'localhost' IDENTIFIED BY 'DevUser!74#';
GRANT ALL PRIVILEGES ON ecomorg_db.* TO 'devuser'@'localhost';
FLUSH PRIVILEGES;