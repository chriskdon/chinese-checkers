package ca.brocku.chinesecheckers.uiengine.exceptions;

import ca.brocku.chinesecheckers.gameboard.AndroidPosition;

/**
 * Thrown when something tries to get a <code>AndroidPiece</code> from a <code>BoardPositions</code>
 * and there is no <code>AndroidPiece</code> at that position.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/2/2014
 */
public class NoPieceAtPositionException extends RuntimeException {
    public NoPieceAtPositionException(AndroidPosition p) {
        super("There is no piece at that position <R:" + p.getRow() + ",I: " + p.getIndex());
    }
}
