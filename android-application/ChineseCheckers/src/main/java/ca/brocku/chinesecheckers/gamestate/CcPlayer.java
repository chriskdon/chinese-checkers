package ca.brocku.chinesecheckers.gamestate;

/**
 * The implementation of Player that is used to represent a chinese checkers player.
 *
 * Author: Peter Pobojewski
 * Student #: 4528311
 * Date: 2/13/2014
 */
public class CcPlayer implements Player {
    String name;
    public CcPlayer(String n) {
        name = n;
    }
    /**
     * Returns the name of a player
     *
     * @return  Player's name
     */
    public String getName() {
        return name;
    }
}
