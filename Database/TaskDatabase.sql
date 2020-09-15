-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: taskdatabase
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `taskinfo`
--

DROP TABLE IF EXISTS `taskinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taskinfo` (
  `taskid` int NOT NULL,
  `userid` int DEFAULT NULL,
  `taskname` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `rdate` date DEFAULT NULL,
  PRIMARY KEY (`taskid`),
  KEY `userid_fk_idx` (`userid`),
  CONSTRAINT `userid_fk` FOREIGN KEY (`userid`) REFERENCES `userinfo` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taskinfo`
--

LOCK TABLES `taskinfo` WRITE;
/*!40000 ALTER TABLE `taskinfo` DISABLE KEYS */;
INSERT INTO `taskinfo` VALUES (102,2,'play','playing','incomplete','2020-09-11'),(104,3,'learn java','OOPs Concept','incomplete','2020-09-13'),(105,2,'Python','practice','completed','2020-03-13'),(107,1,'learn python','coding','completed','2020-03-13'),(109,4,'learn python','tqpp','completed','2020-03-13'),(110,4,'run','running','completed','2020-03-13'),(111,4,'learn php','tutorial','Incomplete','2020-09-08'),(112,4,'learn react','tutorial','Incomplete','2020-07-13'),(113,6,'learn python','tutorial','Incomplete','2020-06-13'),(115,1,'learn angular','tutorial','completed','2020-06-13'),(116,4,'learn collection','about hashmap','Incomplete','2020-06-13'),(117,4,'learn .net','tutorial','Incomplete','2020-01-13'),(118,1,'learn react','coding','completed','2020-09-01'),(119,1,'js','coding','completed','2020-09-11'),(120,1,'learn code','code','completed','2020-04-13'),(121,12,'code','coding','completed','2020-08-13');
/*!40000 ALTER TABLE `taskinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userinfo`
--

DROP TABLE IF EXISTS `userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userinfo` (
  `userid` int NOT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `useremail` varchar(45) DEFAULT NULL,
  `userpassword` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `useraccess` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT 'active',
  `registerDate` date DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userinfo`
--

LOCK TABLES `userinfo` WRITE;
/*!40000 ALTER TABLE `userinfo` DISABLE KEYS */;
INSERT INTO `userinfo` VALUES (1,'Subrat','Jena','sjena35@gmail.com','Subrat@123','Male','nonadmin','active','2020-09-13'),(2,'Subrat','Kumar','subratkumar035@gmail.com','Kumar@123','male','nonadmin','active','2020-09-13'),(3,'Poorva','Singh','poorva1@gmail.com','Poorva@123','female','nonadmin','active','2020-09-13'),(4,'Jyoti','Narayan','jyoti@gmail.com','Jyoti@123','male','nonadmin','active','2020-09-02'),(6,'Abhisek','Sinha','abhisek@gmail.com','Abhisek@123','male','nonadmin','active','2020-09-01'),(7,'Admin',NULL,'admin@gmail.com','Admin@123','female','admin','active','2020-07-13'),(8,'neha','basu','neha@gmail.com','Neha@123','female','nonadmin','deactive','2020-09-09'),(9,'amit','singh','amit@gmail.com','Amit@123','male','nonadmin','deactive','2020-09-10'),(10,'abhijit','agarwal','abhijit@123','Abhijit@123','male','nonadmin','deactive','2020-04-11'),(11,'Anurag','Kumar','anurag@gmail.com','Anurag@123','male','nonadmin','active','2020-09-01'),(12,'Priyanka','mam','tqpriyanka@gmail.com','Abcd@123','female','nonadmin','active','2020-08-13'),(13,'Jay','Agarwal','sjena35@gmail.com','Jay@1234','male','nonadmin','active','2020-09-14'),(14,'Mohit','Sharma','skumarjena95@gmail.com','Mohit@123','male','nonadmin','active','2020-09-15');
/*!40000 ALTER TABLE `userinfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-15 16:27:27
