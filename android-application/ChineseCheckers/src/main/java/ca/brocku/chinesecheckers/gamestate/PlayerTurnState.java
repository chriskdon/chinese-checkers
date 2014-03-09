package ca.brocku.chinesecheckers.gamestate;

import ca.brocku.chinesecheckers.gameboard.ReadOnlyGameBoard;

/**
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 3/8/2014
 */
public interface PlayerTurnState {
    /**
     * Signal that a move has been made.
     * @param m The move that was amde.
     */
    public void signalMove(Player p, MovePath m);

    /**
     * Get the gameboard.
     *
     * @return A Read Only version of the board for the player to use.
     */
    public ReadOnlyGameBoard getGameBoard();
}
