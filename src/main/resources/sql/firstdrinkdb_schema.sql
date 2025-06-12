/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19-11.5.2-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: firstdrinkdb
-- ------------------------------------------------------
-- Server version	11.5.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */;

--
-- Table structure for table `alcohol`
--

DROP TABLE IF EXISTS `alcohol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alcohol` (
  `alcohol_number` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `barcode` varchar(20) DEFAULT NULL,
  `category` char(3) NOT NULL,
  `volume` varchar(50) NOT NULL,
  `price` varchar(80) NOT NULL,
  `content` decimal(5,2) NOT NULL,
  `avg_star` decimal(2,1) DEFAULT NULL,
  `ibu` int(4) DEFAULT NULL,
  `taste_detail` varchar(250) DEFAULT NULL,
  `taste` varchar(50) DEFAULT NULL,
  `aroma` varchar(50) DEFAULT NULL,
  `detail` text DEFAULT NULL,
  PRIMARY KEY (`alcohol_number`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `blob_test`
--

DROP TABLE IF EXISTS `blob_test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blob_test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `file_data` longblob DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `inqury`
--

DROP TABLE IF EXISTS `inqury`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inqury` (
  `inqury_num` int(10) NOT NULL AUTO_INCREMENT,
  `id` varchar(10) NOT NULL,
  `inqury_date` datetime NOT NULL,
  `title` varchar(200) NOT NULL,
  `info` text DEFAULT NULL,
  `picture` blob DEFAULT NULL,
  PRIMARY KEY (`inqury_num`),
  KEY `FK_member_TO_inqury_1` (`id`),
  CONSTRAINT `FK_member_TO_inqury_1` FOREIGN KEY (`id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member` (
  `id` varchar(50) NOT NULL,
  `nickname` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `phone` char(15) NOT NULL,
  `grade` int(1) NOT NULL DEFAULT 1 CHECK (`grade` >= 1 and `grade` <= 7),
  `point` int(4) NOT NULL DEFAULT 0,
  `view_num` int(4) NOT NULL DEFAULT 0,
  `tastenote_num` int(4) NOT NULL DEFAULT 0,
  `starpoint` decimal(2,1) NOT NULL DEFAULT 0.0,
  `common` char(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nickname` (`nickname`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
  `review_number` int(10) NOT NULL AUTO_INCREMENT,
  `id` varchar(10) NOT NULL,
  `common` enum('Y','N') NOT NULL COMMENT 'N(전문가), Y(일반)',
  `review_starpoint` decimal(2,1) NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `alcohol_number` int(4) NOT NULL,
  `review_info` text NOT NULL,
  `picture` blob DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`review_number`),
  KEY `FK_member_TO_review_1` (`id`),
  KEY `FK_alcohol_TO_review_1` (`alcohol_number`),
  CONSTRAINT `FK_alcohol_TO_review_1` FOREIGN KEY (`alcohol_number`) REFERENCES `alcohol` (`alcohol_number`),
  CONSTRAINT `FK_member_TO_review_1` FOREIGN KEY (`id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `scent`
--

DROP TABLE IF EXISTS `scent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scent` (
  `scent_number` int(3) NOT NULL AUTO_INCREMENT,
  `scent_info` varchar(10) NOT NULL,
  PRIMARY KEY (`scent_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `taste`
--

DROP TABLE IF EXISTS `taste`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `taste` (
  `taste_number` int(3) NOT NULL AUTO_INCREMENT,
  `taste_info` varchar(10) NOT NULL,
  PRIMARY KEY (`taste_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tastenote`
--

DROP TABLE IF EXISTS `tastenote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tastenote` (
  `tastenote_number` int(10) NOT NULL AUTO_INCREMENT,
  `id` varchar(10) NOT NULL,
  `alcohol_number` int(4) NOT NULL,
  `tastenote_starpoint` decimal(2,1) DEFAULT NULL,
  `creationdate` date NOT NULL,
  `tastenote_info` text DEFAULT NULL,
  `tastenote_format` text DEFAULT NULL,
  `taste_number` int(3) NOT NULL,
  `scent_number` int(3) NOT NULL,
  `open` enum('Y','N') NOT NULL DEFAULT 'N' COMMENT 'N(비공개), Y(공개)',
  `tasting_day` date NOT NULL,
  `memo` text DEFAULT NULL,
  `firstscent` varchar(200) DEFAULT NULL,
  `firstscent_value` varchar(200) DEFAULT NULL,
  `firstscent_date` datetime DEFAULT NULL,
  `middlescent` varchar(200) DEFAULT NULL,
  `middlescent_value` varchar(200) DEFAULT NULL,
  `middlescent_date` datetime DEFAULT NULL,
  `finalscent` varchar(200) DEFAULT NULL,
  `finalscent_value` varchar(200) DEFAULT NULL,
  `finalscent_date` datetime DEFAULT NULL,
  `glass` varchar(20) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `viscosity` int(1) DEFAULT NULL,
  `sugar` int(1) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `final_scent` varchar(255) DEFAULT NULL,
  `final_scent_date` datetime DEFAULT NULL,
  `first_scent` varchar(255) DEFAULT NULL,
  `first_scent_date` datetime DEFAULT NULL,
  `middle_scent` varchar(255) DEFAULT NULL,
  `middle_scent_date` datetime DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tastenote_number`),
  KEY `FK_member_TO_tastenote_1` (`id`),
  KEY `FK_alcohol_TO_tastenote_1` (`alcohol_number`),
  KEY `FK_taste_TO_tastenote_1` (`taste_number`),
  KEY `FK_Scent_TO_tastenote_1` (`scent_number`),
  CONSTRAINT `FK_Scent_TO_tastenote_1` FOREIGN KEY (`scent_number`) REFERENCES `scent` (`scent_number`),
  CONSTRAINT `FK_alcohol_TO_tastenote_1` FOREIGN KEY (`alcohol_number`) REFERENCES `alcohol` (`alcohol_number`),
  CONSTRAINT `FK_member_TO_tastenote_1` FOREIGN KEY (`id`) REFERENCES `member` (`id`),
  CONSTRAINT `FK_taste_TO_tastenote_1` FOREIGN KEY (`taste_number`) REFERENCES `taste` (`taste_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */;

-- Dump completed on 2025-06-12 13:08:09
