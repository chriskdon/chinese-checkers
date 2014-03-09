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
    private float strokeWidth;

    /**
     * Create a new hint.
     * @param pieceDrawingDetails   Details for drawing the hint at a piece location.
     * @param color                 The color of the stroke.
     * @param strokeWidth           The width of the stroke.
     */
    public HintVisual(PieceDrawingDetails pieceDrawingDetails, int color, float strokeWidth) {
        super(pieceDrawingDetails, color);

        this.strokeWidth = strokeWidth;

        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(strokeWidth);
    }

    /**
     * Draw the object onto the <code>canvas</code>
     *
     * @param canvas The canvas to draw to.
     */
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(getCenterX(), getCenterY(), getRadius() - (strokeWidth/2), paint);
    }
}
