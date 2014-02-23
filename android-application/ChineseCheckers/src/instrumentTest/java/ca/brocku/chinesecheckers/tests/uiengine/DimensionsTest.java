package ca.brocku.chinesecheckers.tests.uiengine;

import junit.framework.TestCase;

import ca.brocku.chinesecheckers.uiengine.Dimensions;

/**
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/22/2014
 */
public class DimensionsTest extends TestCase {
    /**
     * Make sure the getters return the same value set in constructor.
     */
    public void testGetters() {
        Dimensions d = new Dimensions(100, 150);

        assertEquals("Width not equal to initial value.",(float)100, d.getWidth());
        assertEquals("Height not equal to initial value.",(float)150, d.getHeight());
    }

    /**
     * Make sure the setters update the value.
     */
    public void testSetters() {
        Dimensions d = new Dimensions(100, 150);

        d.setWidth(500);
        d.setHeight(200);

        assertEquals("Width not equal to set value.",(float)500, d.getWidth());
        assertEquals("Height not equal to set value.",(float)200, d.getHeight());
    }
}
