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
    private Paint strokePaint;

    /**
     * Create a new hint.
     * @param pieceDrawingDetails   Details for drawing the hint at a piece location.
     * @param backgroundColor       The color of the background.
     * @param strokeColor           The color of the outer stroke.
     */
    public HintVisual(PieceDrawingDetails pieceDrawingDetails, int backgroundColor, int strokeColor) {
        super(pieceDrawingDetails, backgroundColor);

        this.strokeWidth = this.getRadius()/4;

        this.strokePaint = new Paint();
        this.strokePaint.setStyle(Paint.Style.STROKE);
        this.strokePaint.setAntiAlias(true);
        this.strokePaint.setColor(strokeColor);
        this.strokePaint.setStrokeWidth(strokeWidth);
    }

    /**
     * Draw the object onto the <code>canvas</code>
     *
     * @param canvas The canvas to draw to.
     */
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(getCenterX(), getCenterY(), getRadius(), paint);
        canvas.drawCircle(getCenterX(), getCenterY(), getRadius() - (strokeWidth/2), strokePaint);
    }
}
