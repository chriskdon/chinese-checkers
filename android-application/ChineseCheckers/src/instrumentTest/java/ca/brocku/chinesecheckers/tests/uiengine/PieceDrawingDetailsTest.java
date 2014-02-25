package ca.brocku.chinesecheckers.tests.uiengine;

import junit.framework.TestCase;

import ca.brocku.chinesecheckers.gameboard.Position;
import ca.brocku.chinesecheckers.uiengine.PieceDrawingDetails;

/**
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/22/2014
 */
public class PieceDrawingDetailsTest extends TestCase {
    /**
     * Initialize the object
     * @return The object
     */
    private PieceDrawingDetails init() {
        return new PieceDrawingDetails(new Position() {
            @Override
            public int getRow() {
                return 0;
            }

            @Override
            public int getIndex() {
                return 0;
            }
        }, 10, 10, 50);
    }

    /**
     * Make sure the radius returns the correct result
     */
    public void testGetterRadius() {
        PieceDrawingDetails d = init();

        assertEquals("Radius not equal to initial value", (float)50.0, d.getRadius());
    }

    /**
     * Make sure the position returns the correct row/index.
     */
    public void testGetterPosition() {
        PieceDrawingDetails d = init();

        assertEquals("Position Row not equal", 0, d.getPosition().getRow());
        assertEquals("Position Index not equal", 0, d.getPosition().getIndex());
    }
}
