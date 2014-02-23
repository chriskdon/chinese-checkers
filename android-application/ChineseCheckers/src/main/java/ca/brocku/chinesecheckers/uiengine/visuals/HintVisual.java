package ca.brocku.chinesecheckers.uiengine.visuals;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import ca.brocku.chinesecheckers.uiengine.PieceDrawingDetails;

/**
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/22/2014
 */
public class HintVisual extends PieceVisual {
    /**
     * Create a new hint.
     * @param pieceDrawingDetails   Details for drawing the hint at a piece location.
     */
    public HintVisual(PieceDrawingDetails pieceDrawingDetails) {
        // TODO: Replace color with something from resources.
        super(pieceDrawingDetails, Color.rgb(229, 231, 233));

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(6); // TODO: Width from resources?
    }

    /**
     * Draw the object onto the <code>canvas</code>
     *
     * @param canvas The canvas to draw to.
     */
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(getCenterX(), getCenterY(), getRadius(), paint);
    }
}
