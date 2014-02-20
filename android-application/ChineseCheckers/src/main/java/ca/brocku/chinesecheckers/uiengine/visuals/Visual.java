package ca.brocku.chinesecheckers.uiengine.visuals;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.ListIterator;

import ca.brocku.chinesecheckers.uiengine.Dimensions;
import ca.brocku.chinesecheckers.uiengine.PixelPosition;

/**
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/2/2014
 */
public class Visual {
    protected Visual parent;
    private ArrayList<Visual> visuals = new ArrayList<Visual>(); 		// Order matters
    protected TouchEventHandler handler = null;
    public Dimensions dimensions;   // TODO: Make private
    public PixelPosition position; // TODO: make private

    public Visual(float x, float y, float w, float h) {
        this(new PixelPosition(x, y), new Dimensions(w, h));
    }

    public Visual(PixelPosition position, Dimensions dimensions) {
        this.position = position;
        this.dimensions = dimensions;
    }

    /**
     * Add a child to this visual element
     * @param v The child to add to this.
     */
    public void addChild(Visual v) {
        if(v == null) { throw new RuntimeException("Can't add null child"); }
        if(v == this) { throw new RuntimeException("Can't add visual as child of its self."); }
        if(v.parent != null) { throw new RuntimeException("Child is already has a parent."); }

        // Add it
        v.setParent(this);
        visuals.add(v);
    }

    /**
     * Set the parent of this visual element in the hierarchy
     * @param v Set this instance parent to <code>v</code>.
     */
    public void setParent(Visual v) {
        if(parent != null) { throw new RuntimeException("Parent can't be changed."); }
        if(v != null) {
            parent = v;

            this.position.addOffset(parent.position);
        }
    }

    /**
     * Did the (x,y) intersect with any part of this <code>Visual</code>.
     * @param x The x coordinate.
     * @param y The y coordinate
     * @return  True if it intersects, False otherwise.
     */
    protected boolean didIntersect(float x, float y) {
        return ( x >= this.position.getX() &&
                 x <= this.position.getX() + this.dimensions.getWidth() &&
                 y >= this.position.getY() &&
                 y <= this.position.getY() + this.dimensions.getHeight());
    }

    /**
     * Executed when drawing the onto the canvas.
     * @param c The canvas to draw to.
     */
    public void onDraw(Canvas c) {}

    /**
     * Call when you want to draw to the canvas.
     * @param c The canvas to draw to.
     */
    final public void draw(Canvas c) {
        this.onDraw(c);

        for(Visual v : visuals) {
            v.draw(c);
        }
    }

    /**
     * Set the touch event handler for this visual element.
     * @param handler   The handler to fire.
     */
    public final void setTouchEventHandler(TouchEventHandler handler) {
        this.handler = handler;
    }

    /**
     * Send touch event.
     *
     * @param e           		The event info.
     * @param sendToAncestors 	If true the event will be sent to this instance's ancestors (e.g. parent up).
     *                         	It won't stop bubbling when it reaches itself again.
     */
    public void sendTouchEvent(MotionEvent e, boolean sendToAncestors) {
        if(didIntersect(e.getX(), e.getY())) {
            pushEventDown((sendToAncestors ? null : this), e);
        }
    }

    /**
     * Send touch event to children.
     * It will not send the event to it's ancestors.
     *
     * @param e Event details.
     */
    public void sendTouchEvent(MotionEvent e) {
        sendTouchEvent(e, false);
    }

    // ------------------------------------------
    // EVENT BUBBLING
    //
    // Push the event down to the lowest child and
    // then start propagating the events upward
    // through the hierarchy.
    // ------------------------------------------

    /**
     * Bubble the event up from the lowest visual element to the parent.
     * @param sender    The <code>Visual</code> that sent the event.
     * @param e         The event that occurred.
     */
    private void bubbleEventUp(Visual sender, MotionEvent e) {
        boolean shouldBubble = true;

        if(this.handler != null) {
            shouldBubble = !this.handler.onTouch(this, e);
        }

        if(this != sender && shouldBubble && this.parent != null) {
            this.parent.bubbleEventUp(sender, e);
        }
    }

    /**
     * Push the event down to the lowest child and then start bubbling up.
     *
     * @param sender    The <code>Visual</code> that sent the event.
     * @param e         The event that occurred.
     */
    private void pushEventDown(Visual sender, MotionEvent e) {
        ListIterator<Visual> li = visuals.listIterator(visuals.size());

        // Most recent element gets event if overlapping
        Visual v = null;
        while(li.hasPrevious()) {
            v = li.previous();

            if(v.didIntersect(e.getX(), e.getY())) {
                break;
            } else {
                v = null;
            }
        }

        if(v != null) {
            v.pushEventDown(sender, e);
        } else {
            bubbleEventUp(sender, e);
        }
    }

    /**
     * Handles touch events.
     */
    public interface TouchEventHandler {
        /**
         * Fired when a Visual element is touched within it's bounds.
         *
         * @param  v The Visual that was touched
         * @param  e The event.
         * @return   True to absorb the event and stop bubbling, or false to bubble.
         */
        public boolean onTouch(Visual v, MotionEvent e);
    }
}
