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
public class Position implements Parcelable, Serializable {
    int row, index;

    public Position(int row, int index) {
        this.row = row;
        this.index = index;
    }

    public int getRow() {
        return  row;
    }

    public int getIndex() {
        return index;
    }

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
     * Constructor for parcel.
     * @param p The parcel.
     */
    Position(Parcel p) {
        row = p.readInt();
        index = p.readInt();
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
        dest.writeInt(row);
        dest.writeInt(index);
    }

    /**
     * Recreate this instance
     */
    public static final Parcelable.Creator<Position> CREATOR =
            new Parcelable.Creator<Position>() {

                public Position createFromParcel(Parcel in) {
                    return new Position(in);
                }

                public Position[] newArray(int size) {
                    return new Position[size];
                }
            };
}
