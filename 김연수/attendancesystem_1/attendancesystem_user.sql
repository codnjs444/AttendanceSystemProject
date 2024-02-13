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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` varchar(255) NOT NULL,
  `user_idValue` varchar(255) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `user_birth` varchar(255) NOT NULL,
  `user_address` varchar(255) NOT NULL,
  `user_gender` varchar(255) NOT NULL,
  `user_phoneNum` varchar(255) NOT NULL,
  `user_major` varchar(255) NOT NULL,
  `class_name` varchar(255) NOT NULL,
  `access_level` int NOT NULL,
  `profile_image` blob NOT NULL,
  `profile_name` varchar(255) NOT NULL,
  `profile_uploadName` varchar(255) NOT NULL,
  `profile_extension` varchar(255) NOT NULL,
  `profile_size` int NOT NULL,
  `class_num` int DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('user_id_1','1','password_1','Name 1','1990-01-01','Address 1','Male','123456789','Major 1','java반',1,_binary 'image_blob_1','Profile Name 1','Upload Name 1','png',100,5),('user_id_10','1','password_10','Name 10','1999-10-10','Address 10','Female','1212121212','Major 10','java반',10,_binary 'image_blob_10','Profile Name 10','Upload Name 10','jpeg',550,5),('user_id_11','1','password_11','Name 11','2000-11-11','Address 11','Male','1313131313','Major 11','java반',11,_binary 'image_blob_11','Profile Name 11','Upload Name 11','jpg',600,5),('user_id_12','1','password_12','Name 12','2001-12-12','Address 12','Female','1414141414','Major 12','cad1급반',12,_binary 'image_blob_12','Profile Name 12','Upload Name 12','png',650,6),('user_id_13','0','password_13','Name 13','2002-01-13','Address 13','Male','1515151515','Major 13','cad1급반',13,_binary 'image_blob_13','Profile Name 13','Upload Name 13','jpeg',700,6),('user_id_14','0','password_14','Name 14','2003-02-14','Address 14','Female','1616161616','Major 14','cad1급반',14,_binary 'image_blob_14','Profile Name 14','Upload Name 14','jpg',750,6),('user_id_15','0','password_15','Name 15','2004-03-15','Address 15','Male','1717171717','Major 15','cad2급반',15,_binary 'image_blob_15','Profile Name 15','Upload Name 15','png',800,7),('user_id_16','0','password_16','Name 16','2005-04-16','Address 16','Female','1818181818','Major 16','cad2급반',16,_binary 'image_blob_16','Profile Name 16','Upload Name 16','jpeg',850,7),('user_id_17','0','password_17','Name 17','2006-05-17','Address 17','Male','1919191919','Major 17','cad2급반',17,_binary 'image_blob_17','Profile Name 17','Upload Name 17','jpg',900,7),('user_id_18','1','password_18','Name 18','2007-06-18','Address 18','Female','2020202020','Major 18','컴활반',18,_binary 'image_blob_18','Profile Name 18','Upload Name 18','png',950,8),('user_id_19','1','password_19','Name 19','2008-07-19','Address 19','Male','2121212121','Major 19','컴활반',19,_binary 'image_blob_19','Profile Name 19','Upload Name 19','jpeg',1000,8),('user_id_2','1','password_2','Name 2','1991-02-02','Address 2','Female','987654321','Major 2','컴활반',2,_binary 'image_blob_2','Profile Name 2','Upload Name 2','jpg',150,8),('user_id_20','1','password_20','Name 20','2009-08-20','Address 20','Female','2222222222','Major 20','java반',20,_binary 'image_blob_20','Profile Name 20','Upload Name 20','jpg',1050,5),('user_id_3','1','password_3','Name 3','1992-03-03','Address 3','Male','555555555','Major 3','java반',3,_binary 'image_blob_3','Profile Name 3','Upload Name 3','jpeg',200,5),('user_id_4','1','password_4','Name 4','1993-04-04','Address 4','Female','333333333','Major 4','java반',4,_binary 'image_blob_4','Profile Name 4','Upload Name 4','png',250,5),('user_id_5','0','password_5','Name 5','1994-05-05','Address 5','Male','777777777','Major 5','java반',5,_binary 'image_blob_5','Profile Name 5','Upload Name 5','jpg',300,5),('user_id_6','0','password_6','Name 6','1995-06-06','Address 6','Female','888888888','Major 6','java반',6,_binary 'image_blob_6','Profile Name 6','Upload Name 6','png',350,5),('user_id_7','0','password_7','Name 7','1996-07-07','Address 7','Male','999999999','Major 7','java반',7,_binary 'image_blob_7','Profile Name 7','Upload Name 7','jpeg',400,5),('user_id_8','0','password_8','Name 8','1997-08-08','Address 8','Female','1010101010','Major 8','java반',8,_binary 'image_blob_8','Profile Name 8','Upload Name 8','jpg',450,5),('user_id_9','0','password_9','Name 9','1998-09-09','Address 9','Male','1111111111','Major 9','java반',9,_binary 'image_blob_9','Profile Name 9','Upload Name 9','png',500,5);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-13  6:02:38
