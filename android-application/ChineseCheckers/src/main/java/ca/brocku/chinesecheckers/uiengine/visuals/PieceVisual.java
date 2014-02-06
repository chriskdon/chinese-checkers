package ca.brocku.chinesecheckers.uiengine.visuals;

import android.graphics.Canvas;
import android.graphics.Paint;

import ca.brocku.chinesecheckers.gameboard.Piece;
import ca.brocku.chinesecheckers.gameboard.Position;
import ca.brocku.chinesecheckers.uiengine.Compositor;
import ca.brocku.chinesecheckers.uiengine.PieceInformation;
import ca.brocku.chinesecheckers.uiengine.utils.GameBoardPositionsCalculator;

/**
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/3/2014
 */
public class PieceVisual extends Visual {
    Position p;
    PieceInformation info;
    int color;

    public PieceVisual(Position p, int color) {
        this.color = color;

        this.p = p;
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

        if(info == null) {
            for(PieceInformation pos : new GameBoardPositionsCalculator().calculatePiecePositions(canvas.getWidth(), canvas.getHeight())) {
                if(pos.index == this.p.getIndex() && pos.row == this.p.getRow()) {
                    this.info = pos;
                    break;
                }
            }
        }

        canvas.drawCircle(info.x, info.y, info.radius, p);
    }
}
