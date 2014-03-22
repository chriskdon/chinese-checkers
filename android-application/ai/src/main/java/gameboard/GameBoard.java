package gameboard;


import java.io.Serializable;

/**
 * The representation of a game board and what it can do.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/1/2014
 */
public interface GameBoard extends Serializable {
    /**
     * The number of available positions in each row.
     */
    public static final int[] ROW_POSITION_COUNT = {1, 2, 3, 4, 13, 12, 11, 10, 9, 10, 11, 12, 13, 4, 3, 2, 1};

    /**
     * Total number of spaces on the board
     */
    public static final int TOTAL_PIECE_COUNT = 121;

    /**
     * Maximum number of pieces on a row
     */
    public static final int MAXIMUM_PIECES_PER_ROW = 13;

    /**
     * MovePath a piece from one position to another.
     *
     * @param piece The piece to move.
     * @param to    The new position of the piece.
     */
    public abstract void movePiece(Piece piece, Position to);

    /**
     * Returns all the pieces on the board in no specified order.
     * Blank positions are not returned.
     *
     * @return All the pieces.
     */
    public abstract Piece[] getAllPieces();

    /**
     * Get the piece that is at a position on the board.
     *
     * @param at    The position the piece is at.
     *
     * @return      The piece that was at the position specified.
     */
    public abstract Piece getPiece(Position at);

    /**
     * A list of valid positions that the specified piece could move to.
     *
     * @param forPiece  The piece to check positions for.
     *
     * @return          The list of positions the piece can move to. Or <code>null</code> if there
     *                  is nowhere to move.
     */
    public abstract Position[] getPossibleMoves(Piece forPiece);

    /**
     * A list of valid positions that a piece can go to ONLY by hopping over another player.
     *
     * @param forPiece  The piece to check positions for.
     * @return  The list of positions the piece can move to.
     */
    public abstract Position[] getPossibleHops(Piece forPiece);

    /**
     * Check if a move is valid for a specified piece.
     *
     * @param piece The piece making the move.
     * @param to    The position the piece is trying to move to.
     * @return      True if the move is valid, false otherwise.
     */
    public abstract boolean isValidMove(Piece piece, Position to);

    /**
     * Add a piece to the board.
     *
     * @param p
     */
    public abstract void addPiece(Piece p);

    /**
     * Returns a deep copied version of the gameboard.
     * @return
     */
    public abstract GameBoard getDeepCopy();

    /**
     * Get the number of players
     * @return
     */
    public abstract int getPlayerCount();

    /**
     * Reset the board to start state
     */
    public abstract void reset();

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */

    public abstract boolean hasPlayerWon(int playerNumber);
}
