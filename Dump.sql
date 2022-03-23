-- MySQL dump 10.13  Distrib 8.0.22, for macos10.15 (x86_64)
--
-- Host: localhost    Database: Library
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Book`
--

DROP TABLE IF EXISTS `Book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `store_cnt` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Book`
--

LOCK TABLES `Book` WRITE;
/*!40000 ALTER TABLE `Book` DISABLE KEYS */;
INSERT INTO `Book` VALUES (2,'离散数学2',14),(3,'线性代数',333312),(5,'test',0),(6,'test2',0),(9,'test3',2),(10,'test4',12),(11,'test5',2),(12,'test6',9),(13,'test7',2),(14,'test8',333308),(15,'test9',5),(16,'test10',5),(17,'test11',1),(19,'test13',2),(20,'test14',2),(21,'test15',2),(22,'32131',2),(23,'dasdsa',3),(27,'233334',2),(28,'5555',2),(30,'dsdsadsa',4),(32,'asss',4),(33,'asssf',4),(34,'fuckjava',4),(36,'{',4),(37,'return 0;',4),(38,'}',4),(40,'test16',2),(41,'焯',7),(42,'离散数学',2),(43,'miaowow',44),(44,'Hello World',100),(45,'喵呜',233);
/*!40000 ALTER TABLE `Book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `HistoryUser`
--

DROP TABLE IF EXISTS `HistoryUser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `HistoryUser` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Date_s` datetime NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `book` varchar(100) NOT NULL,
  `state` tinyint NOT NULL DEFAULT '0' COMMENT '0借，1还',
  `Date_e` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `HistoryUser`
--

LOCK TABLES `HistoryUser` WRITE;
/*!40000 ALTER TABLE `HistoryUser` DISABLE KEYS */;
INSERT INTO `HistoryUser` VALUES (1,'2021-12-30 12:34:35','t','离散数学2',1,'2021-12-30 12:34:37'),(2,'2021-12-30 12:34:44','t','test11',1,'2021-12-30 12:35:05'),(3,'2021-12-30 12:34:54','t','离散数学2',1,'2021-12-30 12:35:02'),(4,'2021-12-30 12:36:09','t','离散数学2',1,'2021-12-30 12:36:17'),(5,'2021-12-30 12:36:19','t','离散数学2',1,'2021-12-30 12:36:21'),(6,'2021-12-30 12:36:28','t','焯',0,NULL),(7,'2021-12-30 12:36:44','admin','dasdsa',0,NULL),(8,'2021-12-30 12:39:47','test','喵呜',1,'2021-12-30 12:39:51'),(9,'2021-12-30 12:39:57','test','喵呜',1,'2021-12-30 12:39:59'),(10,'2021-12-30 12:40:25','test11','离散数学2',0,NULL),(11,'2021-12-30 12:40:44','admin','焯',1,'2021-12-30 12:40:49');
/*!40000 ALTER TABLE `HistoryUser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `manager` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'admin','admin',1),(2,'test','test',0),(3,'test1','test',1),(4,'wahahaa','23333',0),(5,'test2','1234',1),(6,'test3','1234',0),(18,'aaa','aaa',1),(20,'test5','1234',1),(23,'wow','123456789',0),(24,'wow1','123456789',0),(25,'wow2','233',0),(26,'mon','12345',0),(27,'t','t',0),(28,'你好啊','233',0),(29,'test11','test',0);
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-30 12:47:36
