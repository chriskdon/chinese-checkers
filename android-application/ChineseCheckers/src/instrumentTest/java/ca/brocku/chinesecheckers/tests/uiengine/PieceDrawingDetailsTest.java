package ca.brocku.chinesecheckers.tests.uiengine;

import android.os.Parcel;

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
