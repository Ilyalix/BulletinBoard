CREATE TABLE matchingAd (matching_ad_id INT PRIMARY KEY AUTO_INCREMENT, priceFrom INT, priceTo INT, title VARCHAR(255),
version INT, FK_Mad_Author INT NULL, FK_Mad_Category INT NULL, FOREIGN KEY (FK_Mad_Author) REFERENCES Author(author_id),
FOREIGN KEY (FK_Mad_Category) REFERENCES Category(category_id));
