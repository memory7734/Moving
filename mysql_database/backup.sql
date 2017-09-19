-- MySQL dump 10.13  Distrib 5.6.25-73.1, for Linux (x86_64)
--
-- Host: localhost    Database: movingmaster
-- ------------------------------------------------------
-- Server version	5.6.25-73.1

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
-- Table structure for table `user_daily_fit`
--

DROP TABLE IF EXISTS `user_daily_fit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_daily_fit` (
  `date_time` int(11) NOT NULL,
  `userkey` int(11) NOT NULL,
  `activity` decimal(18,0) DEFAULT NULL,
  `activite_energy` decimal(18,0) DEFAULT NULL,
  `cycling_distance` decimal(18,0) DEFAULT NULL,
  `exercise_minutes` decimal(10,0) DEFAULT NULL,
  `walking_running_distance` decimal(18,0) DEFAULT NULL,
  `step` decimal(10,0) DEFAULT NULL,
  `blood_glucose` decimal(18,0) DEFAULT NULL,
  `forced_vital_vapacity` decimal(18,0) DEFAULT NULL,
  `blood_alcohol_content` decimal(18,0) DEFAULT NULL,
  `height` decimal(18,0) DEFAULT NULL,
  `weight` decimal(18,0) DEFAULT NULL,
  PRIMARY KEY (`date_time`,`userkey`),
  KEY `FK_userkey` (`userkey`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_daily_fit`
--

LOCK TABLES `user_daily_fit` WRITE;
/*!40000 ALTER TABLE `user_daily_fit` DISABLE KEYS */;
INSERT INTO `user_daily_fit` VALUES (20170903,1,80,450,3000,100,10000,4000,70,2500,0,175,75),(20170904,1,90,500,1600,150,12000,4500,75,2600,25,175,75),(20170902,1,100,400,1500,100,10000,4500,75,2600,20,175,75),(20170901,1,90,450,1500,100,12000,4200,75,2600,0,175,75),(20170831,1,20,500,2000,80,11000,4200,70,2500,20,175,0),(20170829,1,500,600,0,0,0,0,0,0,0,0,0),(20170830,1,0,0,1600,80,12000,6000,75,0,0,175,0),(20170801,0,100,400,1600,100,10000,4200,3,2500,20,175,58);
/*!40000 ALTER TABLE `user_daily_fit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `userkey` int(11) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `birthdate` int(11) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `wheelchair` varchar(255) DEFAULT NULL,
  `blood_type` varchar(255) DEFAULT NULL,
  `fitzpatrick_skin_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userkey`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (0,'15115115151','lys2','123132',0,'wuhan',NULL,NULL,NULL),(1,'15027885651','lys','123123',NULL,'wuhan',NULL,NULL,NULL),(2,'15176250957','lys4','123123',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-19 18:39:03
