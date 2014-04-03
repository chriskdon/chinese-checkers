USE `ccdb`;
DROP procedure IF EXISTS `getGamePlayers`;

DELIMITER $$
USE `ccdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getGamePlayersNeedNotification`(IN gID INT)
BEGIN
	SELECT users.*, gamesusers.playerNumber, users.isAi FROM users 
	JOIN gamesusers ON users.userID = gamesusers.userID 
	JOIN games ON games.gameID = gamesusers.gameID
	WHERE gamesusers.gameID = gID AND users.isAi = 0 AND users.gcmRegistrationId IS NOT NULL;
END$$

DELIMITER ;

