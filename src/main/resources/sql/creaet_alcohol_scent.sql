# alcohol_scent 생성문
CREATE TABLE alcohol_scent (
  alcohol_number INT NOT NULL,
  scent_number INT NOT NULL,
  PRIMARY KEY (alcohol_number, scent_number),
  FOREIGN KEY (alcohol_number) REFERENCES alcohol(alcohol_number)
    ON DELETE CASCADE,
  FOREIGN KEY (scent_number) REFERENCES scent(scent_number)
    ON DELETE CASCADE
);