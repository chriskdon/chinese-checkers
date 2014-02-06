package ca.brocku.chinesecheckers.uiengine.visuals;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import ca.brocku.chinesecheckers.R;
import ca.brocku.chinesecheckers.gameboard.Position;
import ca.brocku.chinesecheckers.uiengine.Compositor;
import ca.brocku.chinesecheckers.uiengine.PieceInformation;
import ca.brocku.chinesecheckers.uiengine.utils.GameBoardPositionsCalculator;

/**
 * Game board with blank positions
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/2/2014
 */
public class GameBoardVisual extends Visual {
    PieceInformation[] positions;
    Paint p;

    public GameBoardVisual(Context context) {
        p = new Paint();
        p.setFlags(Paint.ANTI_ALIAS_FLAG);
        p.setColor(context.getResources().getColor(R.color.gray)); // TODO: Replace with resource color

        redraw(); // It's static only needs to be redrawn once
    }

    /**
     * Draw the object onto the <code>canvas</code>
     *
     * @param canvas The canvas to draw to.
     */
    @Override
    public void draw(Canvas canvas) {
        for(PieceInformation pos : new GameBoardPositionsCalculator().calculatePiecePositions(canvas.getWidth(), canvas.getHeight())) {
            canvas.drawCircle(pos.x, pos.y, pos.radius, p);
        }
    }
}
