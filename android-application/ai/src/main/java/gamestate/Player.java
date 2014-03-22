package gamestate;


import java.io.Serializable;

import gameboard.ReadOnlyGameBoard;

/**
 * Represent a player on the board.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/1/2014
 */
public abstract class Player implements Serializable {
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
     * @param state The current state.
     */
    public abstract MovePath onTurn(ReadOnlyGameBoard board);

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
     * Return the number of this player.
     * @return  The number.
     */
    public final int getPlayerNumber() {
        switch (getPlayerColor()) {
            case RED: return 1;
            case PURPLE: return 2;
            case BLUE: return 3;
            case GREEN: return 4;
            case YELLOW: return 5;
            case ORANGE: return 6;
        }

        throw new IllegalStateException("Invalid Player Color");
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
}
