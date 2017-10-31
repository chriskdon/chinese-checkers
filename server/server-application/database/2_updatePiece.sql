USE `ccdb`;
DROP procedure IF EXISTS `updatePiece`;

DELIMITER $$
USE `ccdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updatePiece`(IN gID INT, IN uID INT, IN oldRow INT, IN oldIndex INT, IN newRow INT, IN newIndex INT)
BEGIN
	UPDATE pieces JOIN gamesusers ON pieces.guID = gamesusers.guID
	SET pieces.onRow = newRow, pieces.onIndex = newIndex 
	WHERE gamesusers.gameID = gID AND gamesusers.userID = uID
	AND pieces.onRow = oldRow AND pieces.onIndex = oldIndex;
END$$

DELIMITER ;

