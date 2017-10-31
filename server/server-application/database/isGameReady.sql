DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `isGameReady`(IN gID INT)
BEGIN
	SELECT isReady FROM Games WHERE gameID = gId;
END