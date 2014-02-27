package ca.brocku.chinesecheckers.gamestate;

import java.io.Serializable;

import ca.brocku.chinesecheckers.gameboard.ReadOnlyGameBoard;

/**
 * Represent a player on the board.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/1/2014
 */
public abstract class Player implements Serializable {
    /**
     * Executed when it is this players turn to act.
     * @param gameBoard The current game board.
     * @param handler   This must be called when the player has finished making their turn.
     */
    public abstract void onTurn(ReadOnlyGameBoard gameBoard, PlayerTurnHandler handler);

    /**
     * Get the name of this player.
     * @return  Return the name.
     */
    public abstract String getName();

    /**
     * Return the number identifier for this player.
     * @return  Player ID
     */
    public abstract int getPlayerNumber();

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
