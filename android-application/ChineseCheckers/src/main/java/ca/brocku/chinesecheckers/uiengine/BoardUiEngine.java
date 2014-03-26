package ca.brocku.chinesecheckers.uiengine;

import ca.brocku.chinesecheckers.gameboard.AndroidPosition;
import ca.brocku.chinesecheckers.gameboard.AndroidPiece;
import ca.brocku.chinesecheckers.gameboard.AndroidReadOnlyGameBoard;
import javajar.gamestate.Player;
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
    public void drawBoard(AndroidReadOnlyGameBoard board);

    /**
     * Highlight a piece on the board.
     *
     * @param piece The position to highlight.
     */
    public void highlightPiece(AndroidPiece piece);

    /**
     * Rotate the board a certain number of <code>degrees</code>.
     *
     * @param degrees       The number of degrees to rotate the board.
     * @param onFinished    Called when the rotation animation has completed.
     */
    public void rotateBoard(float degrees, FinishedRotatingBoardHandler onFinished);

    /**
     * Do the animation for when a player wins.
     *
     * @param player    The player that won.
     */
    public void playerWon(Player player);

    /**
     * Show hints on the board for positions the player could potentially move to.
     *
     * @param positions The positions to draw the hints on.
     */
    public void showHintPositions(AndroidPosition[] positions);

    /**
     * TODO: Possibly put this in constructor
     *
     * Initialize the board with the current piece positions.
     *
     * @param board    The pieces that represent the initial state of the board.
     */
    public void initializeBoard(AndroidReadOnlyGameBoard board);

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
        public void positionTouched(AndroidPosition position);
    }
}
