package ca.brocku.chinesecheckers.gamestate;

import android.os.Parcel;
import android.os.Parcelable;

import com.octo.android.robospice.SpiceManager;

import org.apache.commons.lang3.NotImplementedException;

/**
 * @author Jakub Subczynski
 * @date April 03, 2014
 */
public class OnlineHumanPlayer extends HumanPlayer {
    private long gameId;
    private long userId;


    /**
     * Create a new online human player.
     *
     * @param gameId        the game the user is in
     * @param userId        the ID of the user
     * @param username      the username of the user
     * @param playerNumber  the location of the player on the board
     *
     */
    public OnlineHumanPlayer(long gameId, long userId, String username, int playerNumber) {
        super(username, getPlayerColor(playerNumber));

        this.gameId = gameId;
        this.userId = userId;
    }

    public OnlineHumanPlayer(Parcel in) {
        this(in.readLong(), in.readLong(), in.readString(), in.readInt());
    }

    @Override
    public void signalMove(MovePath movePath) {
        throw new NotImplementedException("Used the spiced one.");
    }

    public void signalMove(SpiceManager spiceManager, MovePath movePath) {
        super.signalMove(movePath);

        // TODO: Network
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
        dest.writeLong(gameId);
        dest.writeLong(userId);
        dest.writeString(getName());
        dest.writeInt(getPlayerNumber());
    }

    /**
     * Recreate this instance
     */
    public static final Parcelable.Creator<OnlineHumanPlayer> CREATOR =
            new Parcelable.Creator<OnlineHumanPlayer>() {

                public OnlineHumanPlayer createFromParcel(Parcel in) {
                    return new OnlineHumanPlayer(in);
                }

                public OnlineHumanPlayer[] newArray(int size) {
                    return new OnlineHumanPlayer[size];
                }
            };
}
