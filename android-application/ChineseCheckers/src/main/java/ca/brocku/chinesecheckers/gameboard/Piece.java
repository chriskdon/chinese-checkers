package ca.brocku.chinesecheckers.gameboard;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * A single piece on the board that is owned
 * by a Player.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/1/2014
 */
public interface Piece extends Parcelable {
    /**
     * Get the position of the piece on the board.
     * @return  The position
     */
    public Position getPosition();

    /**
     * Get the number associated with the player for this piece.
     * @return  The number.
     */
    public int getPlayerNumber();

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    int describeContents();

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    void writeToParcel(Parcel dest, int flags);
}
