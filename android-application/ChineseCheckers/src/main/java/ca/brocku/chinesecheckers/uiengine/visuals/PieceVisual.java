package ca.brocku.chinesecheckers.uiengine.visuals;

import android.graphics.Canvas;
import android.graphics.Paint;

import ca.brocku.chinesecheckers.gameboard.Position;
import ca.brocku.chinesecheckers.uiengine.PieceDrawingDetails;
import ca.brocku.chinesecheckers.uiengine.PixelPosition;

/**
 * A player piece that is drawn on the board.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/3/2014
 */
public class PieceVisual extends Visual {
    private PieceDrawingDetails pieceDrawingDetails;    // Details about how the piece is drawn
    protected int color;                                // The color of the piece
    protected Paint paint;                              // Paint details for the piece

    /**
     * Create a new piece.
     * @param pieceDrawingDetails   Details for drawing the piece.
     * @param color                 The color of the piece.
     */
    public PieceVisual(PieceDrawingDetails pieceDrawingDetails, int color) {
        super(0, 0, // These are set in #setPosition().
              pieceDrawingDetails.getRadius() * 2,
              pieceDrawingDetails.getRadius() * 2);

        this.color = color;
        this.paint = new Paint();

        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);

        setPieceDrawingDetails(pieceDrawingDetails);
    }

    /**
     * Set the details of the piece.
     * @param details
     */
    public void setPieceDrawingDetails(PieceDrawingDetails details) {
        this.pieceDrawingDetails = details;
        this.position = new PixelPosition(details.getX() - details.getRadius(),
                                          details.getY() - details.getRadius());
    }

    public float getCenterX() {
        return pieceDrawingDetails.getX();
    }

    public  float getCenterY() {
        return pieceDrawingDetails.getY();
    }

    public float getRadius() {
        return pieceDrawingDetails.getRadius();
    }

    /**
     * Draw the object onto the <code>canvas</code>
     *
     * @param canvas The canvas to draw to.
     */
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(getCenterX(), getCenterY(), pieceDrawingDetails.getRadius(), paint);
    }

    public Position getPositionOnBoard() {
        return this.pieceDrawingDetails.getPosition();
    }
}
