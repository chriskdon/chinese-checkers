USE `ccdb`;
DROP procedure IF EXISTS `userLeaveGame`;

DELIMITER $$
USE `ccdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `userLeaveGame`(IN uID INT, IN gID INT)
BEGIN
	DECLARE game, pl, np, countAI, countHuman INT;
	SELECT gameID into game FROM gamesusers where userID=uID and gameID=gID;
	SELECT playerNumber into pl FROM gamesusers where userID=uID and gameID=gID;
	CALL `createAI`(@aiID);
	UPDATE gamesusers SET userID = @aiID where userID=uID and gameID=gID;
	SELECT count(isAi) INTO countHuman from users join gamesusers on users.userID = gamesusers.userID where gamesusers.gameID = gID AND users.isAi=0;
	IF countHuman = 0 THEN
		DELETE FROM gamesusers WHERE gamesusers.gameID = gID;
		DELETE FROM games WHERE gameID=gID;
		DELETE FROM users WHERE isAi = 1 AND users.userID NOT IN (
					SELECT userID FROM gamesusers
			);
	END IF;

	SELECT countHuman AS HumansLeftInGame, game AS Deleted;
END$$

DELIMITER ;

