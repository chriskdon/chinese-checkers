package ca.brocku.chinesecheckers.uiengine.elements;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import ca.brocku.chinesecheckers.R;
import ca.brocku.chinesecheckers.gameboard.GameBoard;

/**
 * Game board with blank positions
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/2/2014
 */
public class EmptyGameBoardElement implements BoardElement {
    private Context context;

    public EmptyGameBoardElement(Context context) {
        this.context = context;
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
        p.setColor(Color.GRAY); // TODO: Replace with resource color

        final int width = canvas.getWidth();

        int radius = width/50;
        int rowOffset = radius + 100;
        for(int row = 0; row < GameBoard.ROW_POSITION_COUNT.length; row++) {
            int indexCount = GameBoard.ROW_POSITION_COUNT[row];

            int columnOffset = width/2 - ((radius*3)*indexCount)/2;

            for(int i = 0; i < indexCount; i++) {
                canvas.drawCircle(columnOffset, rowOffset, radius, p);
                columnOffset += radius*3;
            }

            rowOffset += radius*2.5;
        }
    }
}
