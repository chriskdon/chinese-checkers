package ca.brocku.chinesecheckers.gamestate;

import android.os.Parcel;
import android.os.Parcelable;

import com.ccapi.receivables.PlayerMoveReceivable;

import ca.brocku.chinesecheckers.gameboard.ReadOnlyGameBoard;
import de.greenrobot.event.EventBus;

/**
 * @author Jakub Subczynski
 * @date April 03, 2014
 */
public class NetworkPlayer extends Player {
    private long gameId;
    private long userId;
    private String username;
    private int playerNumber;
    private MovePath m;

    public NetworkPlayer(long userId, long gameId, String username, int playerNumber) {
        super(getPlayerColor(playerNumber));

        this.userId = userId;
        this.gameId = gameId;
        this.username = username;
        this.playerNumber = playerNumber;

        EventBus.getDefault().register(this);
    }

    public NetworkPlayer(Parcel parcel) {
        this(parcel.readLong(), parcel.readLong(), parcel.readString(), parcel.readInt());
    }

    /**
     * Received move from the network.
     * @param event
     */
    public void onEvent(PlayerMoveReceivable event) {
        if(event.userId != this.userId || event.gameId != this.gameId) {
            return; // Not our event
        }

        m = new MovePath(event.move);
    }

    @Override
    public MovePath onTurn(ReadOnlyGameBoard board) {
        while(m == null){
            try {
                Thread.sleep(THREAD_SLEEP_TIME);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex); // Rethrow at runtime
            }

        }

        MovePath temp = m;
        m = null;
        return temp;
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
        dest.writeLong(gameId);
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
