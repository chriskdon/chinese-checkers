package ca.brocku.chinesecheckers.uiengine.handlers;

import ca.brocku.chinesecheckers.gameboard.AndroidPiece;
import ca.brocku.chinesecheckers.gameboard.AndroidPosition;

/**
 * Occurs when the drawing engine has finished the animation
 * for a piece moving.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/1/2014
 */
public interface FinishedMovingPieceHandler {
    /**
     * Fires when the animation has completed for a piece moving.
     *
     * @param movedPiece    The piece that has been moved. It will contain is final position.
     * @param movedFrom     The position that the piece moved from.
     */
    public void onPieceFinishedMovingAnimation(AndroidPiece movedPiece, AndroidPosition movedFrom);
}
