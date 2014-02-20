package ca.brocku.chinesecheckers.uiengine;

/**
 * Stores a pixel coordinate
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/2/2014
 */
public class PixelPosition {
    public float x; // The x coordinate
    public float y; // The y coordinate

    /**
     * Initialize pixel position.
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public PixelPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void addOffset(PixelPosition position) {
        this.x += position.x;
        this.y += position.y;
    }

    @Override
    public String toString() {
        return "(" + getX() + ", " + getY() + ")";
    }
}
