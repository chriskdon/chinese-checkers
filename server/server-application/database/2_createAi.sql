USE `ccdb`;
DROP procedure IF EXISTS `createAI`;

DELIMITER $$
USE `ccdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createAI`(OUT aiID INT)
BEGIN
	DECLARE usrname VARCHAR(50);
	DECLARE mID INT;
	SELECT MAX(userID) into mID from Users;
	SET usrname = concat("Anonymous", mID+1);
	INSERT INTO users (username, isAi, gcmRegistrationId) values (usrname, 1, null);
	SELECT userID into aiID from Users where username=usrname;
END$$

DELIMITER ;

