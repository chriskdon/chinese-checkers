package ca.brocku.chinesecheckers.gamestate;

import ca.brocku.chinesecheckers.gameboard.ReadOnlyGameBoard;

/**
 * The implementation of Player that is used to represent a chinese checkers player.
 *
 * Author: Peter Pobojewski
 * Student #: 4528311
 * Date: 2/13/2014
 */
public class HumanPlayer extends Player {
    private String name;

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
     * @param gameBoard The current game board.
     * @param handler   This must be called when the player has finished making their turn.
     */
    @Override
    public void onTurn(ReadOnlyGameBoard gameBoard, PlayerTurnHandler handler) {
        // TODO: Human Player Interaction
    }
}
