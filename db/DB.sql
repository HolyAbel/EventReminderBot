CREATE DATABASE IF NOT EXISTS event_reminder;

USE event_reminder;

DROP TABLE IF EXISTS `event`;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` text,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `chat_id` bigint NOT NULL,
  PRIMARY KEY (`id`,`chat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--
-- Dumping data for table `user`
--
LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES (NULL,'Nick','SaintAbel','1234',0),(NULL,'Kolya','EdenAver','1235',0);
UNLOCK TABLES;

CREATE TABLE `event` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `summary` text,
  `datetime` datetime NOT NULL,
  `duration` int NOT NULL,
  `type` tinyint NOT NULL,
  `is_end` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--
-- Dumping data for table `event`
--
LOCK TABLES `event` WRITE;
INSERT INTO `event` VALUES (NULL,1,'event','2025-10-21 15:00:00',10,1,0),(NULL,1,'EventToo','2024-10-20 12:00:00',33,2,0);
UNLOCK TABLES;
