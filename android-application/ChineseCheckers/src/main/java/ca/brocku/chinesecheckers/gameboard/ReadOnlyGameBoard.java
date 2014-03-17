package ca.brocku.chinesecheckers.gameboard;

import android.os.Parcel;

/**
 * Creates a ReadOnlyGameBoard out of a GameBoard
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/22/2014
 */
public class ReadOnlyGameBoard implements GameBoard {
    private GameBoard gameBoard;

    public ReadOnlyGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     * MovePath a piece from one position to another.
     *
     * @param piece The piece to move.
     * @param to    The new position of the piece.
     */
    @Override
    public void movePiece(Piece piece, Position to) {
        throw new UnsupportedOperationException("You can't modify the board.");
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

    /**
     * Parcel constructor
     * @param p
     */
    private ReadOnlyGameBoard(Parcel p) {
        gameBoard = p.readParcelable(GameBoard.class.getClassLoader());
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * A list of valid positions that a piece can go to ONLY by hopping over another player.
     *
     * @param forPiece The piece to check positions for.
     * @return The list of positions the piece can move to.
     */
    @Override
    public Position[] getPossibleHops(Piece forPiece) {
        return gameBoard.getPossibleHops(forPiece);
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(gameBoard, 0);
    }

    /**
     * Add a piece to the board.
     *
     * @param p
     */
    @Override
    public void addPiece(Piece p) {
        throw new UnsupportedOperationException("You can not add a piece to a Read-Only board.");
    }

    /**
     * Returns a deep copied version of the gameboard.
     *
     * @return
     */
    @Override
    public GameBoard getDeepCopy() {
        return gameBoard.getDeepCopy();
    }

    /**
     * Get the number of players
     *
     * @return
     */
    @Override
    public int getPlayerCount() {
        return gameBoard.getPlayerCount();
    }

    /**
     * Reset the board to start state
     */
    @Override
    public void reset() {
        throw new UnsupportedOperationException("This board cannot be modified");
    }

    /**
     * Has the specified player won the game.
     *
     * @param playerNumber The player number.
     * @return True if they have won, false otherwise.
     */
    @Override
    public boolean hasPlayerWon(int playerNumber) {
        return gameBoard.hasPlayerWon(playerNumber);
    }
}
