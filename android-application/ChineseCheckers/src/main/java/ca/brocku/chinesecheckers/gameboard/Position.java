package ca.brocku.chinesecheckers.gameboard;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * The common interface that defines a position
 * on the board.
 *
 * Returns the row a piece a occurs on and the index of the piece
 * within the row.
 *
 * See the architecture diagrams for a better description.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/1/2014
 */
public abstract class Position implements Parcelable, Serializable {
    public abstract int getRow();
    public abstract int getIndex();

    /**
     * Recreate this instance
     */
    public static final Parcelable.Creator<Position> CREATOR =
            new Parcelable.Creator<Position>() {
                @Override
                public Position createFromParcel(Parcel parcel) {
                    return null;
                }

                @Override
                public Position[] newArray(int i) {
                    return new Position[0];
                }
            };

    /**
     * Equality test. Two positions are equal if they have the same row and index
     *
     * @param o The object to test for equality.
     * @return  True if they are equal
     */
    @Override
    public boolean equals(Object o) {
        if(o == null) { return false; }
        if(o == this) { return true; }
        if(o instanceof Position) {
            final Position x = (Position)o;

            return (getIndex() == x.getIndex() && getRow() == x.getRow());
        }

        return  false;
    }

    /**
     * Generate a hash code. Two positions with the same row/index will
     * generate the same hash code.
     *
     * @return  The hash code.
     */
    @Override
    public int hashCode() {
        return ("R" + getRow() + "I" + getIndex()).hashCode();
    }
}
