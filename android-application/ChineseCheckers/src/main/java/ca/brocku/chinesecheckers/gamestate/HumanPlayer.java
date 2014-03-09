package ca.brocku.chinesecheckers.gamestate;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The implementation of Player that is used to represent a chinese checkers player.
 *
 * Author: Peter Pobojewski
 * Student #: 4528311
 * Date: 2/13/2014
 */
public class HumanPlayer extends Player {
    private String name;

    private transient PlayerTurnState playerTurnState;

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

    /**
     * Executed when it is this players turn to act.
     *
     * @param state The current state.
     */
    @Override
    public void onTurn(PlayerTurnState state) {
        this.playerTurnState = state;
    }

    /**
     * Get the turn state
     * @return
     */
    public PlayerTurnState getPlayerTurnState() {
        if(playerTurnState == null) {
            throw new IllegalStateException("This player does not have a player turn state manager.");
        }

        return this.playerTurnState;
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
