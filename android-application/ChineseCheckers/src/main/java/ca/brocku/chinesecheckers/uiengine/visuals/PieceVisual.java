package ca.brocku.chinesecheckers.uiengine.visuals;

import android.graphics.Canvas;
import android.graphics.Paint;

import ca.brocku.chinesecheckers.gameboard.Position;
import ca.brocku.chinesecheckers.uiengine.PieceDrawingDetails;
import ca.brocku.chinesecheckers.uiengine.PiecePositionSystem;

/**
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/3/2014
 */
public class PieceVisual extends Visual {
    private PieceDrawingDetails position;
    private int color;

    public PieceVisual(PieceDrawingDetails position, int color) {
        this.position = position;
        this.color = color;
    }

    public PieceVisual(PiecePositionSystem positionSystem, Position pos, int color) {
        this(positionSystem.get(pos), color);
    }

    /**
     * Draw the object onto the <code>canvas</code>
     *
     * @param canvas The canvas to draw to.
     */
    @Override
    public void draw(Canvas canvas) {
        Paint p = new Paint();
        p.setFlags(Paint.ANTI_ALIAS_FLAG);
        p.setColor(color);

        canvas.drawCircle(position.x, position.y, position.getRadius(), p);
    }
}
