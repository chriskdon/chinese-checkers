package ca.brocku.chinesecheckers.uiengine;

import ca.brocku.chinesecheckers.gameboard.Position;
import ca.brocku.chinesecheckers.gamestate.Player;

/**
 * Can draw components needed for the board.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/1/2014
 */
public interface BoardUiDrawingEngine {
    /**
     * Highlight a position on the board so that the player
     * can see something important is occurring there.
     *
     * @param position The position to highlight.
     */
    public void highlightPosition(Position position);
    public void playerWon(Player player);
}
