package ca.brocku.chinesecheckers.gamestate;

import android.os.Parcel;
import android.os.Parcelable;

import ca.brocku.chinesecheckers.gameboard.ReadOnlyGameBoard;

/**
 * @author Jakub Subczynski
 * @date April 03, 2014
 */
public class NetworkPlayer extends Player {
    private long userId;
    private String username;
    private int playerNumber;

    public NetworkPlayer(long userId, String username, int playerNumber) {
        super(getPlayerColor(playerNumber));

        this.userId = userId;
        this.username = username;
        this.playerNumber = playerNumber;
    }

    public NetworkPlayer(Parcel parcel) {
        this(parcel.readLong(), parcel.readString(), parcel.readInt());
    }

    @Override
    public MovePath onTurn(ReadOnlyGameBoard board) {
        int i = 1;
        while(2>i){
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex); // Rethrow at runtime
            }

        }

        return null;
    }

    @Override
    public String getName() {
        return username;
    }

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
        dest.writeLong(userId);
        dest.writeString(username);
        dest.writeInt(playerNumber);
    }

    /**
     * Recreate this instance
     */
    public static final Parcelable.Creator<NetworkPlayer> CREATOR =
            new Parcelable.Creator<NetworkPlayer>() {

                public NetworkPlayer createFromParcel(Parcel in) {
                    return new NetworkPlayer(in);
                }

                public NetworkPlayer[] newArray(int size) {
                    return new NetworkPlayer[size];
                }
            };
}
