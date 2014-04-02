SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

SHOW WARNINGS;
CREATE SCHEMA IF NOT EXISTS `ccdb` DEFAULT CHARACTER SET utf8 ;
SHOW WARNINGS;
USE `ccdb` ;

-- -----------------------------------------------------
-- Table `ccdb`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ccdb`.`users` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `ccdb`.`users` (
  `userID` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `isAi` INT(1) NOT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8
COMMENT = 'Users table';

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `ccdb`.`games`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ccdb`.`games` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `ccdb`.`games` (
  `gameID` INT(11) NOT NULL AUTO_INCREMENT,
  `numPlayer` INT(1) NOT NULL,
  `isReady` INT(1) NOT NULL DEFAULT '0',
  `currentTurn` INT(1) NOT NULL DEFAULT '1',
  `created` DATETIME NOT NULL,
  `winnerID` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`gameID`),
  INDEX `winnerlink_idx` (`winnerID` ASC),
  CONSTRAINT `winnerlink`
    FOREIGN KEY (`winnerID`)
    REFERENCES `ccdb`.`users` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `ccdb`.`gamesusers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ccdb`.`gamesusers` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `ccdb`.`gamesusers` (
  `guID` INT(11) NOT NULL AUTO_INCREMENT,
  `userID` INT(11) NULL DEFAULT NULL,
  `gameID` INT(11) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `playerNumber` INT(1) NOT NULL,
  PRIMARY KEY (`guID`),
  INDEX `gamelink_idx` (`gameID` ASC),
  INDEX `userlink_idx` (`username` ASC, `userID` ASC),
  INDEX `gameslink_idx` (`gameID` ASC),
  INDEX `userlink_idx1` (`userID` ASC),
  CONSTRAINT `gamelink`
    FOREIGN KEY (`gameID`)
    REFERENCES `ccdb`.`games` (`gameID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `gameslink`
    FOREIGN KEY (`gameID`)
    REFERENCES `ccdb`.`games` (`gameID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `userlink`
    FOREIGN KEY (`userID`)
    REFERENCES `ccdb`.`users` (`userID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 41
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `ccdb`.`lookuptable`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ccdb`.`lookuptable` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `ccdb`.`lookuptable` (
  `playerNumber` INT(1) NOT NULL,
  `onRow` INT(2) NOT NULL,
  `onIndex` INT(2) NOT NULL,
  PRIMARY KEY (`playerNumber`, `onRow`, `onIndex`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `ccdb`.`pieces`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ccdb`.`pieces` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `ccdb`.`pieces` (
  `guID` INT(11) NOT NULL,
  `onRow` INT(2) NOT NULL,
  `onIndex` INT(2) NOT NULL,
  PRIMARY KEY (`guID`, `onRow`, `onIndex`),
  INDEX `gameuser_idx` (`guID` ASC),
  CONSTRAINT `usersgameslink`
    FOREIGN KEY (`guID`)
    REFERENCES `ccdb`.`gamesusers` (`guID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;
USE `ccdb` ;

-- -----------------------------------------------------
-- procedure AiJoinGame
-- -----------------------------------------------------

USE `ccdb`;
DROP procedure IF EXISTS `ccdb`.`AiJoinGame`;
SHOW WARNINGS;

DELIMITER $$
USE `ccdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `AiJoinGame`(IN gID INT)
BEGIN
	CALL `createAI`(@aiID);
	CALL `userJoinGame`(@aiID, gID);
	SELECT COUNT(userID) from gamesusers WHERE gameID = gID;
END$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure checkWinner
-- -----------------------------------------------------

USE `ccdb`;
DROP procedure IF EXISTS `ccdb`.`checkWinner`;
SHOW WARNINGS;

DELIMITER $$
USE `ccdb`$$
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
END$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure countPlayers
-- -----------------------------------------------------

USE `ccdb`;
DROP procedure IF EXISTS `ccdb`.`countPlayers`;
SHOW WARNINGS;

DELIMITER $$
USE `ccdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `countPlayers`(IN gID INT, OUT count INT)
BEGIN
	SELECT COUNT(playerNumber) as count FROM gamesusers WHERE gameID=gID;
END$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure createAI
-- -----------------------------------------------------

USE `ccdb`;
DROP procedure IF EXISTS `ccdb`.`createAI`;
SHOW WARNINGS;

DELIMITER $$
USE `ccdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createAI`(OUT aiID INT)
BEGIN
	DECLARE usrname VARCHAR(50);
	DECLARE mID INT;
	SELECT MAX(userID) into mID from Users;
	SET usrname = concat("Anonymous", mID+1);
	INSERT INTO users (username, isAi) values (usrname, 1);
	SELECT userID into aiID from Users where username=usrname;
END$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure createGame
-- -----------------------------------------------------

USE `ccdb`;
DROP procedure IF EXISTS `ccdb`.`createGame`;
SHOW WARNINGS;

DELIMITER $$
USE `ccdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createGame`(numPl INT(1))
BEGIN
	INSERT INTO games (numPlayer, created) values (numPl, NOW());
	SELECT LAST_INSERT_ID() as gID;
END$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure createUser
-- -----------------------------------------------------

USE `ccdb`;
DROP procedure IF EXISTS `ccdb`.`createUser`;
SHOW WARNINGS;

DELIMITER $$
USE `ccdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createUser`(IN usrname VARCHAR(50))
BEGIN
	INSERT INTO users (username, isAi) values (usrname, 0);
	SELECT userID from Users where username=usrname;
END$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure deleteUser
-- -----------------------------------------------------

USE `ccdb`;
DROP procedure IF EXISTS `ccdb`.`deleteUser`;
SHOW WARNINGS;

DELIMITER $$
USE `ccdb`$$
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
	
END$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure getCreationTimes
-- -----------------------------------------------------

USE `ccdb`;
DROP procedure IF EXISTS `ccdb`.`getCreationTimes`;
SHOW WARNINGS;

DELIMITER $$
USE `ccdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getCreationTimes`()
BEGIN
	SELECT gameID, created FROM games WHERE isReady=0; 
END$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure getGamePieces
-- -----------------------------------------------------

USE `ccdb`;
DROP procedure IF EXISTS `ccdb`.`getGamePieces`;
SHOW WARNINGS;

DELIMITER $$
USE `ccdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getGamePieces`(IN gID INT)
BEGIN
	SELECT playerNumber, onRow, onIndex FROM gamesusers JOIN pieces ON gamesusers.guID = pieces.guID WHERE gameID=gID;
END$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure getGameState
-- -----------------------------------------------------

USE `ccdb`;
DROP procedure IF EXISTS `ccdb`.`getGameState`;
SHOW WARNINGS;

DELIMITER $$
USE `ccdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getGameState`(IN gID INT)
BEGIN
	SELECT users.userID, users.username, gamesusers.playerNumber, users.isAi, games.currentTurn, games.winnerID FROM users 
	JOIN gamesusers ON users.userID = gamesusers.userID JOIN games ON games.gameID = gamesusers.gameID
	WHERE gamesusers.gameID=gID;
END$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure getNumberOfPlayers
-- -----------------------------------------------------

USE `ccdb`;
DROP procedure IF EXISTS `ccdb`.`getNumberOfPlayers`;
SHOW WARNINGS;

DELIMITER $$
USE `ccdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getNumberOfPlayers`(IN gID INT, OUT numPl INT)
BEGIN
	SELECT numPlayer as numPl FROM games WHERE gameID=gID;
END$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure getUserGames
-- -----------------------------------------------------

USE `ccdb`;
DROP procedure IF EXISTS `ccdb`.`getUserGames`;
SHOW WARNINGS;

DELIMITER $$
USE `ccdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getUserGames`(IN uID INT)
BEGIN
	SELECT games.gameID, games.numPlayer, games.isReady, games.currentTurn, gamesusers.playerNumber, games.winnerID FROM gamesusers JOIN games 
		ON gamesusers.gameID = games.gameID WHERE gamesusers.userID = uID;
END$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure getUsername
-- -----------------------------------------------------

USE `ccdb`;
DROP procedure IF EXISTS `ccdb`.`getUsername`;
SHOW WARNINGS;

DELIMITER $$
USE `ccdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getUsername`(IN uID INT)
BEGIN
	SELECT username FROM users WHERE userID=uID;
END$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure getWinner
-- -----------------------------------------------------

USE `ccdb`;
DROP procedure IF EXISTS `ccdb`.`getWinner`;
SHOW WARNINGS;

DELIMITER $$
USE `ccdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getWinner`(IN gID INT)
BEGIN
	SELECT username FROM users JOIN games ON users.userID = games.winnerID;
END$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure matchMake
-- -----------------------------------------------------

USE `ccdb`;
DROP procedure IF EXISTS `ccdb`.`matchMake`;
SHOW WARNINGS;

DELIMITER $$
USE `ccdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `matchMake`(IN uID INT, IN numPlayers INT)
BEGIN
	DECLARE gID, playerNum  INT;
	SELECT gameID into gID FROM (
		SELECT MAX(count), gameID FROM (
			SELECT COUNT(userID) as count, games.gameID FROM gamesusers 
			JOIN games ON gamesusers.gameID = games.gameID 
			WHERE games.numPlayer = numPlayers AND games.isReady=0 
				AND games.gameID NOT IN (SELECT gameID FROM gamesusers WHERE userID = uID)
			GROUP BY games.gameID) as c2
		) as c3
		;
	IF gID IS NULL THEN
		SELECT gameID into gID from games WHERE isReady = 0 AND numPlayer = numPlayers AND gameID NOT IN(SELECT gameID FROM gamesusers WHERE userID = uID) ORDER BY created ASC LIMIT 1;
	END IF;

	IF gID IS NULL THEN
		INSERT INTO games (numPlayer, created) values (numPlayers, NOW());
		SELECT LAST_INSERT_ID() into gID;
		CALL `userJoinGame`(uID, gID);
	ELSE
		CALL `userJoinGame`(uID, gID);
	END IF;
	SELECT gID;
END$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure nextTurn
-- -----------------------------------------------------

USE `ccdb`;
DROP procedure IF EXISTS `ccdb`.`nextTurn`;
SHOW WARNINGS;

DELIMITER $$
USE `ccdb`$$
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
END$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure populateBoard
-- -----------------------------------------------------

USE `ccdb`;
DROP procedure IF EXISTS `ccdb`.`populateBoard`;
SHOW WARNINGS;

DELIMITER $$
USE `ccdb`$$
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
END$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure setUsername
-- -----------------------------------------------------

USE `ccdb`;
DROP procedure IF EXISTS `ccdb`.`setUsername`;
SHOW WARNINGS;

DELIMITER $$
USE `ccdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `setUsername`(IN uID INT, IN newname VARCHAR(50))
BEGIN
	UPDATE users SET username = newname where userID = uID;
END$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure setWinner
-- -----------------------------------------------------

USE `ccdb`;
DROP procedure IF EXISTS `ccdb`.`setWinner`;
SHOW WARNINGS;

DELIMITER $$
USE `ccdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `setWinner`(IN wID INT, IN gID INT)
BEGIN
	UPDATE games SET winnerID = uID WHERE gameID=gID;
END$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure updatePiece
-- -----------------------------------------------------

USE `ccdb`;
DROP procedure IF EXISTS `ccdb`.`updatePiece`;
SHOW WARNINGS;

DELIMITER $$
USE `ccdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updatePiece`(IN gID INT, IN oldRow INT, IN oldIndex INT, IN newRow INT, IN newIndex INT)
BEGIN
	UPDATE pieces JOIN gamesusers ON pieces.guID = gamesusers.guID
	SET pieces.onRow = newRow, pieces.onIndex = newIndex 
	WHERE gamesusers.gameID = gID AND pieces.onRow = oldRow AND pieces.onIndex = oldIndex;
END$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure userJoinGame
-- -----------------------------------------------------

USE `ccdb`;
DROP procedure IF EXISTS `ccdb`.`userJoinGame`;
SHOW WARNINGS;

DELIMITER $$
USE `ccdb`$$
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
END$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure userLeaveGame
-- -----------------------------------------------------

USE `ccdb`;
DROP procedure IF EXISTS `ccdb`.`userLeaveGame`;
SHOW WARNINGS;

DELIMITER $$
USE `ccdb`$$
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
END$$

DELIMITER ;
SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
USE `ccdb`;

DELIMITER $$

USE `ccdb`$$
DROP TRIGGER IF EXISTS `ccdb`.`checkNumPlayeronInsert` $$
SHOW WARNINGS$$
USE `ccdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `ccdb`.`checkNumPlayeronInsert`
BEFORE INSERT ON `ccdb`.`games`
FOR EACH ROW
BEGIN
    IF (NEW.numPlayer < 2 OR new.numPlayer > 6 OR new.numPlayer = 5) THEN
    SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'check constraint on number of players failed';
    END IF;
END$$

SHOW WARNINGS$$

USE `ccdb`$$
DROP TRIGGER IF EXISTS `ccdb`.`checkNumPlayeronUpdate` $$
SHOW WARNINGS$$
USE `ccdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `ccdb`.`checkNumPlayeronUpdate`
BEFORE UPDATE ON `ccdb`.`games`
FOR EACH ROW
BEGIN
    IF (NEW.numPlayer < 2 OR new.numPlayer > 6 OR new.numPlayer = 5) THEN
    SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'check constraint on number of players failed';
    END IF;
END$$

SHOW WARNINGS$$

USE `ccdb`$$
DROP TRIGGER IF EXISTS `ccdb`.`checkPlayerNumBeforeUpdate` $$
SHOW WARNINGS$$
USE `ccdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `ccdb`.`checkPlayerNumBeforeUpdate`
BEFORE UPDATE ON `ccdb`.`gamesusers`
FOR EACH ROW
BEGIN
	DECLARE usr VARCHAR(255);
	SELECT username into usr FROM users where users.userID = NEW.userID;
    IF (NEW.playerNumber > 6 OR NEW.playerNumber < 0) THEN
    SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'check constraint on player number failed';
    END IF;
	IF EXISTS(SELECT gameID FROM gamesusers WHERE gameID = new.gameID AND userID = new.userID) THEN
	SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'error. user is already a part of this game.';
	END IF;

	
END$$

SHOW WARNINGS$$

USE `ccdb`$$
DROP TRIGGER IF EXISTS `ccdb`.`checkPlayerNumonInsert` $$
SHOW WARNINGS$$
USE `ccdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `ccdb`.`checkPlayerNumonInsert`
BEFORE INSERT ON `ccdb`.`gamesusers`
FOR EACH ROW
BEGIN
	DECLARE usr VARCHAR(255);
	SELECT username into usr FROM users where users.userID = NEW.userID;
    IF (NEW.playerNumber > 6 OR NEW.playerNumber < 0) THEN
    SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'check constraint on player number failed';
    END IF;
	IF EXISTS(SELECT gameID FROM gamesusers WHERE gameID = new.gameID AND userID = new.userID) THEN
	SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'error. user is already a part of this game.';
	END IF;

END$$

SHOW WARNINGS$$

USE `ccdb`$$
DROP TRIGGER IF EXISTS `ccdb`.`gamesusers_AINS` $$
SHOW WARNINGS$$
USE `ccdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `ccdb`.`gamesusers_AINS`
AFTER INSERT ON `ccdb`.`gamesusers`
FOR EACH ROW
BEGIN
	DECLARE np, countPlayer INT;
	SELECT numPlayer into np from games where games.gameID = new.gameID;
	SELECT count(userID) into countPlayer from gamesusers where gamesusers.gameID = new.gameID;
	IF np = countPlayer THEN
		UPDATE games SET isReady=1 WHERE games.gameID = new.gameID;
	END IF;
END$$

SHOW WARNINGS$$

USE `ccdb`$$
DROP TRIGGER IF EXISTS `ccdb`.`checkRowIndexBeforeInsert` $$
SHOW WARNINGS$$
USE `ccdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `ccdb`.`checkRowIndexBeforeInsert`
BEFORE INSERT ON `ccdb`.`pieces`
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
END$$

SHOW WARNINGS$$

USE `ccdb`$$
DROP TRIGGER IF EXISTS `ccdb`.`checkRowIndexBeforeUpdate` $$
SHOW WARNINGS$$
USE `ccdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `ccdb`.`checkRowIndexBeforeUpdate`
BEFORE UPDATE ON `ccdb`.`pieces`
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
END$$

SHOW WARNINGS$$

DELIMITER ;
