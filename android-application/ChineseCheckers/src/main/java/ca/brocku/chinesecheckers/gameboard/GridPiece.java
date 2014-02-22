package ca.brocku.chinesecheckers.gameboard;

import ca.brocku.chinesecheckers.gamestate.CcPlayer;
import ca.brocku.chinesecheckers.gamestate.Player;
/**
 * The implementation of Piece used to represent the pieces players may use on a chinese checkers
 * board.
 *
 * Author: Peter Pobojewski
 * Student #: 4528311
 * Date: 2/13/2014
 */
public class GridPiece implements Piece {
    Position position;
    int player;
    public GridPiece (Position pos, int pl) {
        position = pos;
        player = pl;
    }
    /**
     * Returns the position of a piece on the board.
     *
     * @return  Position of piece
     */
    public Position getPosition() {
        return position;
    }
    /**
     * Returns the owner of the piece.
     *
     * @return  Player that owns the piece.
     */
    public int getPlayer() {
        return player;
    }
    /**
     * Sets a new position for a piece.
     *
     */
    public void setPosition(Position at) {
        position=at;
    }
    /**
     * Sets a new player for a piece (May not be needed).
     *
     */
    public void setPlayer(int np) {
        player = np;
    }
}
