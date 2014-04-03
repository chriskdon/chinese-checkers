package ca.brocku.chinesecheckers.gameboard;

import android.os.Parcel;
import android.os.Parcelable;

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
public class AndroidPosition extends javajar.gameboard.Position implements Parcelable{
    //int row, index;

    public AndroidPosition(int row, int index) {
        super(row,index);
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
    AndroidPosition(Parcel p) {
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
    public static final Parcelable.Creator<AndroidPosition> CREATOR =
            new Parcelable.Creator<AndroidPosition>() {

                public AndroidPosition createFromParcel(Parcel in) {
                    return new AndroidPosition(in);
                }

                public AndroidPosition[] newArray(int size) {
                    return new AndroidPosition[size];
                }
            };
}
