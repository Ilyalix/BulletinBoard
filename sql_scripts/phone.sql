CREATE TABLE phone (phone_id INT PRIMARY KEY AUTO_INCREMENT, phone VARCHAR(255), version INT, FK_Phone_Author INT,
FOREIGN KEY (FK_Phone_Author) REFERENCES Author(author_id));
