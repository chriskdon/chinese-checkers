USE `ccdb`;
DROP procedure IF EXISTS `getGamePlayers`;

DELIMITER $$
USE `ccdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getGamePlayers`(IN gID INT)
BEGIN
	SELECT users.*, gamesusers.playerNumber, users.isAi FROM users 
	JOIN gamesusers ON users.userID = gamesusers.userID JOIN games ON games.gameID = gamesusers.gameID
	WHERE gamesusers.gameID=gID;
END$$

DELIMITER ;

