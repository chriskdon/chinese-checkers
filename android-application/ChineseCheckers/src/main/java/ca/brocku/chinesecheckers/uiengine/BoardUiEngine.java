package ca.brocku.chinesecheckers.uiengine;

import ca.brocku.chinesecheckers.gameboard.Piece;
import ca.brocku.chinesecheckers.gameboard.Position;
import ca.brocku.chinesecheckers.gameboard.ReadOnlyGameBoard;
import ca.brocku.chinesecheckers.gamestate.Move;
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
     * Animate moving a piece on the board.
     *
     * @param board
     * @return              Returns true if a piece could be successfully moved.
     */
    public void drawBoard(ReadOnlyGameBoard board);

    /**
     * Highlight a piece on the board.
     *
     * @param piece The position to highlight.
     */
    public void highlightPiece(Piece piece);

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
     * @param move              The piece and path taken.
     * @param onFinished        Callback to fire when the animation has completed.
     */
    public void cancelMove(Move move, FinishedMovingPieceHandler onFinished);

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
     * @param board    The pieces that represent the initial state of the board.
     */
    public void initializeBoard(ReadOnlyGameBoard board);

    /**
     * Add a handler to receive any events that occur on the board.
     *
     * @param handler The handler to register for the events.
     */
    public void setBoardEventsHandler(BoardUiEventsHandler handler);

    /**
     * Events caused by the BoardUiEngine
     */
    public static interface BoardUiEventsHandler {
        public void positionTouched(Position position);
    }
}
