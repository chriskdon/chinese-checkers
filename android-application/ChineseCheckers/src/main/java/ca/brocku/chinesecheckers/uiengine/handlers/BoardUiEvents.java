package ca.brocku.chinesecheckers.uiengine.handlers;

import ca.brocku.chinesecheckers.gameboard.Piece;

/**
 * Events that are caused by a change or message
 * from the board.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/1/2014
 */
public interface BoardUiEvents {
    public void pieceTouched(Piece piece);
}
