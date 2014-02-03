package ca.brocku.chinesecheckers.uiengine.elements;

import android.graphics.Canvas;

/**
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/2/2014
 */
public interface BoardElement {
    /**
     * Draw the object onto the <code>canvas</code>
     *
     * @param canvas    The canvas to draw to.
     */
    public void draw(Canvas canvas);
}
