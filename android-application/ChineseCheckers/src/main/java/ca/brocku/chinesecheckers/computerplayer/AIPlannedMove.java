package ca.brocku.chinesecheckers.computerplayer;

import java.util.ArrayList;
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
    private int heuristic = 0;
    private Piece pieceMoved;
    private ArrayList<Position> path;


    /**
     * Constructor for the piece that moves and the path taken.
     *
     * @param pieceMoved    The piece that was moved.
     * @param path          The path the piece took to move.
     */
    //public AIPlannedMove(Piece pieceMoved, List<Position> path, int heuristic) {
    public AIPlannedMove(Piece pieceMoved, ArrayList<Position> path, int heuristic) {
        this.pieceMoved = pieceMoved;
        this.path = path;
        this.heuristic = heuristic;
    }

    public void setHeuristic(int heuristic){ this.heuristic = heuristic; }

    public void setPieceMoved(Piece piece){ this.pieceMoved = piece; }

    public void setPath(ArrayList<Position> path){ this.path = path; }

    public int getHeuristic() {
        return heuristic;
    }

    public Piece getPieceMoved() {
        return pieceMoved;
    }

    public ArrayList<Position> getPath() {
        return path;
    }
}
