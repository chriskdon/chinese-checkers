package ca.brocku.chinesecheckers.uiengine.visuals;

import android.graphics.Canvas;
import android.graphics.Paint;

import ca.brocku.chinesecheckers.uiengine.Dimensions;
import ca.brocku.chinesecheckers.uiengine.PieceDrawingDetails;

/**
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/3/2014
 */
public class PieceVisual extends Visual {
    private PieceDrawingDetails pieceDrawingDetails;
    private int color;

    /**
     * Create a new piece.
     * @param pieceDrawingDetails   Details for drawing the piece.
     * @param color                 The color of the piece.
     */
    public PieceVisual(PieceDrawingDetails pieceDrawingDetails, int color) {
        super(pieceDrawingDetails,
                new Dimensions(pieceDrawingDetails.getRadius()*2,
                        pieceDrawingDetails.getRadius()*2));

        this.pieceDrawingDetails = pieceDrawingDetails;
        this.color = color;
    }

    /**
     * Draw the object onto the <code>canvas</code>
     *
     * @param canvas The canvas to draw to.
     */
    @Override
    public void onDraw(Canvas canvas) {
        Paint p = new Paint();
        p.setFlags(Paint.ANTI_ALIAS_FLAG);
        p.setColor(color);

        canvas.drawCircle(position.getX(), position.getY(), pieceDrawingDetails.getRadius(), p);
    }
}
