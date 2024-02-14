-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: attendancesystem
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance` (
  `user_num` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) NOT NULL,
  `attendance_in` datetime DEFAULT NULL,
  `attendance_out` datetime DEFAULT NULL,
  `attendance_status` varchar(255) DEFAULT NULL,
  `attendance_date` date DEFAULT NULL,
  PRIMARY KEY (`user_num`),
  KEY `attendance_user_id_foreign` (`user_id`),
  CONSTRAINT `attendance_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` VALUES (1,'user_id_1','2024-02-13 22:10:28','2024-02-13 22:10:28','출석','2024-02-13'),(2,'user_id_2','2024-02-13 22:10:28','2024-02-13 22:10:28','출석','2024-02-13'),(3,'user_id_3','2024-02-13 22:10:28','2024-02-13 22:10:28','출석','2024-02-13'),(4,'user_id_4','2024-02-13 22:10:28','2024-02-13 22:10:28','출석','2024-02-14'),(5,'user_id_5','2024-02-13 22:10:28','2024-02-13 22:10:28','출석','2024-02-14'),(6,'user_id_6','2024-02-13 22:10:28','2024-02-13 22:10:28','출석','2024-02-13'),(7,'user_id_7','2024-02-13 22:10:28','2024-02-13 22:10:28','출석','2024-02-13'),(8,'user_id_8','2024-02-13 22:10:28','2024-02-13 22:10:28','출석','2024-02-13'),(9,'user_id_9','2024-02-13 22:10:28','2024-02-13 22:10:28','출석','2024-02-13'),(10,'user_id_10','2024-02-13 22:10:28','2024-02-13 22:10:28','출석','2024-02-13'),(17,'아이고',NULL,NULL,'not checked',NULL),(18,'아이고','2024-02-14 06:41:32','2024-02-14 12:05:02','출석',NULL),(19,'컴퓨터','2024-02-14 06:49:52',NULL,NULL,NULL),(20,'놀랍다','2024-02-14 11:42:01','2024-02-14 11:55:28','출석',NULL),(22,'user_id_18','2024-02-14 12:07:52','2024-02-14 12:08:08',NULL,NULL),(23,'user_id_14','2024-02-14 15:31:27',NULL,NULL,NULL),(24,'user_id_1','2024-02-14 15:33:24',NULL,NULL,NULL);
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-14 15:49:13
