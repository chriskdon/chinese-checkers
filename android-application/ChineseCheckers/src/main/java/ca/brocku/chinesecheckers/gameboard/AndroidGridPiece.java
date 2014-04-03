package ca.brocku.chinesecheckers.gameboard;

import android.os.Parcel;
import android.os.Parcelable;

import javajar.gameboard.Position;

/**
 * The implementation of AndroidPiece used to represent the pieces players may use on a chinese checkers
 * board.
 *
 * Author: Peter Pobojewski
 * Student #: 4528311
 * Date: 2/13/2014
 */
public class AndroidGridPiece extends javajar.gameboard.GridPiece implements Parcelable,AndroidPiece {
    //private AndroidPosition position;
    //private int player;

    public AndroidGridPiece(Position pos, int pl) {
        super(pos,pl);
    }

    /**
     * Constructor for parcel.
     * @param p
     */
    private AndroidGridPiece(Parcel p) {
        position = (Position)p.readParcelable(AndroidPosition.class.getClassLoader());
        player = p.readInt();
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
        dest.writeParcelable(new AndroidPosition(position.getRow(),position.getIndex()), 0);
        dest.writeInt(player);
    }

    /**
     * Recreate this instance
     */
    public static final Parcelable.Creator<AndroidGridPiece> CREATOR =
            new Parcelable.Creator<AndroidGridPiece>() {

        public AndroidGridPiece createFromParcel(Parcel in) {
            return new AndroidGridPiece(in);
        }

        public AndroidGridPiece[] newArray(int size) {
            return new AndroidGridPiece[size];
        }
    };
}