CREATE DATABASE IF NOT EXISTS medilabo;
USE medilabo;

CREATE TABLE genre (
  id INT PRIMARY KEY,
  libelle VARCHAR(10)
);

CREATE TABLE adresse (
  id INT PRIMARY KEY,
  libelle VARCHAR(255)
);

CREATE TABLE telephone (
  id INT PRIMARY KEY,
  numero VARCHAR(20)
);

CREATE TABLE patient (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nom VARCHAR(255),
  prenom VARCHAR(255),
  date_naissance VARCHAR(20),
  genre_id INT,
  adresse_id INT,
  telephone_id INT,
  FOREIGN KEY (genre_id) REFERENCES genre(id),
  FOREIGN KEY (adresse_id) REFERENCES adresse(id),
  FOREIGN KEY (telephone_id) REFERENCES telephone(id)
);

INSERT INTO genre (id, libelle) VALUES (1, 'F'), (2, 'M');

INSERT INTO adresse (id, libelle) VALUES
(1, '1 Brookside St'),
(2, '2 High St'),
(3, '3 Club Road'),
(4, '4 Valley Dr');

INSERT INTO telephone (id, numero) VALUES
(1, '100-222-3333'),
(2, '200-333-4444'),
(3, '300-444-5555'),
(4, '400-555-6666');

INSERT INTO patient (nom, prenom, date_naissance, genre_id, adresse_id, telephone_id) VALUES
('TestNone', 'Test', '1966-12-31', 1, 1, 1),
('TestBorderline', 'Test', '1945-06-24', 2, 2, 2),
('TestInDanger', 'Test', '2004-06-18', 2, 3, 3),
('TestEarlyOnset', 'Test', '2002-06-28', 1, 4, 4);