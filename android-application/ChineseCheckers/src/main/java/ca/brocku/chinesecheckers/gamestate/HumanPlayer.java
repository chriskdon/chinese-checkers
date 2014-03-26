package ca.brocku.chinesecheckers.gamestate;

import android.os.Parcel;
import android.os.Parcelable;

import ca.brocku.chinesecheckers.gameboard.AndroidReadOnlyGameBoard;
import javajar.gamestate.Player;
import javajar.gameboard.ReadOnlyGameBoard;

/**
 * The implementation of Player that is used to represent a chinese checkers player.
 *
 * Author: Peter Pobojewski
 * Student #: 4528311
 * Date: 2/13/2014
 */
public class HumanPlayer extends Player implements Parcelable{
    private String name;

    // This needs to be small enough that a human can't detect the difference between tapping
    // the screen and seeing their piece move;
    private static final int THREAD_SLEEP_TIME = 100;

    /**
     * Create a new player.
     *
     * @param name          The name of the player.
     * @param playerColor   The color of the player.
     */
    public HumanPlayer(String name, PlayerColor playerColor) {
        super(playerColor);

        this.name = name;
    }

    /**
     * Revive
     *
     * @param parcel
     */
    private HumanPlayer(Parcel parcel) {
        this(parcel.readString(), PlayerColor.valueOf(parcel.readString()));
    }

    /**
     * Returns the name of a player
     *
     * @return  Player's name
     */
    public String getName() {
        return name;
    }

    private AndroidMovePath m;

    /**
     * Executed when it is this players turn to act.
     *
     * @param board
     */
    @Override
    public AndroidMovePath onTurn(ReadOnlyGameBoard board) {
        while(m == null){
            try {
                Thread.sleep(THREAD_SLEEP_TIME);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex); // Rethrow at runtime
            }

        }

        AndroidMovePath temp = m;
        m = null;
        return temp;
    }

    public void signalMove(AndroidMovePath movePath) {
        m = movePath;
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
        dest.writeString(name);
        dest.writeString(getPlayerColor().toString());
    }

    /**
     * Recreate this instance
     */
    public static final Parcelable.Creator<HumanPlayer> CREATOR =
        new Parcelable.Creator<HumanPlayer>() {

        public HumanPlayer createFromParcel(Parcel in) {
            return new HumanPlayer(in);
        }

        public HumanPlayer[] newArray(int size) {
            return new HumanPlayer[size];
        }
    };
}
