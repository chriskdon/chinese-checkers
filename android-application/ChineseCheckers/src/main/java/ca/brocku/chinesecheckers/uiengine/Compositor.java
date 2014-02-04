package ca.brocku.chinesecheckers.uiengine;

import android.graphics.Canvas;

import java.util.Stack;

import ca.brocku.chinesecheckers.uiengine.visuals.Visual;

/**
 * Composite a series of static and animated visuals together to form an image
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/3/2014
 */
public class Compositor {
    private Stack<Visual> visuals = new Stack<Visual>();

    /**
     * Store a visual on the stack to be composed into the final frame.
     *
     * @param v The visual to add.
     */
    public void pushVisual(Visual v) {
        visuals.push(v);
    }

    /**
     * Composite all the visuals onto the canvas.
     *
     * @param canvas    The canvas to composite onto.
     */
    public void composite(Canvas canvas) {
        for(Visual v : visuals) {
//            if(v.needsRedraw()) { // TODO: Store layers that don't need redraw
                v.draw(canvas);
  //          }
        }
    }
}
