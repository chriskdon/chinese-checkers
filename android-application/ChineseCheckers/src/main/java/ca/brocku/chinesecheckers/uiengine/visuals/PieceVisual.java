package ca.brocku.chinesecheckers.uiengine.visuals;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

import ca.brocku.chinesecheckers.uiengine.Dimensions;
import ca.brocku.chinesecheckers.uiengine.PieceDrawingDetails;
import ca.brocku.chinesecheckers.uiengine.PixelPosition;

/**
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/3/2014
 */
public class PieceVisual extends Visual {
    private PieceDrawingDetails pieceDrawingDetails;
    private int color;

    protected float cX, cY; // Center (x,y) pixel position

    // TODO: DELETE ME -- ONLY FOR TESTING
    public void setColor(int color) {
        this.color = color;
    }


    /**
     * Create a new piece.
     * @param pieceDrawingDetails   Details for drawing the piece.
     * @param color                 The color of the piece.
     */
    public PieceVisual(PieceDrawingDetails pieceDrawingDetails, int color) {
        super(new PixelPosition(pieceDrawingDetails.getX() - pieceDrawingDetails.getRadius(),
                pieceDrawingDetails.getY() - pieceDrawingDetails.getRadius()),
                new Dimensions(pieceDrawingDetails.getRadius()*2,
                        pieceDrawingDetails.getRadius()*2));

        this.cX = pieceDrawingDetails.getX();
        this.cY = pieceDrawingDetails.getY();

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

        canvas.drawCircle(cX, cY, pieceDrawingDetails.getRadius(), p);
    }
}
