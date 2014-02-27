package ca.brocku.chinesecheckers.tests.uiengine;

import android.os.Parcel;

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

            /**
             * Describe the kinds of special objects contained in this Parcelable's
             * marshalled representation.
             *
             * @return a bitmask indicating the set of special object types marshalled
             * by the Parcelable.
             */
            @Override
            public int describeContents() {
                return 0;
            }

            /**
             * Flatten this object in to a Parcel.
             *
             * @param dest  The Parcel in which the object should be written.
             * @param flags Additional flags about how the object should be written.
             *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
             */
            @Override
            public void writeToParcel(Parcel dest, int flags) {

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
