package ca.brocku.chinesecheckers.gameboard;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The implementation of Position used to represent a position on the chinese checkers game grid.
 *
 * Author: Peter Pobojewski
 * Student #: 4528311
 * Date: 2/13/2014
 */
public class GridPosition extends Position {
    private int row, index;

    public GridPosition(int r, int i) {
        row = r;
        index = i;
    }
    /**
     * Returns the row value of a position.
     *
     * @return  Row value
     */
    public int getRow() {
        return row;
    }
    /**
     * Returns the index or column value of a positon.
     *
     * @return  Index value
     */
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
     * Constructor for parcel.
     * @param p The parcel.
     */
    private GridPosition(Parcel p) {
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
    public static final Parcelable.Creator<GridPosition> CREATOR =
            new Parcelable.Creator<GridPosition>() {

        public GridPosition createFromParcel(Parcel in) {
            return new GridPosition(in);
        }

        public GridPosition[] newArray(int size) {
            return new GridPosition[size];
        }
    };
}
