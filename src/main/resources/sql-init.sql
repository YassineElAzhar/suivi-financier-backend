<<<<<<< HEAD
/*We drop tables */
DROP TABLE event;
DROP TABLE expense;
DROP TABLE income;
DROP TABLE type_event;
DROP TABLE type_expense;
DROP TABLE type_income;

/*We create tables*/
CREATE TABLE income (
  id serial PRIMARY KEY,
  type int NOT NULL,
  provenance varchar(32) NOT NULL,
  titre varchar(100) NOT NULL,
  montant int NOT NULL,
  dinsert date NOT NULL,
  dupdate date NOT NULL,
  date_income date
);

CREATE TABLE event (
  id serial PRIMARY KEY,
  type int NOT NULL,
  titre varchar(100) NOT NULL,
  date_event date NOT NULL,
  start_time varchar(32) NOT NULL,
  end_time varchar(32) NOT NULL,
  expense_id int NOT NULL,
  income_id int NOT NULL,
  dinsert date NOT NULL,
  dupdate date NOT NULL
);

CREATE TABLE expense (
  id serial PRIMARY KEY,
  type int NOT NULL,
  destinataire varchar(32) NOT NULL,
  titre varchar(100) NOT NULL,
  montant int NOT NULL,
  date_expense date NOT NULL,
  dinsert date NOT NULL,
  dupdate date NOT NULL
);

CREATE TABLE type_event (
  id serial PRIMARY KEY,
  type varchar(32) NOT NULL,
  is_expense int NOT NULL,
  is_income int NOT NULL,
  is_event int NOT NULL,
  dinsert date NOT NULL,
  dupdate date NOT NULL
);

CREATE TABLE type_expense (
  id serial PRIMARY KEY,
  type varchar(32) NOT NULL,
  dinsert date NOT NULL,
  dupdate date NOT NULL
);

CREATE TABLE type_income (
  id serial PRIMARY KEY,
  type varchar(32) NOT NULL,
  dinsert date NOT NULL,
  dupdate date NOT NULL
);



/*data init*/
INSERT INTO event (id, type, titre, date_event, start_time, end_time, expense_id, income_id, dinsert, dupdate) VALUES
(15, 2, 'Salaire CGI - 09-01', '2022-09-08 04:00:00', '00:00:00', '00:00:00', 0, 0, '2022-09-21', '2022-09-21'),
(3, 1, 'Loyer 1421', '2022-09-01 15:20:41', '00:00:00', '01:00:00', 0, 0, '2022-09-20', '2022-09-21'),
(4, 1, 'Loyer 1423', '2022-09-01 15:20:41', '00:00:00', '01:00:00', 0, 0, '2022-09-20', '2022-09-20'),
(5, 1, 'Loyer 1423 A', '2022-09-01 15:20:41', '00:00:00', '01:00:00', 0, 0, '2022-09-20', '2022-09-20'),
(6, 1, 'Loyer 752 CC', '2022-09-01 15:20:41', '00:00:00', '01:00:00', 0, 0, '2022-09-20', '2022-09-21'),
(7, 6, 'Anniv Nael', '2022-08-05 15:20:41', '14:22:00', '14:25:00', 0, 0, '2022-09-20', '2022-09-20'),
(8, 2, 'Salaire CGI - 01', '2022-08-02 15:20:41', '00:00:00', '00:00:00', 0, 0, '2022-09-20', '2022-09-20'),
(9, 2, 'Salaire CGI - 02', '2022-08-16 15:20:41', '00:00:00', '00:00:00', 0, 0, '2022-09-20', '2022-09-20'),
(10, 6, 'Ceci est un titre', '2022-08-17 04:20:41', '00:00:00', '00:00:00', 0, 0, '2022-09-20', '2022-09-20'),
(11, 6, 'Ceci est un titre 2', '2022-08-17 04:20:41', '01:00:00', '02:00:00', 0, 0, '2022-09-20', '2022-09-20'),
(12, 6, 'Ceci est un titre 3', '2022-08-17 04:20:41', '02:00:00', '03:00:00', 0, 0, '2022-09-20', '2022-09-20'),
(13, 6, 'Ceci est un titre 3', '2022-08-17 04:20:41', '02:00:00', '03:00:00', 0, 0, '2022-09-20', '2022-09-20'),
(14, 2, 'Salaire - CGI - 09-02', '2022-09-22 04:00:00', '00:00:00', '00:00:00', 0, 0, '2022-09-21', '2022-09-21'),
(17, 2, 'RQAP Sophia', '2022-09-21 04:00:00', '00:00:00', '00:00:00', 0, 0, '2022-09-21', '2022-09-21'),
(41, 2, 'Salaire de test', '2022-09-25 00:00:00', '00:00:00', '00:00:00', 0, 24, '2022-09-24', '2022-09-24'),
(42, 4, 'test revenu 2019', '2019-09-25 00:00:00', '00:00:00', '00:00:00', 0, 25, '2022-09-24', '2022-09-24');


INSERT INTO expense (id, type, destinataire, titre, montant, date_expense, dinsert, dupdate) VALUES
(2, 3, 'RBC', 'Hypothèque Lasalle - Septembre 2022', 2429, '2022-08-20 00:00:00', '2022-09-21', '2022-09-23'),
(3, 3, 'RBC', 'Hypothèque Chm Chambly - Septembre 2022', 975, '2022-08-30 00:00:00', '2022-09-21', '2022-09-23'),
(4, 3, 'RBC', 'Hypothèque GardenVille - Septembre 2022', 2395, '2022-09-06 00:00:00', '2022-09-21', '2022-09-23'),
(5, 5, 'Reno-Depot', 'Achat outils divers', 45, '2022-09-19 00:00:00', '2022-09-23', '2022-09-23');

INSERT INTO income (id, type, provenance, titre, montant, dinsert, dupdate, date_income) VALUES
(1, 2, 'CGI', 'Salaire - Juin 2022 (01)', 2150, '2022-06-01', '2022-09-23', '2022-05-26 00:00:00'),
(2, 2, 'CGI', 'Salaire - Juin 2022 (02)', 2150, '2022-06-15', '2022-06-15', '2022-06-15 00:00:00'),
(8, 2, 'RQAP', 'RQAP Yassine', 970, '2022-09-18', '2022-09-23', '2022-09-29 00:00:00'),
(24, 2, 'Salaire de test', 'Salaire de test', 400, '2022-09-24', '2022-09-24', '2022-09-25 00:00:00'),
(25, 4, 'test 2019', 'test revenu 2019', 4111, '2022-09-24', '2022-09-24', '2019-09-25 00:00:00');

INSERT INTO type_event (id, type, is_expense, is_income, is_event, dinsert, dupdate) VALUES
(1, 'Loyer', 1, 1, 1, '2022-09-19', '2022-09-19'),
(2, 'Salaire', 0, 1, 1, '2022-09-19', '2022-09-19'),
(3, 'Hypoteque', 1, 0, 1, '2022-09-19', '2022-09-19'),
(4, 'Immobilier', 1, 1, 1, '2022-09-19', '2022-09-19'),
(5, 'Facture', 1, 0, 1, '2022-09-19', '2022-09-19'),
(6, 'Divers', 1, 1, 1, '2022-09-19', '2022-09-19'),
(7, 'Taxes', 1, 0, 1, '2022-09-20', '2022-09-20');

INSERT INTO type_expense (id, type, dinsert, dupdate) VALUES
(1, 'Hypoteque', '2022-09-18', '2022-09-18'),
(2, 'Taxes', '2022-09-18', '2022-09-18'),
(3, 'Facture', '2022-09-18', '2022-09-18'),
(4, 'Immobilier', '2022-09-18', '2022-09-18'),
(5, 'Divers', '2022-09-22', '2022-09-22');

INSERT INTO type_income (id, type, dinsert, dupdate) VALUES
(1, 'Salaire', '2022-09-15', '2022-09-15'),
(2, 'Immobilier', '2022-09-15', '2022-09-15'),
(3, 'Loyer', '2022-09-15', '2022-09-15'),
(4, 'Divers', '2022-09-15', '2022-09-15');