DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getGameList`(IN playerUserID INT)
BEGIN
	SELECT 
		GMS.*, 
		GU.playerNumber, 
		GUW.username AS winnerUsername, 
		GUW.playerNumber AS winnerPlayerNumber
	FROM GamesUsers GU
		LEFT JOIN games GMS ON GMS.gameId = GU.gameID
		LEFT JOIN GamesUsers GUW ON GUW.gameID = GU.gameID AND GMS.winnerID = GUW.userID
	WHERE GU.userId = playerUserID;
END