package ca.brocku.chinesecheckers.tests.uiengine;

import junit.framework.TestCase;

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

    /**
     * Make sure the position details can't be modified.
     */
    public void testUnmodifiable() {
        PiecePositionSystem pps = init();

       try {
           pps.getPositionDetails().clear();
           assertEquals("No Exception was thrown when trying to modify list,", 1, 1);
       } catch (UnsupportedOperationException ex) {}
    }
}
