package ca.brocku.chinesecheckers.uiengine.handlers;

import javajar.gameboard.Piece;
import javajar.gameboard.Position;

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
    public void onPieceFinishedMovingAnimation(Piece movedPiece, Position movedFrom);
}
