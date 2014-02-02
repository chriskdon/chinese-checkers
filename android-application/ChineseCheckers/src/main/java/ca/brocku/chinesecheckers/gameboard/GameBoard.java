package ca.brocku.chinesecheckers.gameboard;

/**
 * The representation of a game board and what it can do.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/1/2014
 */
public abstract class GameBoard {
    /**
     * The number of available positions in each row.
     */
    protected final int[] ROW_POSITION_COUNT = {1, 2, 3, 4, 13, 12, 11, 10,
            9,
            10, 11, 12, 13, 4, 3, 2, 1};

    /**
     * Move a piece from one position to another.
     *
     * @param piece The piece to move.
     * @param to    The new position of the piece.
     */
    public abstract void movePiece(Piece piece, Position to);

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
     * Check if a move is valid for a specified piece.
     *
     * @param piece The piece making the move.
     * @param to    The position the piece is trying to move to.
     * @return      True if the move is valid, false otherwise.
     */
    public abstract boolean isValidMove(Piece piece, Position to);
}
