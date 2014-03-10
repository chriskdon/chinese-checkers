package ca.brocku.chinesecheckers.gamestate;

import ca.brocku.chinesecheckers.gameboard.ReadOnlyGameBoard;

/**
 * This is used by players to get information about the game when it is their turn
 * and to signal that they have completed their turn.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 3/8/2014
 */
public interface PlayerTurnState {
    /**
     * Signal that a move has been made.
     *
     * @param player    The player that is making the move.
     * @param movePath  The path from start to end of the move that was made.
     */
    public void signalMove(Player player, MovePath movePath);

    /**
     * Get the GameBoard.
     *
     * @return A Read Only version of the board for the player to use.
     */
    public ReadOnlyGameBoard getGameBoard();
}
