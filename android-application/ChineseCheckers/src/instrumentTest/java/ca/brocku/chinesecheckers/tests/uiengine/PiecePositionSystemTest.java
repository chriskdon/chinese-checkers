package ca.brocku.chinesecheckers.tests.uiengine;

import junit.framework.TestCase;

import ca.brocku.chinesecheckers.gameboard.Position;
import ca.brocku.chinesecheckers.uiengine.PieceDrawingDetails;
import ca.brocku.chinesecheckers.uiengine.PiecePositionSystem;

/**
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/22/2014
 */
public class PiecePositionSystemTest extends TestCase {
    private PiecePositionSystem init() {
        return new PiecePositionSystem(1200, 1594);
    }

    private Position createPosition(final int row, final int index) {
        return new Position() {
            @Override
            public int getRow() {
                return row;
            }

            @Override
            public int getIndex() {
                return index;
            }
        };
    }

    /**
     * Make sure the position details map can't be modified.
     */
    public void testUnmodifiablePositionMapping() {
       PiecePositionSystem pps = init();

       try {
           pps.getPositionDetailsMapping().clear();
           assertEquals("No Exception was thrown when trying to modify list,", 1, 1);
       } catch (UnsupportedOperationException ex) {}
    }

    /**
     * Make sure the position details list can't be modified.
     */
    public void testUnmodifiablePositionList() {
        PiecePositionSystem pps = init();

        try {
            pps.getPositionDetails().clear();
            assertEquals("No Exception was thrown when trying to modify list,", 1, 1);
        } catch (UnsupportedOperationException ex) {}
    }

    /**
     * Check the position.
     */
    private void checkXYPosition(PiecePositionSystem pps, int row, int index, int x, int y) {
        PieceDrawingDetails d = pps.get(createPosition(row, index));

        assertTrue("(X,Y) not correct.", d.getX() == x && d.getY() == y);
    }

    /**
     * Test that the calculations create the correct XY Calculations
     */
    public void testPositionXyCalculation() {
        PiecePositionSystem pps = init();

        checkXYPosition(pps, 0, 0, 600, 215);
        checkXYPosition(pps, 9, 3, 474, 869);
        checkXYPosition(pps, 16, 0, 600, 1378);
    }
}
