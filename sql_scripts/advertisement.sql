CREATE TABLE advertisement (advertisement_id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), dateOfPublic VARCHAR(255),
price INT, text VARCHAR(255), version INT, isActive TINYINT, FK_Ad_Author INT NULL, FK_Ad_Category INT NULL,
FOREIGN KEY (FK_Ad_Author) REFERENCES Author(author_id), FOREIGN KEY (FK_Ad_Category) REFERENCES Category(category_id));


