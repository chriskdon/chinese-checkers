package ca.brocku.chinesecheckers.gamestate;

import java.io.Serializable;
import android.os.Parcelable;

import ca.brocku.chinesecheckers.gameboard.ReadOnlyGameBoard;

/**
 * Represent a player on the board.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/1/2014
 */
public abstract class Player implements Serializable, Parcelable {
    public static enum PlayerColor {
        RED,
        PURPLE,
        BLUE,
        GREEN,
        YELLOW,
        ORANGE
    };

    public static final PlayerColor FIRST_PLAYER = PlayerColor.RED;

    private PlayerColor playerColor;

    public Player(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    /**
     * Executed when it is this players turn to act.
     * @param gameBoard The current game board.
     */
    public abstract Move onTurn(ReadOnlyGameBoard gameBoard);

    /**
     * Get the name of this player.
     * @return  Return the name.
     */
    public abstract String getName();

    /**
     * Return the color the player is associated with.
     *
     * @return  The player color
     */
    public final PlayerColor getPlayerColor() {
        return playerColor;
    }

    /**
     * Return the color of the player based on their number.
     *
     * @param playerNumber  The number of the player.
     *
     * @return  The color of the player
     */
    public static final PlayerColor getPlayerColor(int playerNumber) {
        switch (playerNumber) {
            case 1: return PlayerColor.RED;
            case 2: return PlayerColor.PURPLE;
            case 3: return PlayerColor.BLUE;
            case 4: return PlayerColor.GREEN;
            case 5: return PlayerColor.YELLOW;
            case 6: return PlayerColor.ORANGE;
        }

        throw new IllegalArgumentException("Player number must be between 1 - 6");
    }

    /**
     * Handles a player making a move.
     */
    public interface PlayerTurnHandler {
        /**
         * Fired when the player makes a move.
         * @param move  The move that was made.
         */
        public void onMoveMade(Move move);
    }
}
