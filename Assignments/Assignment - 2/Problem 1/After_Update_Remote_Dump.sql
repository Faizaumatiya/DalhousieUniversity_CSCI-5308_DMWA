CREATE DATABASE  IF NOT EXISTS `a2_rem<b00899642>` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `a2_rem<b00899642>`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 35.238.156.72    Database: a2_rem<b00899642>
-- ------------------------------------------------------
-- Server version	8.0.26-google

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '0e7bf806-e9d0-11ec-8e0b-42010a800003:1-55560';

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
INSERT INTO `allowed items` VALUES (1,'12','$30','Food'),(2,'20',' $24','Drink'),(3,'8','$10','Bag'),(4,'5','$22','Clothes'),(5,'2','$50','Toys');
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
-- Table structure for table `campsite`
--

DROP TABLE IF EXISTS `campsite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `campsite` (
  `Campsite_id` int NOT NULL,
  `Capacity` int NOT NULL,
  `Duration` varchar(45) NOT NULL,
  `Location` varchar(45) NOT NULL,
  `Campsite_name` varchar(45) NOT NULL,
  PRIMARY KEY (`Campsite_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campsite`
--

LOCK TABLES `campsite` WRITE;
/*!40000 ALTER TABLE `campsite` DISABLE KEYS */;
INSERT INTO `campsite` VALUES (101,20,'3 days','Halifax','Summer Camp '),(201,30,'4 days','Halifax','Hill Wood'),(301,15,'7 days','Bedford','Golden'),(401,5,'10 days','Dartmouth','Green Meadows'),(501,25,'2 days','Sackville','Majestic Nature Reserve');
/*!40000 ALTER TABLE `campsite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipments`
--

DROP TABLE IF EXISTS `equipments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipments` (
  `E_id` int NOT NULL,
  `E_name` varchar(45) NOT NULL,
  `E_type` varchar(45) NOT NULL,
  `No._of_equipments` int NOT NULL,
  PRIMARY KEY (`E_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipments`
--

LOCK TABLES `equipments` WRITE;
/*!40000 ALTER TABLE `equipments` DISABLE KEYS */;
INSERT INTO `equipments` VALUES (12,' Sleeping bags','mandatory',2),(13,' Mess kits ','optional',3),(14,'Medications ','mandatory',5),(15,'water shoes','mandatory',7),(16,'Tent','optional',1);
/*!40000 ALTER TABLE `equipments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event` (
  `E_id` int NOT NULL,
  `E_timimgs` varchar(45) NOT NULL,
  `E_list` varchar(45) NOT NULL,
  `E_fees` varchar(45) NOT NULL,
  PRIMARY KEY (`E_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (22,'2:00 pm','Dance Paradise ','$100'),(23,'2:15pm','Boogie Horizon','$70'),(24,'10:00am','Festivscape','$120'),(25,'12:00pm','Gala Jungle ','$50'),(26,'11:30pm','Music Heritage','$110');
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facilities`
--

DROP TABLE IF EXISTS `facilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facilities` (
  `F_id` int NOT NULL,
  `Indoor` varchar(45) NOT NULL,
  `Outdoor` varchar(45) NOT NULL,
  `F_name` varchar(45) NOT NULL,
  `No._of_facilities` int NOT NULL,
  PRIMARY KEY (`F_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facilities`
--

LOCK TABLES `facilities` WRITE;
/*!40000 ALTER TABLE `facilities` DISABLE KEYS */;
INSERT INTO `facilities` VALUES (345,'PlayBox','Picnic','Parking',2),(445,'Magic Valley','Trail','Walkways',3),(545,'Black cultural','Wildlife Observation','Toilet',6),(645,'Museum','Game','Benches',10),(745,'Craft & Design','Hunting','Cottages',4);
/*!40000 ALTER TABLE `facilities` ENABLE KEYS */;
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
INSERT INTO `job applications` VALUES (1100,'Resume','12-6-22','Developer'),(1200,'Skills','20-7-22','Software Engineer'),(1300,'Work Experience','25-7-22','Cashier'),(1400,'Education Background','2-8-22','Park attendant'),(1500,'Extra-curricular','10-8-22','Manager');
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
INSERT INTO `park` VALUES (210,'2:00 pm','3:00pm','Sunday','Halifax','Bedford'),(310,'3:00pm','4:00pm','Monday','Truro','Mahone Bay'),(410,'4:00pm','5:00pm','Tuesday','Yarmouth','Frost Park'),(510,'5:00pm','6:00pm','Wednesday','Sydney','Breton Island'),(610,'6:00pm','7:00pm','Thursday','Chester','Oak Island');
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

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservations` (
  `Reservation_id` int NOT NULL,
  `Reservation_fees` varchar(45) NOT NULL,
  `Park_name` varchar(45) NOT NULL,
  `Arrival_date` varchar(45) NOT NULL,
  `Departure_date` varchar(45) NOT NULL,
  `Party_size` int NOT NULL,
  PRIMARY KEY (`Reservation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
INSERT INTO `reservations` VALUES (211,'$150','Dark Sky Preserve','12-1-22','21-2-22',21),(311,'$250','Boat Launch','3-3-22','17-3-22',30),(411,'$300',' Mi’kmaw heritage','23-3-22','31-3-22',50),(511,'$200','Otentik','14-4-22','25-4-22',45),(611,'$350','Hiking Trails','30-5-22','10-6-22',35);
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendor`
--

DROP TABLE IF EXISTS `vendor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vendor` (
  `V_id` int NOT NULL,
  `V_name` varchar(45) NOT NULL,
  `Emailid` varchar(45) NOT NULL,
  `Address` varchar(45) NOT NULL,
  PRIMARY KEY (`V_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendor`
--

LOCK TABLES `vendor` WRITE;
/*!40000 ALTER TABLE `vendor` DISABLE KEYS */;
INSERT INTO `vendor` VALUES (610,'Alex','alex@gmail.cam','Halifax'),(620,'Greg','greg@gmail.com','Truro'),(630,'Meet','meet@gmail.com','Sydney'),(640,'Justin','justin@yahoo.com','Chester'),(650,'Oliver','robert@yahoo.com','Yarmouth');
/*!40000 ALTER TABLE `vendor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visitor`
--

DROP TABLE IF EXISTS `visitor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `visitor` (
  `v_id` int NOT NULL,
  `v_age` int NOT NULL,
  `v_phone` varchar(45) NOT NULL,
  `v_name` varchar(45) NOT NULL,
  PRIMARY KEY (`v_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visitor`
--

LOCK TABLES `visitor` WRITE;
/*!40000 ALTER TABLE `visitor` DISABLE KEYS */;
INSERT INTO `visitor` VALUES (1200,23,'233-456-9988','Smith'),(1300,33,'934-879-0022','Lucci'),(1400,38,'899-903-4567','Mikylah'),(1500,45,'867-234-9344','Sarrah'),(1600,48,'976-635-9521','Austin');
/*!40000 ALTER TABLE `visitor` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-18 13:47:22
