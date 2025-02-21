CREATE DATABASE annuaire;

USE annuaire;

CREATE TABLE site (
    id INT AUTO_INCREMENT PRIMARY KEY,
    city VARCHAR(100) NOT NULL
);

CREATE TABLE service (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    last_name VARCHAR(100) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    landline_phone VARCHAR(15) NOT NULL,
    mobile_phone VARCHAR(15),
    email VARCHAR(50) UNIQUE NOT NULL,
    site_id INT,
    service_id INT,
    FOREIGN KEY (site_id) REFERENCES site(id),
    FOREIGN KEY (service_id) REFERENCES service(id)
);
