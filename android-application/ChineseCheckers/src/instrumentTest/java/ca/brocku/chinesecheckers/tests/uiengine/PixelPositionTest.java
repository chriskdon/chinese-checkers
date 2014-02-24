package ca.brocku.chinesecheckers.tests.uiengine;

import junit.framework.TestCase;

import ca.brocku.chinesecheckers.uiengine.PixelPosition;

/**
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/22/2014
 */
public class PixelPositionTest extends TestCase {
    /**
     * Test the X, Y Getters
     */
    public void testGettersXY() {
        PixelPosition pos = new PixelPosition((float)1.1, (float)2.1);

        assertEquals("X != expected (1)", (float)1.1, pos.getX());
        assertEquals("Y != expected (2)", (float)2.1, pos.getY());
    }

    /**
     * Test the setters.
     */
    public void testSettersXY() {
        PixelPosition pos = new PixelPosition((float)1, (float)2);

        pos.setX((float)10.1);
        pos.setY((float)12.1);

        assertEquals("X != expected (10.1)", (float)10.1, pos.getX());
        assertEquals("Y != expected (12.1)", (float)12.1, pos.getY());
    }

    /**
     * Test the offset calculation.
     */
    public void testOffset() {
        PixelPosition a = new PixelPosition((float)1, (float)2);
        PixelPosition b = new PixelPosition((float)3, (float)4);

        a.addOffset(b);

        assertEquals("Offset X Not Equal", (float)4.0, a.getX());
        assertEquals("Offset Y Not Equal", (float)6.0, a.getY());
    }


}
