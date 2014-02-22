package ca.brocku.chinesecheckers.gameboard;

import ca.brocku.chinesecheckers.gamestate.Player;

/**
 * A single piece on the board that is owned
 * by a Player.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/1/2014
 */
public interface Piece {
    public Position getPosition();
    public int getPlayerNumber();
}
