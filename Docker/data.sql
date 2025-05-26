-- Schema full-stack-sports-center

CREATE DATABASE IF NOT EXISTS `sports-center`;

USE `sports-center`;

-- Drop existing tables if they exist (in reverse order of dependency)
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS type;
DROP TABLE IF EXISTS brand;


-- Create the Brand table
CREATE TABLE `brand` (
                         Id INT AUTO_INCREMENT PRIMARY KEY,
                         Name VARCHAR(255) NOT NULL
);

-- Insert data into the Brand table
INSERT INTO brand (Name) VALUES
                             ('Adidas'),
                             ('ASICS'),
                             ('Victor'),
                             ('Yonex'),
                             ('Puma'),
                             ('Nike'),
                             ('Babolat');

-- Create the Type table
CREATE TABLE `type` (
                        Id INT AUTO_INCREMENT PRIMARY KEY,
                        Name VARCHAR(255) NOT NULL
);

-- Insert data into the Type table
INSERT INTO type (Name) VALUES
                            ('Shoes'),
                            ('Rackets'),
                            ('Football'),
                            ('Kit Bags');

-- Create the Product table
CREATE TABLE `product` (
                           Id INT AUTO_INCREMENT PRIMARY KEY,
                           Name VARCHAR(255) NOT NULL,
                           Description TEXT,
                           Price DECIMAL(10, 2) NOT NULL,
                           PictureUrl VARCHAR(255),
                           ProductTypeId INT NOT NULL,
                           ProductBrandId INT NOT NULL,
                           FOREIGN KEY (ProductTypeId) REFERENCES type (Id),
                           FOREIGN KEY (ProductBrandId) REFERENCES brand (Id)
);

-- Insert data into the Product table
INSERT INTO product (Name, Description, Price, PictureUrl, ProductTypeId, ProductBrandId) VALUES
                                                                                              ('Adidas Quick Force Indoor Badminton Shoes', 'Designed for professional as well as amateur badminton players, these shoes offer excellent grip and support.', 75.99, 'https://placehold.co/600x400/000000/FFFFFF?text=Adidas+Shoes', 1, 1),
                                                                                              ('Asics Gel Rocket 8 Indoor Court Shoes', 'The Asics Gel Rocket 8 Indoor Court Shoes (Orange/Silver) are perfect for indoor sports, providing stability and comfort.', 85.50, 'https://placehold.co/600x400/000000/FFFFFF?text=Asics+Shoes', 1, 2),
                                                                                              ('Yonex Voltric Z-Force II Badminton Racket', 'A powerful and aerodynamic racket for advanced players, offering explosive smashes and quick swings.', 199.99, 'https://placehold.co/600x400/000000/FFFFFF?text=Yonex+Racket', 2, 4),
                                                                                              ('Puma Future 6.1 Netfit FG/AG Football Boots', 'Revolutionary football boots with NETFIT technology for a custom fit and enhanced agility on firm ground and artificial grass.', 120.00, 'https://placehold.co/600x400/000000/FFFFFF?text=Puma+Football+Boots', 3, 5),
                                                                                              ('Nike Brasilia Training Duffel Bag', 'Durable and spacious duffel bag with multiple compartments for all your training essentials.', 45.00, 'https://placehold.co/600x400/000000/FFFFFF?text=Nike+Kit+Bag', 4, 6);
