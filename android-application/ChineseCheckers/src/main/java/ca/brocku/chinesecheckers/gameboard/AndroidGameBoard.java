package ca.brocku.chinesecheckers.gameboard;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * The representation of a game board and what it can do.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/1/2014
 */
public interface AndroidGameBoard extends Parcelable, javajar.gameboard.GameBoard {

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    public abstract int describeContents();

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public abstract void writeToParcel(Parcel dest, int flags);

    /**
     * Has the specified player won the game.
     * @param playerNumber  The player number.
     * @return  True if they have won, false otherwise.
     */
}
