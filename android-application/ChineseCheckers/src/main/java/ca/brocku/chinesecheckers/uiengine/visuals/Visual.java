package ca.brocku.chinesecheckers.uiengine.visuals;

import android.graphics.Canvas;

/**
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/2/2014
 */
public abstract class Visual {
    protected boolean redraw = false;

    /**
     * Draw the object onto the <code>canvas</code>
     *
     * @param canvas    The canvas to draw to.
     */
    public abstract void draw(Canvas canvas);

    public synchronized void redraw() {
        redraw = true;
    }

    public synchronized boolean needsRedraw() {
        return redraw;
    }
}
