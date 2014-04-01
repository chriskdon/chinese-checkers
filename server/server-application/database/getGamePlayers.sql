-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getGamePlayers`(IN gID INT)
BEGIN
	SELECT users.userID, users.username, gamesusers.playerNumber, users.isAi FROM users 
	JOIN gamesusers ON users.userID = gamesusers.userID JOIN games ON games.gameID = gamesusers.gameID
	WHERE gamesusers.gameID=gID;
END