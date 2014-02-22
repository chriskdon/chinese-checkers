package ca.brocku.chinesecheckers.gameboard;

/**
 * Creates a ReadOnlyGameBoard out of a GameBoard
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/22/2014
 */
public class ReadOnlyGameBoard extends GameBoard {
    private GameBoard gameBoard;

    public ReadOnlyGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     * Move a piece from one position to another.
     *
     * @param piece The piece to move.
     * @param to    The new position of the piece.
     */
    @Override
    public void movePiece(Piece piece, Position to) {
        throw new IllegalAccessError("You can't modify the board.");
    }

    /**
     * Returns all the pieces on the board in no specified order.
     * Blank positions are not returned.
     *
     * @return All the pieces.
     */
    @Override
    public Piece[] getAllPieces() {
        return  gameBoard.getAllPieces();
    }

    /**
     * Get the piece that is at a position on the board.
     *
     * @param at The position the piece is at.
     * @return The piece that was at the position specified.
     */
    @Override
    public Piece getPiece(Position at) {
        return gameBoard.getPiece(at);
    }

    /**
     * A list of valid positions that the specified piece could move to.
     *
     * @param forPiece The piece to check positions for.
     * @return The list of positions the piece can move to. Or <code>null</code> if there
     * is nowhere to move.
     */
    @Override
    public Position[] getPossibleMoves(Piece forPiece) {
        return gameBoard.getPossibleMoves(forPiece);
    }

    /**
     * Check if a move is valid for a specified piece.
     *
     * @param piece The piece making the move.
     * @param to    The position the piece is trying to move to.
     * @return True if the move is valid, false otherwise.
     */
    @Override
    public boolean isValidMove(Piece piece, Position to) {
        return isValidMove(piece, to);
    }
}
