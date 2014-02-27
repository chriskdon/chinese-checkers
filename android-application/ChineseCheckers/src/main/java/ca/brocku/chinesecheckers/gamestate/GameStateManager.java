package ca.brocku.chinesecheckers.gamestate;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.brocku.chinesecheckers.gameboard.GameBoard;
import ca.brocku.chinesecheckers.gameboard.Piece;
import ca.brocku.chinesecheckers.gameboard.Position;
import ca.brocku.chinesecheckers.gameboard.ReadOnlyGameBoard;
import ca.brocku.chinesecheckers.uiengine.visuals.Visual;

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
    private Map<Player.PlayerColor, Player> players;          // Players in the game
    private Player.PlayerColor currentPlayer;   // The current players turn

    private transient GameStateEvents gameStateEventsHandler;

    /**
     * Constructor
     *
     * @param gameBoard The game board to use to manage the rules and state of the game.
     * @param players   The players in the game.
     */
    public GameStateManager(GameBoard gameBoard, ArrayList<Player> players) {
        this(gameBoard, players, null);
    }

    /**
     * Constructor
     *
     * @param gameBoard         The game board to use to manage the rules and state of the game.
     * @param players           The players in the game.
     * @param currentPlayer     The current player's turn.
     */
    public GameStateManager(GameBoard gameBoard, ArrayList<Player> players, Player.PlayerColor currentPlayer) {
        if(gameBoard == null) { throw new IllegalArgumentException("Board must be defined."); }
        if(players == null || players.size() <= 0) {
            throw new IllegalArgumentException("Players must be defined.");
        }

        this.gameBoard = gameBoard;

        this.players = new HashMap<Player.PlayerColor, Player>(players.size());
        for(Player p : players) {
            this.players.put(p.getPlayerColor(), p);
        }

        if(currentPlayer == null) {
            this.currentPlayer = Player.FIRST_PLAYER;
        } else {
            this.currentPlayer = currentPlayer;
        }

        this.onStateReady();
    }

    /**
     * Called after all the non-transient objects are ready. This should be called after all
     * constructor code. And after deserialization.
     */
    private void onStateReady() {
        if(this.gameBoard == null) {
            throw new IllegalStateException("GameBoard must be setup.");
        }

        this.gameBoard.setGameBoardEventsHandler(new GameBoardEventsHandler());
    }

    /**
     * Return the player who's turn it currently is.
     * @return  The player.
     */
    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
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
        this((GameBoard) parcel.readParcelable(GameBoard.class.getClassLoader()),
                parcel.readArrayList(Player.class.getClassLoader()),
                Player.PlayerColor.valueOf(parcel.readString()));
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
        dest.writeList(new ArrayList<Player>(players.values()));
        dest.writeString(currentPlayer.toString());
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
