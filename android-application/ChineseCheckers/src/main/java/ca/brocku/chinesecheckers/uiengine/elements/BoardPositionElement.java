package ca.brocku.chinesecheckers.uiengine.elements;

import android.graphics.Canvas;
import android.graphics.Color;

import java.security.InvalidParameterException;

import ca.brocku.chinesecheckers.gameboard.Piece;
import ca.brocku.chinesecheckers.gameboard.Position;
import ca.brocku.chinesecheckers.uiengine.PixelPosition;
import ca.brocku.chinesecheckers.uiengine.exceptions.NoPieceAtPositionException;

/**
 * Information about a position on the board.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/2/2014
 */
public class BoardPositionElement {
    private Canvas canvas;                  // Draw to the canvas

    private PixelPosition pixelPosition;    // The position on the screen in pixels
    private Position position;              // The position on the board.
    private Piece piece;                    // The piece associated with the position.
    private Color color;                    // The color of the position.

    /**
     * Initialize a <code>BoardPositionElement</code> to a specific position.
     *
     * @param canvas        The canvas to draw to.
     * @param pixelPosition The center position on the screen.
     * @param position      The position on the board.
     * @param color         The color of the position.
     * @param piece         The piece associated with the position if any.
     */
    public BoardPositionElement(Canvas canvas, PixelPosition pixelPosition, Position position, Color color, Piece piece) {
        // Check for valid params
        if(canvas == null) { throw new InvalidParameterException("canvas can't be null"); }
        if(pixelPosition == null) { throw new InvalidParameterException("pixelPosition can't be null"); }
        if(position == null) { throw new InvalidParameterException("position can't be null"); }
        if(color == null) { throw new InvalidParameterException("color can't be null"); }

        // Assign values
        this.canvas = canvas;
        this.pixelPosition = pixelPosition;
        this.position = position;
        this.piece = piece;
        this.color = color;
    }

    /**
     * Get the piece associated with this position.
     *
     * @return The piece at that position.
     */
    public Piece getPiece() {
        if(hasPiece()) {
            return piece;
        }

        throw new NoPieceAtPositionException(position);
    }

    /**
     * Does this position have a piece associated with it.
     *
     * @return
     */
    public boolean hasPiece() {
        return (piece != null);
    }
}
