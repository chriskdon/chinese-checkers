package ca.brocku.chinesecheckers.gameboard;

import android.os.Parcel;

/**
 * Creates a AndroidReadOnlyGameBoard out of a AndroidGameBoard
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/22/2014
 */
public class AndroidReadOnlyGameBoard extends javajar.gameboard.ReadOnlyGameBoard implements AndroidGameBoard {
    private AndroidGameBoard gameBoard;

    public AndroidReadOnlyGameBoard(AndroidGameBoard gameBoard) {
        super(gameBoard);
    }

    /**
     * Parcel constructor
     * @param p
     */
    private AndroidReadOnlyGameBoard(Parcel p) {
        gameBoard = p.readParcelable(AndroidGameBoard.class.getClassLoader());
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
        dest.writeParcelable(gameBoard, 0);
    }

}
