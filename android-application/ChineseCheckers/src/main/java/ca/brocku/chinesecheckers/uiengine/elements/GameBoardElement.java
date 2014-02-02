package ca.brocku.chinesecheckers.uiengine.elements;

import android.graphics.Canvas;

import ca.brocku.chinesecheckers.gameboard.Piece;

/**
 * A game board with positions that can be drawn.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/2/2014
 */
public class GameBoardElement {
    protected final int NUMBER_OF_POSITIONS = 121; // The total number of positions on the board.

    private Canvas canvas;

    protected BoardPositionElement[] boardPositions = new BoardPositionElement[NUMBER_OF_POSITIONS];

    public GameBoardElement(Canvas canvas, Piece[] initialPiecePositions) {
        this.canvas = canvas;


    }
}
