CREATE DATABASE cabdb;
USE cabdb;

CREATE TABLE users (
 id INT AUTO_INCREMENT PRIMARY KEY,
 name VARCHAR(100),
 email VARCHAR(100),
 phone VARCHAR(20)
);

CREATE TABLE cabs (
 id INT AUTO_INCREMENT PRIMARY KEY,
 number_plate VARCHAR(20),
 driver_name VARCHAR(100),
 model VARCHAR(100),
 available BOOLEAN DEFAULT TRUE
);

CREATE TABLE bookings (
 id INT AUTO_INCREMENT PRIMARY KEY,
 user_id INT,
 cab_id INT,
 pickup VARCHAR(255),
 dropoff VARCHAR(255),
 status VARCHAR(50),
 FOREIGN KEY (user_id) REFERENCES users(id),
 FOREIGN KEY (cab_id) REFERENCES cabs(id)
);
