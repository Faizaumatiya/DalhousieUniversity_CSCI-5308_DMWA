CREATE DATABASE  IF NOT EXISTS `a2_dist<b00899642>` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `a2_dist<b00899642>`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: a2_dist<b00899642>
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `allowed items`
--

DROP TABLE IF EXISTS `allowed items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `allowed items` (
  `Item_id` int NOT NULL,
  `Quantity` varchar(45) NOT NULL,
  `Charge` varchar(45) NOT NULL,
  `Name` varchar(45) NOT NULL,
  PRIMARY KEY (`Item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allowed items`
--

LOCK TABLES `allowed items` WRITE;
/*!40000 ALTER TABLE `allowed items` DISABLE KEYS */;
INSERT INTO `allowed items` VALUES (1,'12','$30','Food'),(2,'20','$14','Drink'),(3,'8','$10','Bag'),(4,'5','$22','Clothes'),(5,'2','$50','Toys');
/*!40000 ALTER TABLE `allowed items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `camp program`
--

DROP TABLE IF EXISTS `camp program`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `camp program` (
  `Cp_id` int NOT NULL,
  `Cp_name` varchar(45) NOT NULL,
  `Duration` varchar(45) NOT NULL,
  `fess` varchar(45) NOT NULL,
  PRIMARY KEY (`Cp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `camp program`
--

LOCK TABLES `camp program` WRITE;
/*!40000 ALTER TABLE `camp program` DISABLE KEYS */;
INSERT INTO `camp program` VALUES (111,'Rock Creek','5 days','$100'),(222,'West Canyon','4 days','$150'),(333,'Horseshoe Bend','6 days','$80'),(444,'Thorntree Lake','4 days','$120'),(555,'Walnut Ridge','7 days','$250');
/*!40000 ALTER TABLE `camp program` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job applications`
--

DROP TABLE IF EXISTS `job applications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job applications` (
  `JA_id` int NOT NULL,
  `Requirement` varchar(45) NOT NULL,
  `Deadline` varchar(45) NOT NULL,
  `JA_name` varchar(45) NOT NULL,
  PRIMARY KEY (`JA_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job applications`
--

LOCK TABLES `job applications` WRITE;
/*!40000 ALTER TABLE `job applications` DISABLE KEYS */;
INSERT INTO `job applications` VALUES (1100,'Resume','12-6-22','Developer'),(1200,'Skills','20-7-22','Software Engineer'),(1300,'Work Experience','25-7-22','Cashier'),(1400,'Volunteering','2-8-22','Park attendant'),(1500,'Extra-curricular','10-8-22','Manager');
/*!40000 ALTER TABLE `job applications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `newsletter`
--

DROP TABLE IF EXISTS `newsletter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `newsletter` (
  `newletter_id` int NOT NULL,
  `newsletter_name` varchar(45) NOT NULL,
  `newletter_type` varchar(45) NOT NULL,
  PRIMARY KEY (`newletter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `newsletter`
--

LOCK TABLES `newsletter` WRITE;
/*!40000 ALTER TABLE `newsletter` DISABLE KEYS */;
INSERT INTO `newsletter` VALUES (1001,'Camping Season','Organization'),(2002,'Land Protection','Company'),(3003,'Campsite Bookings','Consumer'),(4004,'Job openings','Consumer'),(5005,'Park Availability','Organization');
/*!40000 ALTER TABLE `newsletter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `park`
--

DROP TABLE IF EXISTS `park`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `park` (
  `Park_id` int NOT NULL,
  `Check_in` varchar(45) NOT NULL,
  `Check_out` varchar(45) NOT NULL,
  `Availability` varchar(45) NOT NULL,
  `City` varchar(45) NOT NULL,
  `Location` varchar(45) NOT NULL,
  PRIMARY KEY (`Park_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `park`
--

LOCK TABLES `park` WRITE;
/*!40000 ALTER TABLE `park` DISABLE KEYS */;
INSERT INTO `park` VALUES (210,'2:00 pm','3:00pm','Sunday','Halifax','Bedford'),(310,'3:00pm','4:00pm','Monday','Truro','Mahone Bay'),(410,'4:00pm','5:00pm','Tuesday','Yarmouth','Frost Park'),(510,'5:00pm','6:00pm','Wednesday','Sydney','Breton Island'),(610,'6:00pm','7:00pm','Thursday','Dartmouth','Oak Island');
/*!40000 ALTER TABLE `park` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `park advisories`
--

DROP TABLE IF EXISTS `park advisories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `park advisories` (
  `PA_id` int NOT NULL,
  `PA_name` varchar(45) NOT NULL,
  `PA_type` varchar(45) NOT NULL,
  `PA_contact` varchar(45) NOT NULL,
  PRIMARY KEY (`PA_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `park advisories`
--

LOCK TABLES `park advisories` WRITE;
/*!40000 ALTER TABLE `park advisories` DISABLE KEYS */;
INSERT INTO `park advisories` VALUES (1230,'Algonquin','City Archivis','810-895-2356'),(1340,'Alexander','Committee','234-896-4442'),(1450,'Arrowhead','Directors','178-009-9234'),(1560,'Awenda','Management','234-654-7777'),(1670,'Bonnechere','City Archivis','190-623-8356');
/*!40000 ALTER TABLE `park advisories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `park event history`
--

DROP TABLE IF EXISTS `park event history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `park event history` (
  `Record_id` int NOT NULL,
  `Record_name` varchar(45) NOT NULL,
  `Record_num` varchar(45) NOT NULL,
  PRIMARY KEY (`Record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `park event history`
--

LOCK TABLES `park event history` WRITE;
/*!40000 ALTER TABLE `park event history` DISABLE KEYS */;
INSERT INTO `park event history` VALUES (55,'Contact','205'),(66,'Address','307'),(77,'Camp details','412'),(88,'infrastructure','390'),(99,'Finance','1700');
/*!40000 ALTER TABLE `park event history` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-18 13:59:22
