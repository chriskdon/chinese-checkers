USE `ccdb`;
DROP procedure IF EXISTS `createUser`;

DELIMITER $$
USE `ccdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createUser`(IN usrname VARCHAR(50), IN gcmId VARCHAR(600))
BEGIN
	INSERT INTO users (username, isAi, gcmRegistrationId) values (usrname, 0, gcmId);
	SELECT userID from Users where username=usrname;
END$$

DELIMITER ;

