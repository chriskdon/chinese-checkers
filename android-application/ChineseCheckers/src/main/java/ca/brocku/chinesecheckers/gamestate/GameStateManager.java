package ca.brocku.chinesecheckers.gamestate;

import ca.brocku.chinesecheckers.gameboard.Piece;
import ca.brocku.chinesecheckers.gameboard.Position;

/**
 * Handles coordinating the game between multiple players and keeping the game
 * board up to date.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/22/2014
 */
public class GameStateManager {


    /**
     * Events that the GameStateManager will throw throughout the lifecycle of the game.
     */
    public static interface GameStateEvents {
        /**
         * Fired when a piece on the board is moved from one position to another.
         * @param from      The piece that was moved (with its starting position still intact)
         * @param pathTo    The path taken by the piece to get to the final resting position.
         *                  Order of the positions added to this array matters. 0..n, 0 is the
         *                  first position moved to and n is the final resting spot.
         */
        public void onPieceMoved(Piece from, Position[] pathTo);

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
