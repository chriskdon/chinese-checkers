USE `ccdb`;
DROP procedure IF EXISTS `calculateWinner`;

DELIMITER $$
USE `ccdb`$$
CREATE PROCEDURE `calculateWinner` (IN gameId INT)
BEGIN
  DECLARE chk INT;
SELECT * INTO chk FROM (SELECT
(SELECT COUNT(*) FROM (
  SELECT COUNT(*) 
  FROM (SELECT P.*, GU.playerNumber, L.playerNumber AS inGoal
      FROM pieces P
        LEFT JOIN gamesusers GU ON GU.guID = P.guID
        LEFT JOIN lookuptable L ON L.playerNumber = GU.playerNumber AND L.onRow = P.onRow AND L.onIndex = P.onIndex
      WHERE GU.gameId = gameId) X
  WHERE inGoal IS NULL
  GROUP BY playerNumber
  HAVING COUNT(*) >= 1) Y) - (SELECT numPlayer FROM games WHERE gameId = gameId)) AS X;

	-- Somebody Won
	IF chk <> 0 THEN
		SELECT 1;
	END IF;

END$$

DELIMITER ;

