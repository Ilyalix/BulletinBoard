CREATE TABLE author (author_id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), age INT, password VARCHAR(255),
version INT, active BIT, FK_Author_Address INT, FK_Author_Email INT, FOREIGN KEY (FK_Author_Address) REFERENCES Address(address_id),
FOREIGN KEY (FK_Author_Email) REFERENCES Email(email_id));

INSERT INTO role (name) VALUES ("ROLE_ADMIN");
