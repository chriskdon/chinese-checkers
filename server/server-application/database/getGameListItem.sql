USE `ccdb`;
DROP procedure IF EXISTS `getGameListItem`;

DELIMITER $$
USE `ccdb`$$
CREATE PROCEDURE `getGameListItem` (IN gameId INT, IN playerUserId INT)
BEGIN
	SELECT 
		GMS.*, 
		GU.playerNumber, 
		GUW.username AS winnerUsername, 
		GUW.playerNumber AS winnerPlayerNumber
	FROM GamesUsers GU
		LEFT JOIN games GMS ON GMS.gameId = GU.gameID
		LEFT JOIN GamesUsers GUW ON GUW.gameID = GU.gameID AND GMS.winnerID = GUW.userID
	WHERE GU.userId = playerUserId AND GU.gameID = gameId;

END$$

DELIMITER ;

