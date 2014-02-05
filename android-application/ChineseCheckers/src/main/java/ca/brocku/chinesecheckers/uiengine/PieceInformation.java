package ca.brocku.chinesecheckers.uiengine;

import ca.brocku.chinesecheckers.gameboard.Position;

/**
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/3/2014
 */
public class PieceInformation {
    /**
     * Pixel Coordinates
     */
    public float x, y;

    /**
     * Board Position
     */
    public int row, index;

    public float radius;

    public PieceInformation(float x, float y, int row, int index, float radius) {
        this.x = x;
        this.y = y;

        this.row = row;
        this.index = index;

        this.radius = radius;
    }
}
