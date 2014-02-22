package ca.brocku.chinesecheckers.gamestate;

import ca.brocku.chinesecheckers.gameboard.GameBoard;
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
    private GameBoard gameBoard;
    private GameStateEvents gameStateEventsHandler;

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
     * Set the event handler for the various game state events.
     * @param handler   The handler to register
     */
    public void setGameStateEventsHandler(GameStateEvents handler) {
        this.gameStateEventsHandler = handler;
    }

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
         * Fired when a piece on the board is moved from one position to another.
         * @param move  The path describing the move.
         */
        public void onPieceMoved(Move move);

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
