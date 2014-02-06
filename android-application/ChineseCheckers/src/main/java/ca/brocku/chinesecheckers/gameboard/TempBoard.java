package ca.brocku.chinesecheckers.gameboard;

/**
 * The representation of a game board and what it can do.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/1/2014
 */
public interface TempBoard {
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
     * Move a piece from one position to another.
     *
     * @param piece The piece to move.
     * @param to    The new position of the piece.
     */
    public void movePiece(GridPiece piece, GridPosition to);

    /**
     * Returns all the pieces on the board in no specified order.
     * Blank positions are not returned.
     *
     * @return All the pieces.
     */
    public GridPiece[] getAllPieces();

    /**
     * Get the piece that is at a position on the board.
     *
     * @param at    The position the piece is at.
     *
     * @return      The piece that was at the position specified.
     */
    public GridPiece getPiece(GridPosition at);

    /**
     * A list of valid positions that the specified piece could move to.
     *
     * @param forPiece  The piece to check positions for.
     *
     * @return          The list of positions the piece can move to. Or <code>null</code> if there
     *                  is nowhere to move.
     */
    public GridPosition[] getPossibleMoves(GridPiece forPiece);

    /**
     * Check if a move is valid for a specified piece.
     *
     * @param piece The piece making the move.
     * @param to    The position the piece is trying to move to.
     * @return      True if the move is valid, false otherwise.
     */
    public boolean isValidMove(GridPiece piece, GridPosition to);
}
