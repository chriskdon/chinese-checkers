package ca.brocku.chinesecheckers.uiengine.visuals;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import ca.brocku.chinesecheckers.uiengine.Compositor;

/**
 * //TODO Implement class
 * Draw animated visual to the canvas.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/3/2014
 */
public abstract class AnimatedVisual extends Visual {
    /**
     * Draw the object onto the <code>canvas</code>
     *
     * @param canvas The canvas to draw to.
     */
    @Override
    public void draw(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(Color.RED);

        canvas.drawCircle(0, 20, 20, p);

     //   x++;

       // redraw();
    }
}
