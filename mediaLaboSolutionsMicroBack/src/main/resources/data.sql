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