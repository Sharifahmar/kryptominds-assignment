-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.1.73-community - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for kryptominds_db
DROP DATABASE IF EXISTS `kryptominds_db`;
CREATE DATABASE IF NOT EXISTS `kryptominds_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `kryptominds_db`;

-- Dumping structure for table kryptominds_db.profile
DROP TABLE IF EXISTS `profile`;
CREATE TABLE IF NOT EXISTS `profile` (
  `PROFILE_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_DATE` datetime NOT NULL,
  `UPDATED_DATE` datetime NOT NULL,
  `ADDRESS` text,
  `C_ADDRESS` text,
  `C_CITY` varchar(100) DEFAULT NULL,
  `C_COUNTRY` varchar(100) DEFAULT NULL,
  `C_EMAIL_ID` varchar(100) NOT NULL,
  `C_NAME` varchar(100) DEFAULT NULL,
  `C_PHONE` varchar(100) NOT NULL,
  `C_STATE` varchar(100) DEFAULT NULL,
  `C_WEBSITE` varchar(100) DEFAULT NULL,
  `C_ZIPCODE` varchar(100) DEFAULT NULL,
  `CITY` varchar(100) DEFAULT NULL,
  `COUNTRY` varchar(100) DEFAULT NULL,
  `EMAIL_ID` varchar(100) NOT NULL,
  `FIRST_NAME` varchar(100) DEFAULT NULL,
  `LAST_NAME` varchar(100) DEFAULT NULL,
  `PHONE` varchar(100) NOT NULL,
  `STATE` varchar(100) DEFAULT NULL,
  `PROFILE_STS` bit(1) NOT NULL,
  `ZIPCODE` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PROFILE_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- Dumping data for table kryptominds_db.profile: 0 rows
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;

-- Dumping structure for table kryptominds_db.refresh_tokens
DROP TABLE IF EXISTS `refresh_tokens`;
CREATE TABLE IF NOT EXISTS `refresh_tokens` (
  `token` varchar(255) NOT NULL,
  `expirationDateTime` datetime DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`token`),
  KEY `FKhspjwa36lvj54jpx0kuyx4b33` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- Dumping data for table kryptominds_db.refresh_tokens: 0 rows
/*!40000 ALTER TABLE `refresh_tokens` DISABLE KEYS */;
/*!40000 ALTER TABLE `refresh_tokens` ENABLE KEYS */;

-- Dumping structure for table kryptominds_db.roles
DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `ROLE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_DESC` text,
  `ROLE_NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`ROLE_ID`),
  UNIQUE KEY `UK_qfo9r93pug5nrwxcqhps55jvn` (`ROLE_NAME`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table kryptominds_db.roles: 0 rows
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT IGNORE INTO `roles` (`ROLE_ID`, `ROLE_DESC`, `ROLE_NAME`) VALUES
	(1, 'ROLE_ADMIN', 'ROLE_ADMIN'),
	(2, 'ROLE_USER', 'ROLE_USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;

-- Dumping structure for table kryptominds_db.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `USER_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_DATE` datetime NOT NULL,
  `UPDATED_DATE` datetime NOT NULL,
  `ADDRESS` text,
  `CITY` varchar(100) DEFAULT NULL,
  `COUNTRY` varchar(100) DEFAULT NULL,
  `EMAIL_ID` varchar(100) NOT NULL,
  `FIRST_NAME` varchar(100) DEFAULT NULL,
  `LAST_NAME` varchar(100) DEFAULT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `PHONE` varchar(100) NOT NULL,
  `STATE` varchar(100) DEFAULT NULL,
  `USER_STS` bit(1) NOT NULL,
  `ZIPCODE` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `UKq7m1l7d7rjcxryb6bs64nmord` (`EMAIL_ID`),
  UNIQUE KEY `UKig284flnsskg3l7df8ty1d9o9` (`PHONE`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- Dumping data for table kryptominds_db.users: 0 rows
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

-- Dumping structure for table kryptominds_db.user_roles
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE IF NOT EXISTS `user_roles` (
  `USER_ID` bigint(20) NOT NULL,
  `ROLE_ID` int(11) NOT NULL,
  PRIMARY KEY (`USER_ID`,`ROLE_ID`),
  KEY `FK61x3nvxbu49xad90tp9f8kmo1` (`ROLE_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- Dumping data for table kryptominds_db.user_roles: 0 rows
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
