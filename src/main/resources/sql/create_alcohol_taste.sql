# create alcohol_taste
CREATE TABLE alcohol_taste (
  alcohol_number INT NOT NULL,
  taste_number INT NOT NULL,
  PRIMARY KEY (alcohol_number, taste_number),
  FOREIGN KEY (alcohol_number) REFERENCES alcohol(alcohol_number)
    ON DELETE CASCADE,
  FOREIGN KEY (taste_number) REFERENCES taste(taste_number)
    ON DELETE CASCADE
);