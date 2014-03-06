package ca.brocku.chinesecheckers.computerplayer;

import java.util.Collections;
import java.util.List;

import ca.brocku.chinesecheckers.gameboard.Piece;
import ca.brocku.chinesecheckers.gameboard.Position;

/**
 * A potential move to be made by a computer player.
 *
 * Author: James Kostiuk
 * Student #: 4366340
 * Date: 2/22/2014
 */

public class AIPlannedMove {
    private int heuristic;          // The heuristic after the move.
    private Piece pieceMoved;       // The piece that was moved.
    private Position newPosition;
    //private List<Position> path;    // The path it took to get to its final position.

    /**
     * Constructor for the piece that moves and the path taken.
     *
     * @param pieceMoved    The piece that was moved.
     * @param path          The path the piece took to move.
     */
    //public AIPlannedMove(Piece pieceMoved, List<Position> path, int heuristic) {
    public AIPlannedMove(Piece pieceMoved, Position newPosition, int heuristic) {
        this.pieceMoved = pieceMoved;
        this.newPosition = newPosition;
        //this.path = path;
        this.heuristic = heuristic;
    }

    public void setHeuristic(int heuristic){ this.heuristic = heuristic; }

    public void setPieceMoved(Piece piece){ this.pieceMoved = piece; }

    public void setNewPosition(Position newPosition){ this.newPosition = newPosition; }

    public int getHeuristic() {
        return heuristic;
    }

    public Piece getPieceMoved() {
        return pieceMoved;
    }

    public Position getNewPosition() {
        return newPosition;
    }

    /**
     * Returns an unmodifiable path that the piece took.
     *
     * @return The path.
     */
    /*public List<Position> getPath() {
        return Collections.unmodifiableList(path);
    }*/
}
