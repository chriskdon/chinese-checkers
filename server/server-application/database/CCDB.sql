CREATE DATABASE  IF NOT EXISTS `ccdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ccdb`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: ccdb
-- ------------------------------------------------------
-- Server version	5.6.16

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
-- Table structure for table `games`
--

DROP TABLE IF EXISTS `games`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `games` (
  `gameID` int(11) NOT NULL AUTO_INCREMENT,
  `numPlayer` int(1) NOT NULL,
  `isReady` int(1) NOT NULL DEFAULT '0',
  `currentTurn` int(1) NOT NULL DEFAULT '1',
  `created` datetime NOT NULL,
  `winnerID` int(11) DEFAULT NULL,
  PRIMARY KEY (`gameID`),
  KEY `winnerlink_idx` (`winnerID`),
  CONSTRAINT `winnerlink` FOREIGN KEY (`winnerID`) REFERENCES `users` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `games`
--

LOCK TABLES `games` WRITE;
/*!40000 ALTER TABLE `games` DISABLE KEYS */;
INSERT INTO `games` VALUES (6,2,1,1,'2014-03-12 14:57:07',1),(7,2,1,4,'2014-03-12 14:59:34',NULL);
/*!40000 ALTER TABLE `games` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `checkNumPlayeronInsert` BEFORE INSERT ON `games`
FOR EACH ROW
BEGIN
    IF (NEW.numPlayer < 2 OR new.numPlayer > 6 OR new.numPlayer = 5) THEN
    SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'check constraint on number of players failed';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `checkNumPlayeronUpdate` BEFORE UPDATE ON `games`
FOR EACH ROW
BEGIN
    IF (NEW.numPlayer < 2 OR new.numPlayer > 6 OR new.numPlayer = 5) THEN
    SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'check constraint on number of players failed';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `gamesusers`
--

DROP TABLE IF EXISTS `gamesusers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gamesusers` (
  `guID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) DEFAULT NULL,
  `gameID` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `playerNumber` int(1) NOT NULL,
  PRIMARY KEY (`guID`),
  KEY `gamelink_idx` (`gameID`),
  KEY `userlink_idx` (`username`,`userID`),
  KEY `gameslink_idx` (`gameID`),
  KEY `userlink_idx1` (`userID`),
  CONSTRAINT `gamelink` FOREIGN KEY (`gameID`) REFERENCES `games` (`gameID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `gameslink` FOREIGN KEY (`gameID`) REFERENCES `games` (`gameID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `userlink` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gamesusers`
--

LOCK TABLES `gamesusers` WRITE;
/*!40000 ALTER TABLE `gamesusers` DISABLE KEYS */;
INSERT INTO `gamesusers` VALUES (18,1,6,'Pete',1),(19,6,6,'Tim',4),(20,3,7,'Tim',1),(21,5,7,'Anonymous5',4);
/*!40000 ALTER TABLE `gamesusers` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `checkPlayerNumonInsert` BEFORE INSERT ON `gamesusers`
FOR EACH ROW
BEGIN
	DECLARE usr VARCHAR(255);
	SELECT username into usr FROM users where users.userID = NEW.userID;
    IF (NEW.playerNumber > 6 OR NEW.playerNumber < 0) THEN
    SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'check constraint on player number failed';
    END IF;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `gamesusers_AINS` AFTER INSERT ON `gamesusers` FOR EACH ROW
BEGIN
	DECLARE np, countPlayer INT;
	SELECT numPlayer into np from games where games.gameID = new.gameID;
	SELECT count(userID) into countPlayer from gamesusers where gamesusers.gameID = new.gameID;
	IF np = countPlayer THEN
		UPDATE games SET isReady=1 WHERE games.gameID = new.gameID;
	END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `checkPlayerNumBeforeUpdate` BEFORE UPDATE ON `gamesusers`
FOR EACH ROW
BEGIN
	DECLARE usr VARCHAR(255);
	SELECT username into usr FROM users where users.userID = NEW.userID;
    IF (NEW.playerNumber > 6 OR NEW.playerNumber < 0) THEN
    SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'check constraint on player number failed';
    END IF;
	
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `lookuptable`
--

DROP TABLE IF EXISTS `lookuptable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lookuptable` (
  `playerNumber` int(1) NOT NULL,
  `onRow` int(2) NOT NULL,
  `onIndex` int(2) NOT NULL,
  PRIMARY KEY (`playerNumber`,`onRow`,`onIndex`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lookuptable`
--

LOCK TABLES `lookuptable` WRITE;
/*!40000 ALTER TABLE `lookuptable` DISABLE KEYS */;
INSERT INTO `lookuptable` VALUES (1,0,0),(1,1,0),(1,1,1),(1,2,0),(1,2,1),(1,2,2),(1,3,0),(1,3,1),(1,3,2),(1,3,3),(2,4,9),(2,4,10),(2,4,11),(2,4,12),(2,5,9),(2,5,10),(2,5,11),(2,6,9),(2,6,10),(2,7,9),(3,9,9),(3,10,9),(3,10,10),(3,11,9),(3,11,10),(3,11,11),(3,12,9),(3,12,10),(3,12,11),(3,12,12),(4,13,0),(4,13,1),(4,13,2),(4,13,3),(4,14,0),(4,14,1),(4,14,2),(4,15,0),(4,15,1),(4,16,0),(5,9,0),(5,10,0),(5,10,1),(5,11,0),(5,11,1),(5,11,2),(5,12,0),(5,12,1),(5,12,2),(5,12,3),(6,4,0),(6,4,1),(6,4,2),(6,4,3),(6,5,0),(6,5,1),(6,5,2),(6,6,0),(6,6,1),(6,7,0);
/*!40000 ALTER TABLE `lookuptable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pieces`
--

DROP TABLE IF EXISTS `pieces`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pieces` (
  `guID` int(11) NOT NULL,
  `onRow` int(2) NOT NULL,
  `onIndex` int(2) NOT NULL,
  PRIMARY KEY (`guID`,`onRow`,`onIndex`),
  KEY `gameuser_idx` (`guID`),
  CONSTRAINT `usersgameslink` FOREIGN KEY (`guID`) REFERENCES `gamesusers` (`guID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pieces`
--

LOCK TABLES `pieces` WRITE;
/*!40000 ALTER TABLE `pieces` DISABLE KEYS */;
INSERT INTO `pieces` VALUES (21,1,0),(21,1,1),(21,2,0),(21,2,1),(21,2,3),(21,3,0),(21,3,1),(21,3,3),(21,3,4),(21,5,5);
/*!40000 ALTER TABLE `pieces` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `checkRowIndexBeforeInsert` BEFORE INSERT ON `pieces`
FOR EACH ROW
BEGIN
    IF (NEW.onRow > 16 OR NEW.onRow < 0) THEN
    SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'check constraint on player number failed';
    END IF;
	IF (NEW.onIndex > 12 OR NEW.onIndex < 0) THEN
    SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'check constraint on player number failed';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `checkRowIndexBeforeUpdate` BEFORE UPDATE ON `pieces`
FOR EACH ROW
BEGIN
    IF (NEW.onRow > 16 OR NEW.onRow < 0) THEN
    SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'check constraint on player number failed';
    END IF;
	IF (NEW.onIndex > 12 OR NEW.onIndex < 0) THEN
    SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'check constraint on player number failed';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `isAi` int(1) NOT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='Users table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Peter',0),(3,'Tim',0),(5,'Anonymous5',1),(6,'Anonymous6',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ccdb'
--
/*!50003 DROP PROCEDURE IF EXISTS `checkWinner` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `checkWinner`(IN uID INT, IN gID INT)
BEGIN
	DECLARE gu, pl, res INT;
	SELECT guID into gu from gamesusers where userID = uID AND gameID = gID;
	SELECT playerNumber into pl from gamesusers where userID = uID AND gameID = gID;
	SELECT count(c2.onRow) into res FROM (
		SELECT onRow, onIndex from pieces where guID = gu AND (pieces.onRow, pieces.onIndex) NOT IN (
			SELECT onRow, onIndex from lookuptable where playerNumber = pl
		) 
	) c2;
	if res > 0 THEN
		UPDATE games SET winnerID = uID WHERE gameID = gID;
	END IF;
	SELECT username FROM games JOIN users ON winnerID = userID WHERE winnerID = uID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `countPlayers` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `countPlayers`(IN gID INT, OUT count INT)
BEGIN
	SELECT COUNT(playerNumber) as count FROM gamesusers WHERE gameID=gID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `createAI` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `createAI`(OUT aiID INT)
BEGIN
	DECLARE usrname VARCHAR(50);
	DECLARE mID INT;
	SELECT MAX(userID) into mID from Users;
	SET usrname = concat("Anonymous", mID+1);
	INSERT INTO users (username, isAi) values (usrname, 1);
	SELECT userID into aiID from Users where username=usrname;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `createGame` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `createGame`(numPl INT(1))
BEGIN
	INSERT INTO games (numPlayer, created) values (numPl, NOW());
	SELECT LAST_INSERT_ID();
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `createUser` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `createUser`(IN usrname VARCHAR(50))
BEGIN
	INSERT INTO users (username, isAi) values (usrname, 0);
	SELECT userID from Users where username=usrname;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `deleteUser` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteUser`(IN uID INT)
BEGIN
	DECLARE checkAI, countGames INT;
	SELECT isAi into checkAI FROM users WHERE userID=uID;
	SELECT COUNT(gameID) INTO countGames FROM gamesusers WHERE userID=uID;
	IF (countGames = 0) THEN
		DELETE from users where userID = uID;
	ELSEIF (checkAI = 0) THEN
		CALL `createAI`(@aiID);
		UPDATE gamesusers SET userID = @aiID WHERE userID=uID;
		DELETE from users where userID = uID;
	ELSE	
		SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'AI user detected, deletion aborted';
	END IF;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `fillGame` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `fillGame`(IN gID INT)
BEGIN
	CALL `getNumberOfPlayers`(gID, @total);
	CALL `countPlayers`(gID, @num);
	filler: LOOP
		IF (@num = 1) THEN
			LEAVE filler;
		END IF;
		CALL `createAI`(@aiID);
		CALL `userJoinGame`(@aiID, gID);
		SET @num = @num+1;
	END LOOP filler;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getCreationTimes` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getCreationTimes`()
BEGIN
	SELECT gameID, created FROM games WHERE isReady=0; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getGamePieces` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getGamePieces`(IN gID INT)
BEGIN
	SELECT playerNumber, onRow, onIndex FROM gamesusers JOIN pieces ON gamesusers.guID = pieces.guID WHERE gameID=gID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getGameState` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getGameState`(IN gID INT)
BEGIN
	SELECT users.userID, users.username, gamesusers.playerNumber, users.isAi, games.currentTurn, games.winnerID FROM users 
	JOIN gamesusers ON users.userID = gamesusers.userID JOIN games ON games.gameID = gamesusers.gameID
	WHERE gamesusers.gameID=gID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getNumberOfPlayers` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getNumberOfPlayers`(IN gID INT, OUT numPl INT)
BEGIN
	SELECT numPlayer as numPl FROM games WHERE gameID=gID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getUserGames` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getUserGames`(IN uID INT)
BEGIN
	SELECT games.gameID, games.numPlayer, games.isReady, games.currentTurn, gamesusers.playerNumber, games.winnerID FROM gamesusers JOIN games 
		ON gamesusers.gameID = games.gameID WHERE gamesusers.userID = uID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getUsername` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getUsername`(IN uID INT)
BEGIN
	SELECT username FROM users WHERE userID=uID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getWinner` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getWinner`(IN gID INT)
BEGIN
	SELECT username FROM users JOIN games ON users.userID = games.winnerID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `matchMake` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `matchMake`(IN uID INT, IN numPlayers INT)
BEGIN
	DECLARE gID, playerNum INT;
	SELECT gameID into gID FROM games WHERE numPlayer = numPlayers AND isReady=0;
	IF gID IS NULL THEN
		INSERT INTO games (numPlayer, created) values (numPlayers, NOW());
		SELECT LAST_INSERT_ID() into gID;
		CALL `userJoinGame`(uID, gID);
	ELSE
		CALL `userJoinGame`(uID, gID);
	END IF;
	SELECT gID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `nextTurn` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `nextTurn`(IN gID INT(11))
BEGIN
	DECLARE nextTurn, numberPlayers, ct INT(1);
	SET nextTurn = 0;
	SELECT numPlayer into numberPlayers from games where gameID = gID;
	SELECT currentTurn into ct from games where gameID = gID;
	CASE numberPlayers
		WHEN 2 THEN
			SET nextTurn = mod((ct + 3), 6); 
		WHEN 3 THEN
			SET nextTurn = mod((ct + 2), 6); 
		WHEN 4 THEN
			IF (ct = 3 or ct = 6) THEN
				SET nextTurn = mod((ct + 1), 6);
			ELSE
				SET nextTurn = mod((ct + 2), 6);
			END IF;
		WHEN 6 THEN
			SET nextTurn = mod((ct + 1), 6); 
		ELSE 
			SET nextTurn = -1; 
	END CASE;
	IF nextTurn = 0 THEN
	SET nextTurn = 6;
	END IF;
	UPDATE games SET currentTurn = nextTurn WHERE gameID = gID;
	SELECT currentTurn FROM games WHERE gameID = gID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `populateBoard` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `populateBoard`(IN gID INT, IN playNum INT)
BEGIN
	DECLARE id INT;
	SELECT guID INTO id FROM gamesusers WHERE gameID = gID AND playerNumber = playNum;
	CASE playNum
	WHEN 1 THEN
		INSERT INTO pieces values (id, 16, 0);
		INSERT INTO pieces values (id, 15, 0);
		INSERT INTO pieces values (id, 15, 1);
		INSERT INTO pieces values (id, 14, 0);
		INSERT INTO pieces values (id, 14, 1);
		INSERT INTO pieces values (id, 14, 3);
		INSERT INTO pieces values (id, 13, 0);
		INSERT INTO pieces values (id, 13, 1);
		INSERT INTO pieces values (id, 13, 3);
		INSERT INTO pieces values (id, 13, 4);
	WHEN 2 THEN
		INSERT INTO pieces values (id, 9, 0);
		INSERT INTO pieces values (id, 10, 0);
		INSERT INTO pieces values (id, 10, 1);
		INSERT INTO pieces values (id, 11, 0);
		INSERT INTO pieces values (id, 11, 1);
		INSERT INTO pieces values (id, 11, 3);
		INSERT INTO pieces values (id, 12, 0);
		INSERT INTO pieces values (id, 12, 1);
		INSERT INTO pieces values (id, 12, 3);
		INSERT INTO pieces values (id, 12, 4);
	WHEN 3 THEN
		INSERT INTO pieces values (id, 7, 0);
		INSERT INTO pieces values (id, 6, 0);
		INSERT INTO pieces values (id, 6, 1);
		INSERT INTO pieces values (id, 5, 0);
		INSERT INTO pieces values (id, 5, 1);
		INSERT INTO pieces values (id, 5, 3);
		INSERT INTO pieces values (id, 4, 0);
		INSERT INTO pieces values (id, 4, 1);
		INSERT INTO pieces values (id, 4, 3);
		INSERT INTO pieces values (id, 4, 4);
	WHEN 4 THEN
		INSERT INTO pieces values (id, 0, 0);
		INSERT INTO pieces values (id, 1, 0);
		INSERT INTO pieces values (id, 1, 1);
		INSERT INTO pieces values (id, 2, 0);
		INSERT INTO pieces values (id, 2, 1);
		INSERT INTO pieces values (id, 2, 3);
		INSERT INTO pieces values (id, 3, 0);
		INSERT INTO pieces values (id, 3, 1);
		INSERT INTO pieces values (id, 3, 3);
		INSERT INTO pieces values (id, 3, 4);
	WHEN 5 THEN
		INSERT INTO pieces values (id, 7, 9);
		INSERT INTO pieces values (id, 6, 9);
		INSERT INTO pieces values (id, 6, 10);
		INSERT INTO pieces values (id, 5, 9);
		INSERT INTO pieces values (id, 5, 10);
		INSERT INTO pieces values (id, 5, 11);
		INSERT INTO pieces values (id, 4, 9);
		INSERT INTO pieces values (id, 4, 10);
		INSERT INTO pieces values (id, 4, 11);
		INSERT INTO pieces values (id, 4, 12);
	WHEN 6 THEN
		INSERT INTO pieces values (id, 9, 9);
		INSERT INTO pieces values (id, 10, 9);
		INSERT INTO pieces values (id, 10, 10);
		INSERT INTO pieces values (id, 11, 9);
		INSERT INTO pieces values (id, 11, 10);
		INSERT INTO pieces values (id, 11, 11);
		INSERT INTO pieces values (id, 12, 9);
		INSERT INTO pieces values (id, 12, 10);
		INSERT INTO pieces values (id, 12, 11);
		INSERT INTO pieces values (id, 12, 12);
	ELSE
		SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'invalid player number passed';
	END CASE;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `setUsername` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `setUsername`(IN uID INT, IN newname VARCHAR(50))
BEGIN
	UPDATE users SET username = newname where userID = uID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `setWinner` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `setWinner`(IN wID INT, IN gID INT)
BEGIN
	UPDATE games SET winnerID = uID WHERE gameID=gID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `updatePiece` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `updatePiece`(IN gID INT, IN oldRow INT, IN oldIndex INT, IN newRow INT, IN newIndex INT)
BEGIN
	UPDATE pieces JOIN gamesusers ON pieces.guID = gamesusers.guID
	SET pieces.onRow = newRow, pieces.onIndex = newIndex 
	WHERE gamesusers.gameID = gID AND pieces.onRow = oldRow AND pieces.onIndex = oldIndex;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `userJoinGame` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `userJoinGame`(IN uID INT, IN gID INT)
BEGIN
	DECLARE usr VARCHAR(255);
	DECLARE numPl, latestPl, playerNum INT;
	SELECT username into usr FROM users where userID = uID;
	SELECT numPlayer into numPl from games where gameID = gID;
	SELECT max(playerNumber) into latestPl from gamesusers where gameID = gID;
	IF latestPl IS NULL THEN
		INSERT into gamesusers (userID, gameID, username, playerNumber) values (uID, gID, usr, 1);
	ELSE
		CASE numPl
			WHEN 2 THEN
				INSERT into gamesusers (userID, gameID, username, playerNumber) values (uID, gID, usr, latestPl+3);
			WHEN 3 THEN 
				INSERT into gamesusers (userID, gameID, username, playerNumber) values (uID, gID, usr, latestPl+2);
			WHEN 4 THEN
				IF (latestPl = 3 or latestPl = 6) THEN
					INSERT into gamesusers (userID, gameID, username, playerNumber) values (uID, gID, usr, latestPl+1);
				ELSE
					INSERT into gamesusers (userID, gameID, username, playerNumber) values (uID, gID, usr, latestPl+2);
				END IF;
			WHEN 6 THEN
				INSERT into gamesusers (userID, gameID, username, playerNumber) values (uID, gID, usr, latestPl+1);
		END CASE;
	END IF;
	SELECT playerNumber into playerNum FROM gamesusers WHERE gameID = gID AND userID = uID;
	CALL `populateBoard`(gID, playerNum);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `userLeaveGame` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `userLeaveGame`(IN uID INT, IN gID INT)
BEGIN
	DECLARE game, pl, np, countAI, countHuman INT;
	SELECT gameID into game FROM gamesusers where userID=uID and gameID=gID;
	SELECT playerNumber into pl FROM gamesusers where userID=uID and gameID=gID;
	CALL `createAI`(@aiID);
	UPDATE gamesusers SET userID = @aiID where userID=uID and gameID=gID;
	SELECT count(isAi) into countHuman from users join gamesusers on users.userID = gamesusers.userID where gamesusers.gameID = gID AND users.isAi=0;
	IF countHuman = 0 THEN
		DELETE FROM gamesusers WHERE gamesusers.gameID = gID;
		DELETE FROM games WHERE gameID=gID;
		DELETE FROM users WHERE isAi = 1 AND users.userID NOT IN (
					SELECT userID FROM gamesusers
			);
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-03-23 17:42:26
