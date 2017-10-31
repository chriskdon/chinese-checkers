DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `populateBoard`(IN gID INT, IN playNum INT)
BEGIN
	DECLARE id INT;
	SELECT guID INTO id FROM gamesusers WHERE gameID = gID AND playerNumber = playNum;
	CASE playNum
	WHEN 1 THEN
		INSERT INTO pieces values (id, 16, 0);
		INSERT INTO pieces values (id, 15, 0);
		INSERT INTO pieces values (id, 15, 1);
		INSERT INTO pieces values (id, 14, 0);
		INSERT INTO pieces values (id, 14, 1);
		INSERT INTO pieces values (id, 14, 2);
		INSERT INTO pieces values (id, 13, 0);
		INSERT INTO pieces values (id, 13, 1);
		INSERT INTO pieces values (id, 13, 2);
		INSERT INTO pieces values (id, 13, 3);
	WHEN 2 THEN
		INSERT INTO pieces values (id, 9, 0);
		INSERT INTO pieces values (id, 10, 0);
		INSERT INTO pieces values (id, 10, 1);
		INSERT INTO pieces values (id, 11, 0);
		INSERT INTO pieces values (id, 11, 1);
		INSERT INTO pieces values (id, 11, 2);
		INSERT INTO pieces values (id, 12, 0);
		INSERT INTO pieces values (id, 12, 1);
		INSERT INTO pieces values (id, 12, 2);
		INSERT INTO pieces values (id, 12, 3);
	WHEN 3 THEN
		INSERT INTO pieces values (id, 7, 0);
		INSERT INTO pieces values (id, 6, 0);
		INSERT INTO pieces values (id, 6, 1);
		INSERT INTO pieces values (id, 5, 0);
		INSERT INTO pieces values (id, 5, 1);
		INSERT INTO pieces values (id, 5, 2);
		INSERT INTO pieces values (id, 4, 0);
		INSERT INTO pieces values (id, 4, 1);
		INSERT INTO pieces values (id, 4, 2);
		INSERT INTO pieces values (id, 4, 3);
	WHEN 4 THEN
		INSERT INTO pieces values (id, 0, 0);
		INSERT INTO pieces values (id, 1, 0);
		INSERT INTO pieces values (id, 1, 1);
		INSERT INTO pieces values (id, 2, 0);
		INSERT INTO pieces values (id, 2, 1);
		INSERT INTO pieces values (id, 2, 2);
		INSERT INTO pieces values (id, 3, 0);
		INSERT INTO pieces values (id, 3, 1);
		INSERT INTO pieces values (id, 3, 2);
		INSERT INTO pieces values (id, 3, 3);
	WHEN 5 THEN
		INSERT INTO pieces values (id, 7, 9);
		INSERT INTO pieces values (id, 6, 9);
		INSERT INTO pieces values (id, 6, 10);
		INSERT INTO pieces values (id, 5, 9);
		INSERT INTO pieces values (id, 5, 10);
		INSERT INTO pieces values (id, 5, 11);
		INSERT INTO pieces values (id, 4, 9);
		INSERT INTO pieces values (id, 4, 10);
		INSERT INTO pieces values (id, 4, 11);
		INSERT INTO pieces values (id, 4, 12);
	WHEN 6 THEN
		INSERT INTO pieces values (id, 9, 9);
		INSERT INTO pieces values (id, 10, 9);
		INSERT INTO pieces values (id, 10, 10);
		INSERT INTO pieces values (id, 11, 9);
		INSERT INTO pieces values (id, 11, 10);
		INSERT INTO pieces values (id, 11, 11);
		INSERT INTO pieces values (id, 12, 9);
		INSERT INTO pieces values (id, 12, 10);
		INSERT INTO pieces values (id, 12, 11);
		INSERT INTO pieces values (id, 12, 12);
	ELSE
		SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'invalid player number passed';
	END CASE;
END$$
DELIMITER ;
