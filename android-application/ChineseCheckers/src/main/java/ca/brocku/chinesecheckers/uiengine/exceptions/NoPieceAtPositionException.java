package ca.brocku.chinesecheckers.uiengine.exceptions;

import javajar.gameboard.Position;

/**
 * Thrown when something tries to get a <code>Piece</code> from a <code>BoardPositions</code>
 * and there is no <code>Piece</code> at that position.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/2/2014
 */
public class NoPieceAtPositionException extends RuntimeException {
    public NoPieceAtPositionException(Position p) {
        super("There is no piece at that position <R:" + p.getRow() + ",I: " + p.getIndex());
    }
}
