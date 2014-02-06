package ca.brocku.chinesecheckers.uiengine;

import ca.brocku.chinesecheckers.gameboard.Piece;
import ca.brocku.chinesecheckers.gameboard.Position;
import ca.brocku.chinesecheckers.gamestate.Player;
import ca.brocku.chinesecheckers.uiengine.handlers.FinishedMovingPieceHandler;
import ca.brocku.chinesecheckers.uiengine.handlers.FinishedRotatingBoardHandler;

/**
 * Can draw components needed for the board.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/1/2014
 */
public interface BoardUiEngine {
    /**
     * TODO: Remove and replace with board initialization
     *
     * Set the number of players to draw initial board state.
     *
     * @param playerCount   The number of player playing.
     */
    public void setPlayerCount(int playerCount);

    /**
     * Animate moving a piece on the board.
     *
     * @param piece         The piece to be moved.
     * @param to            The position to move to.
     * @param jumps         @Nullable, The piece that is jumped in this move if any.
     * @param onFinished    Callback to fire when the animation has completed.
     */
    public void movePiece(Piece piece, Position to, Piece jumps, FinishedMovingPieceHandler onFinished);

    /**
     * Highlight a position on the board so that the player
     * can see something important is occurring there.
     *
     * @param position The position to highlight.
     */
    public void highlightPosition(Position position);

    /**
     * Rotate the board a certain number of <code>degrees</code>.
     *
     * @param degrees       The number of degrees to rotate the board.
     * @param onFinished    Called when the rotation animation has completed.
     */
    public void rotateBoard(int degrees, FinishedRotatingBoardHandler onFinished);

    /**
     * Do the animation for when a player wins.
     *
     * @param player    The player that won.
     */
    public void playerWon(Player player);

    /**
     * TODO: Possibly unneeded could be done with movePiece.
     *
     * Return a piece back to it's original position.
     *
     * @param piece             The piece to move.
     * @param originalPosition  The original position to move the piece back to.
     * @param onFinished        Callback to fire when the animation has completed.
     */
    public void cancelMove(Piece piece, Position originalPosition,
                           FinishedMovingPieceHandler onFinished);

    /**
     * Show hints on the board for positions the player could potentially move to.
     *
     * @param positions The positions to draw the hints on.
     */
    public void showHintPositions(Position[] positions);

    /**
     * TODO: Possibly put this in constructor
     *
     * Initialize the board with the current piece positions.
     *
     * @param pieces    The pieces that represent the initial state of the board.
     */
    public void initializeBoard(Piece[] pieces);
}