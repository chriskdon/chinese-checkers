package ca.brocku.chinesecheckers.gamestate;

import ca.brocku.chinesecheckers.gameboard.ReadOnlyGameBoard;

/**
 * The implementation of Player that is used to represent a chinese checkers player.
 *
 * Author: Peter Pobojewski
 * Student #: 4528311
 * Date: 2/13/2014
 */
public class CcPlayer extends Player {
    private String name;
    private int playerNumber;

    /**
     * Create a new player.
     *
     * @param name          The name of the player.
     * @param playerNumber  The number of the player.
     */
    public CcPlayer(String name, int playerNumber) {
        this.name = name;
        this.playerNumber = playerNumber;
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
     * Return the number of the player. Based on their board position.
     *
     * @return Number of the player.
     */
    @Override
    public int getPlayerNumber() {
        return playerNumber;
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
