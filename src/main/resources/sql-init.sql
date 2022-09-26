-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : Dim 25 sep. 2022 à 03:01
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `suivi_financier`
--

-- --------------------------------------------------------

--
-- Structure de la table `event`
--

DROP TABLE IF EXISTS `event`;
CREATE TABLE IF NOT EXISTS `event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL,
  `titre` text NOT NULL,
  `date_event` datetime NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  `expense_id` int(11) NOT NULL,
  `income_id` int(11) NOT NULL,
  `dinsert` date NOT NULL,
  `dupdate` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `type` (`type`)
) ENGINE=MyISAM AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `event`
--

INSERT INTO `event` (`id`, `type`, `titre`, `date_event`, `start_time`, `end_time`, `expense_id`, `income_id`, `dinsert`, `dupdate`) VALUES
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

-- --------------------------------------------------------

--
-- Structure de la table `expense`
--

DROP TABLE IF EXISTS `expense`;
CREATE TABLE IF NOT EXISTS `expense` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL,
  `destinataire` varchar(32) NOT NULL,
  `titre` text NOT NULL,
  `montant` int(11) NOT NULL,
  `date_expense` datetime NOT NULL,
  `dinsert` date NOT NULL,
  `dupdate` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `type` (`type`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `expense`
--

INSERT INTO `expense` (`id`, `type`, `destinataire`, `titre`, `montant`, `date_expense`, `dinsert`, `dupdate`) VALUES
(2, 3, 'RBC', 'Hypothèque Lasalle - Septembre 2022', 2429, '2022-08-20 00:00:00', '2022-09-21', '2022-09-23'),
(3, 3, 'RBC', 'Hypothèque Chm Chambly - Septembre 2022', 975, '2022-08-30 00:00:00', '2022-09-21', '2022-09-23'),
(4, 3, 'RBC', 'Hypothèque GardenVille - Septembre 2022', 2395, '2022-09-06 00:00:00', '2022-09-21', '2022-09-23'),
(5, 5, 'Reno-Depot', 'Achat outils divers', 45, '2022-09-19 00:00:00', '2022-09-23', '2022-09-23');

-- --------------------------------------------------------

--
-- Structure de la table `income`
--

DROP TABLE IF EXISTS `income`;
CREATE TABLE IF NOT EXISTS `income` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL,
  `provenance` varchar(32) NOT NULL,
  `titre` text NOT NULL,
  `montant` int(11) NOT NULL,
  `dinsert` date NOT NULL,
  `dupdate` date NOT NULL,
  `date_income` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrpd4k2qqqjlv92b020bs94oas` (`type`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `income`
--

INSERT INTO `income` (`id`, `type`, `provenance`, `titre`, `montant`, `dinsert`, `dupdate`, `date_income`) VALUES
(1, 2, 'CGI', 'Salaire - Juin 2022 (01)', 2150, '2022-06-01', '2022-09-23', '2022-05-26 00:00:00'),
(2, 2, 'CGI', 'Salaire - Juin 2022 (02)', 2150, '2022-06-15', '2022-06-15', '2022-06-15 00:00:00'),
(8, 2, 'RQAP', 'RQAP Yassine', 970, '2022-09-18', '2022-09-23', '2022-09-29 00:00:00'),
(24, 2, 'Salaire de test', 'Salaire de test', 400, '2022-09-24', '2022-09-24', '2022-09-25 00:00:00'),
(25, 4, 'test 2019', 'test revenu 2019', 4111, '2022-09-24', '2022-09-24', '2019-09-25 00:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `type_event`
--

DROP TABLE IF EXISTS `type_event`;
CREATE TABLE IF NOT EXISTS `type_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(32) NOT NULL,
  `is_expense` int(11) NOT NULL,
  `is_income` int(11) NOT NULL,
  `is_event` int(11) NOT NULL,
  `dinsert` date NOT NULL,
  `dupdate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `type_event`
--

INSERT INTO `type_event` (`id`, `type`, `is_expense`, `is_income`, `is_event`, `dinsert`, `dupdate`) VALUES
(1, 'Loyer', 1, 1, 1, '2022-09-19', '2022-09-19'),
(2, 'Salaire', 0, 1, 1, '2022-09-19', '2022-09-19'),
(3, 'Hypoteque', 1, 0, 1, '2022-09-19', '2022-09-19'),
(4, 'Immobilier', 1, 1, 1, '2022-09-19', '2022-09-19'),
(5, 'Facture', 1, 0, 1, '2022-09-19', '2022-09-19'),
(6, 'Divers', 1, 1, 1, '2022-09-19', '2022-09-19'),
(7, 'Taxes', 1, 0, 1, '2022-09-20', '2022-09-20');

-- --------------------------------------------------------

--
-- Structure de la table `type_expense`
--

DROP TABLE IF EXISTS `type_expense`;
CREATE TABLE IF NOT EXISTS `type_expense` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(32) NOT NULL,
  `dinsert` date NOT NULL,
  `dupdate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `type_expense`
--

INSERT INTO `type_expense` (`id`, `type`, `dinsert`, `dupdate`) VALUES
(1, 'Hypoteque', '2022-09-18', '2022-09-18'),
(2, 'Taxes', '2022-09-18', '2022-09-18'),
(3, 'Facture', '2022-09-18', '2022-09-18'),
(4, 'Immobilier', '2022-09-18', '2022-09-18'),
(5, 'Divers', '2022-09-22', '2022-09-22');

-- --------------------------------------------------------

--
-- Structure de la table `type_income`
--

DROP TABLE IF EXISTS `type_income`;
CREATE TABLE IF NOT EXISTS `type_income` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(32) NOT NULL,
  `dinsert` date NOT NULL,
  `dupdate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `type_income`
--

INSERT INTO `type_income` (`id`, `type`, `dinsert`, `dupdate`) VALUES
(1, 'Salaire', '2022-09-15', '2022-09-15'),
(2, 'Immobilier', '2022-09-15', '2022-09-15'),
(3, 'Loyer', '2022-09-15', '2022-09-15'),
(4, 'Divers', '2022-09-15', '2022-09-15');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
