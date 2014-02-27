package ca.brocku.chinesecheckers.gamestate;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import ca.brocku.chinesecheckers.gameboard.GameBoard;
import ca.brocku.chinesecheckers.gameboard.Piece;
import ca.brocku.chinesecheckers.gameboard.Position;
import ca.brocku.chinesecheckers.gameboard.ReadOnlyGameBoard;

/**
 * Handles coordinating the game between multiple players and keeping the game
 * board up to date.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/22/2014
 */
public class GameStateManager implements Parcelable {
    private GameBoard gameBoard;
    private List<Player> players;

    private transient GameStateEvents gameStateEventsHandler;

    /**
     * Constructor
     * @param gameBoard The game board to use to manage the rules and state of the game.
     */
    public GameStateManager(GameBoard gameBoard) {
        if(gameBoard == null) {
            throw new IllegalArgumentException("GameBoard cannot be null.");
        }

        this.gameBoard = gameBoard;
        this.gameBoard.setGameBoardEventsHandler(new GameBoardEventsHandler());
    }

    /**
     * Return a gameboard that can only be looked at.
     * @return  The gameboard.
     */
    public ReadOnlyGameBoard getGameBoard() {
        return new ReadOnlyGameBoard(gameBoard);
    }

    /**
     * Parcelable constructor
     *
     * @param parcel    The parcel instance to generate the instance from.
     */
    private GameStateManager(Parcel parcel) {
        gameBoard = parcel.readParcelable(GameBoard.class.getClassLoader());
    }

    /**
     * Set the event handler for the various game state events.
     * @param handler   The handler to register
     */
    public void setGameStateEventsHandler(GameStateEvents handler) {
        this.gameStateEventsHandler = handler;
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

    /**
     * Recreate this instance
     */
    public static final Parcelable.Creator<GameStateManager> CREATOR =
            new Parcelable.Creator<GameStateManager>() {

        public GameStateManager createFromParcel(Parcel in) {
            return new GameStateManager(in);
        }

        public GameStateManager[] newArray(int size) {
            return new GameStateManager[size];
        }
    };

    /**
     * Handles the events coming directly from the game board.
     */
    private class GameBoardEventsHandler implements GameBoard.GameBoardEvents {
        @Override
        public void onPlayerWon(int playerNumber) {
            // TODO: Add real player
            if(GameStateManager.this.gameStateEventsHandler != null) {
                GameStateManager.this.gameStateEventsHandler.onPlayerWon(null, playerNumber);
            }
        }
    }

    /**
     * Events that the GameStateManager will throw throughout
     * the lifecycle of the game.
     */
    public static interface GameStateEvents {
        /**
         * Fired when it is a new players turn.
         *
         * @param player    The player who's turn it is.
         */
        public void onPlayerTurn(Player player);

        /**
         * Fired when a piece on the board is moved from one position to another.
         * @param player    The player who made the move.
         * @param move      The path describing the move.
         */
        public void onPieceMoved(Player player, Move move);

        /**
         * Occurs when a player forfeit the game.
         *
         * TODO: Is this just because they quit, or could this be a result of them taking to long to make a move and being kicked out.
         *
         * @param player    The player who forfeited.
         */
        public void onForfeit(Player player);

        /**
         * Occurs when a player wins the game.
         *
         * @param player    The player that won.
         * @param position  The position they finished in (1st, 2nd, 3rd, etc.).
         */
        public void onPlayerWon(Player player, int position);
    }
}
