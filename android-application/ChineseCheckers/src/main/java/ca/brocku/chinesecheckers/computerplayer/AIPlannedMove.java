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
    private int heuristic;
    private Piece pieceMoved;
    private ArrayList<Position> path;

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