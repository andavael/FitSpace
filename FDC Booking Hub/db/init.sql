CREATE DATABASE IF NOT EXISTS FitSpace;
USE FitSpace;

GRANT ALL PRIVILEGES ON FitSpace.* TO 'root'@'localhost';
FLUSH PRIVILEGES;

SHOW TABLES;

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    unique_id VARCHAR(15) NOT NULL,     
    password VARCHAR(255) NOT NULL,    
    first_name VARCHAR(50) NOT NULL,   
    middle_name VARCHAR(50),         
    last_name VARCHAR(50) NOT NULL,    
    department VARCHAR(50), 
    gsuite_account VARCHAR(100) NOT NULL,
    user_type ENUM('student', 'employee') NOT NULL,  
    UNIQUE (unique_id)                  
);

CREATE TABLE facilities (
    facility_id INT AUTO_INCREMENT PRIMARY KEY,  
    name VARCHAR(100) NOT NULL,                   
    status ENUM('active', 'inactive') DEFAULT 'active'  
);

INSERT INTO facilities (name, status) VALUES
('Volleyball Court', 'active'),
('Basketball Court', 'active'),
('Badminton Court', 'active'),
('Dance Hall', 'active'),
('Multipurpose Hall', 'active');

CREATE TABLE time_slots (
    slot_id INT AUTO_INCREMENT PRIMARY KEY,  
    start_time TIME NOT NULL,                 
    end_time TIME NOT NULL                   
);

INSERT INTO time_slots (start_time, end_time) VALUES
('07:00:00', '09:00:00'),
('09:00:00', '11:00:00'),
('11:00:00', '13:00:00'),
('13:00:00', '15:00:00'),
('15:00:00', '17:00:00'),
('17:00:00', '19:00:00');

CREATE TABLE reservations (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,  
    user_id INT NOT NULL,                           
    facility_id INT NOT NULL,                       
    slot_id INT NOT NULL,                           
    reservation_date DATE NOT NULL,                 
    status ENUM('confirmed', 'cancelled') DEFAULT 'confirmed', 
    FOREIGN KEY (user_id) REFERENCES users(user_id),  
    FOREIGN KEY (facility_id) REFERENCES facilities(facility_id), 
    FOREIGN KEY (slot_id) REFERENCES time_slots(slot_id)  
);

INSERT INTO users (user_id, unique_id, password, first_name, middle_name, last_name, department, gsuite_account, user_type)
VALUES 
(1, '23-00123', 'password123', 'John', 'Asher', 'Doe', 'CICS', '23-00123@batstate-u.edu.ph', 'student'),
(2, '23-00789', 'password789', 'Michael', NULL, 'Johnson', 'CET', 'johnson.michael@batstate-u.edu.ph', 'employee');

INSERT INTO reservations (reservation_id, user_id, facility_id, slot_id, reservation_date, status)
VALUES
(1, 1, 2, 3, '2024-11-01', 'confirmed'),
(2, 2, 4, 5, '2024-11-02', 'cancelled');
