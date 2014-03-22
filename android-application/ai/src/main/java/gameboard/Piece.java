package gameboard;


/**
 * A single piece on the board that is owned
 * by a Player.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/1/2014
 */
public interface Piece {
    /**
     * Get the position of the piece on the board.
     * @return  The position
     */
    public Position getPosition();

    /**
     * Get the number associated with the player for this piece.
     * @return  The number.
     */
    public int getPlayerNumber();
}
