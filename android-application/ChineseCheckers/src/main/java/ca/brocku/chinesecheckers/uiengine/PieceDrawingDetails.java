package ca.brocku.chinesecheckers.uiengine;

import ca.brocku.chinesecheckers.gameboard.Position;

/**
 * Store information about where a piece is on the board, and how big it is.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/3/2014
 */
public class PieceDrawingDetails extends PixelPosition {
    private float radius;       // Radius of the piece
    private Position position;  // The position on the board

    /**
     * Create a piece.
     *
     * @param position  The position on the board.
     * @param x         The x coordinate on the canvas.
     * @param y         The y coordinate on the canvas.
     * @param radius    The radius of the piece.
     */
    public PieceDrawingDetails( Position position,float x, float y, float radius) {
        super(x, y);

        this.position = position;
        this.radius = radius;
    }

    /**
     * @return  The radius of the piece.
     */
    public float getRadius() {
        return radius;
    }

    /**
     * @return  The position of the piece in row-index form.
     */
    public Position getPosition() {
        return position;
    }
}
