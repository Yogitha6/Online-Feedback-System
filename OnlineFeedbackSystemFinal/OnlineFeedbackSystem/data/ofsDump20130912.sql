CREATE DATABASE  IF NOT EXISTS `ofs` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ofs`;
-- MySQL dump 10.13  Distrib 5.6.11, for Win32 (x86)
--
-- Host: localhost    Database: ofs
-- ------------------------------------------------------
-- Server version	5.5.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employees` (
  `pmf` varchar(7) NOT NULL,
  `name` varchar(45) NOT NULL,
  `designation` varchar(100) NOT NULL,
  `team` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`pmf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES ('admin','Administrator','Administrator','CA'),('charo20','Rohini','ASE','APM'),('desra02','Ramchander','Director','SaaS Platform'),('gudra09','Rajesh','ASE','SDM'),('khapr01','Prabhjot Khandooja','PEC','Education'),('mahyo01','Yogitha','ASE','APM'),('maldi02','Divya','ASE','APM'),('raian02','Anubha','ASE','ITBM'),('visna03','Navneet','ASE','SaaS Platform');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedbacks`
--

DROP TABLE IF EXISTS `feedbacks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedbacks` (
  `fb_id` int(11) NOT NULL,
  `ans_general` varchar(3) DEFAULT NULL,
  `ans_trainer1` int(11) DEFAULT NULL,
  `ans_trainer2` int(11) DEFAULT NULL,
  `ans_material1` int(11) DEFAULT NULL,
  `ans_material2` int(11) DEFAULT NULL,
  `ans_facilities` int(11) DEFAULT NULL,
  `ans_value_most` varchar(300) DEFAULT NULL,
  `ans_value_least` varchar(300) DEFAULT NULL,
  `suggestion` varchar(300) DEFAULT NULL,
  `overal_score` int(11) NOT NULL,
  PRIMARY KEY (`fb_id`),
  UNIQUE KEY `fb_id_UNIQUE` (`fb_id`),
  CONSTRAINT `map_id` FOREIGN KEY (`fb_id`) REFERENCES `training_user_map` (`fb_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedbacks`
--

LOCK TABLES `feedbacks` WRITE;
/*!40000 ALTER TABLE `feedbacks` DISABLE KEYS */;
INSERT INTO `feedbacks` VALUES (16,'Yes',5,3,5,4,5,'	',' ',' ',4),(18,'Yes',5,5,5,5,5,'	dsa',' dasda',' dad',5);
/*!40000 ALTER TABLE `feedbacks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `user` varchar(7) NOT NULL,
  `password` varchar(16) NOT NULL,
  `role` char(1) NOT NULL,
  PRIMARY KEY (`user`),
  CONSTRAINT `user_pmf` FOREIGN KEY (`user`) REFERENCES `employees` (`pmf`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('admin','admin','a');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `training_user_map`
--

DROP TABLE IF EXISTS `training_user_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `training_user_map` (
  `fb_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_id` int(11) NOT NULL,
  `pmf` varchar(7) NOT NULL,
  PRIMARY KEY (`t_id`,`pmf`),
  UNIQUE KEY `fb_id_UNIQUE` (`fb_id`),
  KEY `user_pmf_idx` (`pmf`),
  KEY `training_tid_idx` (`t_id`),
  CONSTRAINT `map_user_pmf` FOREIGN KEY (`pmf`) REFERENCES `employees` (`pmf`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `training_tid` FOREIGN KEY (`t_id`) REFERENCES `trainings` (`t_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `training_user_map`
--

LOCK TABLES `training_user_map` WRITE;
/*!40000 ALTER TABLE `training_user_map` DISABLE KEYS */;
INSERT INTO `training_user_map` VALUES (15,14,'visna03'),(16,14,'mahyo01'),(18,15,'visna03'),(19,15,'mahyo01'),(20,15,'raian02'),(21,15,'gudra09');
/*!40000 ALTER TABLE `training_user_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trainings`
--

DROP TABLE IF EXISTS `trainings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trainings` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `topic` varchar(200) NOT NULL,
  `trainer` varchar(45) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  PRIMARY KEY (`topic`,`trainer`,`start_date`,`end_date`),
  UNIQUE KEY `t_id_UNIQUE` (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trainings`
--

LOCK TABLES `trainings` WRITE;
/*!40000 ALTER TABLE `trainings` DISABLE KEYS */;
INSERT INTO `trainings` VALUES (14,'Java','Suresh','2013-09-02','2013-09-12'),(15,'Python','Deepak Mishra','2013-09-09','2013-09-12');
/*!40000 ALTER TABLE `trainings` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-09-12 14:45:05
