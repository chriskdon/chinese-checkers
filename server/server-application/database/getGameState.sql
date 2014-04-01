USE `ccdb`;
DROP procedure IF EXISTS `getGameState`;

DELIMITER $$
USE `ccdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getGameState`(IN gID INT)
BEGIN
	SELECT * FROM Games WHERE gameID = gID;
END
$$

DELIMITER ;

